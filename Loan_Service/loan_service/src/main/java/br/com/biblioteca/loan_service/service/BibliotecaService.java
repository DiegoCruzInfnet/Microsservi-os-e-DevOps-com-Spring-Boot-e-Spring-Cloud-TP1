package br.com.biblioteca.loan_service.service;

import br.com.biblioteca.loan_service.client.BibliotecaClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BibliotecaService {

    private final BibliotecaClient bibliotecaClient;

    @CircuitBreaker(name = "biblioteca", fallbackMethod = "fallbackLivro")
    public void validarLivro(Long bookId) {
        bibliotecaClient.buscarLivro(bookId);
    }

    public void fallbackLivro(Long bookId, Throwable t) {
        throw new IllegalStateException(
                "Servico de Biblioteca indisponivel no momento. Nao foi possivel validar o livro. Tente novamente mais tarde.");
    }

    @CircuitBreaker(name = "biblioteca", fallbackMethod = "fallbackUsuario")
    public void validarUsuario(Long userId) {
        bibliotecaClient.buscarUsuario(userId);
    }

    public void fallbackUsuario(Long userId, Throwable t) {
        throw new IllegalStateException(
                "Servico de Biblioteca indisponivel no momento. Nao foi possivel validar o usuario. Tente novamente mais tarde.");
    }
}