package br.com.biblioteca.avaliacao_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Biblioteca")
public interface BibliotecaClient {

    @GetMapping("/book/{id}")
    Object buscarLivro(@PathVariable Long id);

    @GetMapping("/user/{id}")
    Object buscarUsuario(@PathVariable Long id);
}
