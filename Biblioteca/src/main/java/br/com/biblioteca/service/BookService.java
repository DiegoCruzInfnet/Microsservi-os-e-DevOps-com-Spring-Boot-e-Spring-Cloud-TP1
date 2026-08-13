package br.com.biblioteca.service;

import br.com.biblioteca.model.Book;
import br.com.biblioteca.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findByAutor(String author) {
        return bookRepository.findByDetailsAutor(author);
    }

    public List<Book> findByEditora(String editora) {
        return bookRepository.findByDetailsEditora(editora);
    }

    public List<Book> findByTitulo(String titulo) {
        return bookRepository.findByDetailsTitulo(titulo);
    }

    public List<Book> findByIsbn(String isbn) {
        return bookRepository.findByDetailsIsbn(isbn);
    }
}
