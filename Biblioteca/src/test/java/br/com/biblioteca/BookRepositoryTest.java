package br.com.biblioteca;

import br.com.biblioteca.model.Book;
import br.com.biblioteca.model.vo.BookDetails;
import br.com.biblioteca.repository.BookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    private Book book;

    @BeforeEach
    void setup() {
        BookDetails details = new BookDetails(
                "Clean Code", "Robert C. Martin",
                "978-0132350884", "Tecnologia", "Prentice Hall"
        );
        book = new Book(details);
    }

    @Test
    void deveSalvarLivro() {
        Book salvo = bookRepository.save(book);
        assertNotNull(salvo.getId());
    }
    @Test
    void deveBuscarLivroPorTitulo() {
        bookRepository.save(book);
        List<Book> livros = bookRepository.findByDetailsTitulo("Clean Code");
        assertFalse(livros.isEmpty());
    }

    @Test
    void deveBuscarLivroPorAutor() {
        bookRepository.save(book);
        List<Book> livros = bookRepository.findByDetailsAutor("Robert C. Martin");
        assertFalse(livros.isEmpty());
    }

    @Test
    void deveRetornarVazioQuandoLivroNaoExiste() {
        List<Book> livros = bookRepository.findByDetailsTitulo("Livro Inexistente");
        assertTrue(livros.isEmpty());
    }
}