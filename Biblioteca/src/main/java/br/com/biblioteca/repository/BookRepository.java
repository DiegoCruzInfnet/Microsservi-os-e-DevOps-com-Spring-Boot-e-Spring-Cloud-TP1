package br.com.biblioteca.repository;

import br.com.biblioteca.model.Book;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByDetailsTitulo(String titulo);
    List<Book> findByDetailsAutor(String autor);
    List<Book> findByDetailsEditora(String editora);
    List<Book> findByDetailsIsbn(String isbn);
}
