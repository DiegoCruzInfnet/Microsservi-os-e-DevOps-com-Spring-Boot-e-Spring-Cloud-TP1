# Sistema de Gerenciamento de Biblioteca

Sistema de gerenciamento de biblioteca construído com arquitetura de **microservices**, utilizando Spring Boot e Spring Cloud. O sistema controla o acervo de livros, o cadastro de usuários, os empréstimos e as avaliações dos leitores.

---

## Descrição do Projeto

A aplicação é dividida em serviços independentes, cada um responsável por um domínio e dono do seu próprio banco de dados. As requisições externas passam por um **API Gateway**, os serviços se registram em um **Discovery Server (Eureka)** e buscam suas configurações em um **Config Server**. A comunicação entre serviços é protegida por mecanismos de resiliência (Circuit Breaker).

---

## Serviços

| Serviço | Descrição | Porta | Banco |
|---|---|---|---|
| Config Server | Configuração centralizada (lida de repositório Git) | 8888 | — |
| Eureka Server | Discovery Server (registro e descoberta) | 8761 | — |
| API Gateway | Ponto único de entrada e roteamento | 8765 | — |
| Biblioteca | Gerenciamento de livros e usuários | 8080 | PostgreSQL (`biblioteca`) |
| loan-service | Gerenciamento de empréstimos | 8082 | PostgreSQL (`loan`) |
| avaliacao-service | Gerenciamento de avaliações | 8081 | MongoDB (`avaliacao`) |

---

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.3.5
- Spring Cloud 2023.0.3 (Config, Eureka, Gateway, OpenFeign)
- Resilience4j (Circuit Breaker + Fallback)
- PostgreSQL
- MongoDB
- Docker
- Maven

---

## Pré-requisitos

Antes de executar o projeto, é necessário ter instalado:

- **JDK 21**
- **Maven** (ou usar o wrapper `mvnw` incluído em cada serviço)
- **PostgreSQL** (rodando localmente na porta 5432)
- **Docker** (para executar o MongoDB)
- **Git** (o Config Server lê as configurações de um repositório Git)

---

## Configuração

### 1. Bancos de dados PostgreSQL

Os serviços **Biblioteca** e **loan-service** utilizam PostgreSQL. É necessário criar os dois bancos manualmente antes de subir os serviços:

```sql
CREATE DATABASE biblioteca;
CREATE DATABASE loan;
```

As tabelas são criadas automaticamente pelo Hibernate na primeira execução (`ddl-auto=update`).

### 2. Senha do PostgreSQL

Cada serviço que usa PostgreSQL espera a senha em um arquivo local `application-local.properties`, que deve ser criado manualmente em:

- `Biblioteca/src/main/resources/application-local.properties`
- `Loan_Service/loan_service/src/main/resources/application-local.properties`

Conteúdo de cada arquivo (substitua pela sua senha do PostgreSQL):

```properties
spring.datasource.password=suasenha
```

### 3. MongoDB (via Docker)

O serviço **avaliacao-service** utiliza MongoDB. 

```bash
docker run -d --name mongo-avaliacao -p 27017:27017 mongo:7
```
ou
```bash
docker start mongo-avaliacao
```

## Como Executar

Os serviços devem ser iniciados **nesta ordem**:

1. **Config Server** (porta 8888)
2. **Eureka Server** (porta 8761)
3. **Biblioteca** (porta 8080)
4. **loan-service** (porta 8082)
5. **avaliacao-service** (porta 8081) — requer o MongoDB no ar
6. **API Gateway** (porta 8765)

```bash
./mvnw spring-boot:run
```

## Discovery Server (Eureka)

Após subir o Eureka, o painel com os serviços registrados fica disponível em:

```
http://localhost:8761
```

---

## API Gateway — Rotas

O Gateway é o ponto único de entrada (porta 8765). Rotas configuradas:

| Rota externa (via Gateway) | Serviço destino |
|---|---|
| `http://localhost:8765/api/books` | Biblioteca (livros) |
| `http://localhost:8765/api/users` | Biblioteca (usuários) |
| `http://localhost:8765/api/loans` | loan-service |
| `http://localhost:8765/api/avaliacoes` | avaliacao-service |

---

## Exemplos de Requisições

### Via API Gateway

```bash
# Listar livros
curl http://localhost:8765/api/books

# Listar avaliações
curl http://localhost:8765/api/avaliacoes

# Criar uma avaliação
curl -X POST http://localhost:8765/api/avaliacoes \
  -H "Content-Type: application/json" \
  -d '{"bookId": 1, "userId": 1, "nota": 5, "comentario": "Livro excelente"}'

# Criar um empréstimo
curl -X POST http://localhost:8765/api/loans \
  -H "Content-Type: application/json" \
  -d '{"bookId": 1, "userId": 1}'
```

### Acesso direto aos serviços (sem Gateway)

```bash
curl http://localhost:8080/book        # Biblioteca
curl http://localhost:8082/loan        # loan-service
curl http://localhost:8081/avaliacao   # avaliacao-service
```

---

## Testando a Resiliência (Circuit Breaker)

O loan-service protege a chamada ao serviço Biblioteca com um Circuit Breaker (Resilience4j).

1. Com toda a arquitetura no ar, verifique o estado do circuito:

   ```
   http://localhost:8082/actuator/circuitbreakers
   ```

   O estado inicial deve ser `CLOSED`.

2. **Derrube o serviço Biblioteca.**

3. Faça várias requisições de criação de empréstimo:

   ```bash
   curl -X POST http://localhost:8082/loan \
     -H "Content-Type: application/json" \
     -d '{"bookId": 1, "userId": 1}'
   ```

4. Após ultrapassar o limite de falhas, o estado do circuito muda para `OPEN`, e as chamadas passam a receber a resposta de fallback imediatamente, sem tentar contatar a Biblioteca.

5. **Religue a Biblioteca.** Após o tempo de espera, o circuito retorna a `CLOSED` automaticamente (recuperação).

---

## Estrutura de Repositórios

- **Código:** https://github.com/DiegoCruzInfnet/Microsservi-os-e-DevOps-com-Spring-Boot-e-Spring-Cloud-TP1.git
- **Configuração (lida pelo Config Server):** https://github.com/DiegoCruzInfnet/microservices-config.git