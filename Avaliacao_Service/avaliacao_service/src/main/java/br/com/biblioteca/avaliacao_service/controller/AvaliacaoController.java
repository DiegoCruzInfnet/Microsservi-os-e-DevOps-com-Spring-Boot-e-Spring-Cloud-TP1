package br.com.biblioteca.avaliacao_service.controller;

import br.com.biblioteca.avaliacao_service.model.Avaliacao;
import br.com.biblioteca.avaliacao_service.service.AvaliacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacao")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @GetMapping
    public List<Avaliacao> findAll() {
        return avaliacaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> findById(@PathVariable String id) {
        return avaliacaoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/livro/{bookId}")
    public List<Avaliacao> findByBookId(@PathVariable Long bookId) {
        return avaliacaoService.findByBookId(bookId);
    }

    @GetMapping("/usuario/{userId}")
    public List<Avaliacao> findByUserId(@PathVariable Long userId) {
        return avaliacaoService.findByUserId(userId);
    }

    @PostMapping
    public ResponseEntity<Avaliacao> save(@Valid @RequestBody Avaliacao avaliacao) {
        return ResponseEntity.ok(avaliacaoService.save(avaliacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        avaliacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}