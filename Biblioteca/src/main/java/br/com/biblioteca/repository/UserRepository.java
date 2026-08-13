package br.com.biblioteca.repository;

import br.com.biblioteca.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByDetailsEmail(String email);
    List<User> findByDetailsNome(String nome);
    List<User> findByDetailsTelefone(String telefone);
}
