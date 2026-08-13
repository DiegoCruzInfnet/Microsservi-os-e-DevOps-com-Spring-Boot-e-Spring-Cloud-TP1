package br.com.biblioteca.controller;

import br.com.biblioteca.model.Book;
import br.com.biblioteca.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> save(@Valid @RequestBody Book book) {
        return ResponseEntity.ok(bookService.save(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id,@Valid @RequestBody Book book) {
        return bookService.findById(id)
                .map(existing -> ResponseEntity.ok(bookService.save(book)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")
    public List<Book> findByTitulo(@PathVariable String titulo) {
        return bookService.findByTitulo(titulo);
    }

    @GetMapping("/autor/{autor}")
    public List<Book> findByAutor(@PathVariable String autor) {
        return bookService.findByAutor(autor);
    }

    @GetMapping("/editora/{editora}")
    public List<Book> findByEditora(@PathVariable String editora) {
        return bookService.findByEditora(editora);
    }

    @GetMapping("/isbn/{isbn}")
    public List<Book> findByIsbn(@PathVariable String isbn) {
        return bookService.findByIsbn(isbn);
    }
}
