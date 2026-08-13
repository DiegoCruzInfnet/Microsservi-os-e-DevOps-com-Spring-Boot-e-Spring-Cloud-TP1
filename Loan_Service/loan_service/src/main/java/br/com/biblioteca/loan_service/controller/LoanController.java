package br.com.biblioteca.loan_service.controller;

import br.com.biblioteca.loan_service.model.Loan;
import br.com.biblioteca.loan_service.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class LoanController {

    private final LoanService loanService;

    @GetMapping
    public List<Loan> findAll() {
        return loanService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> findById(@PathVariable Long id) {
        return loanService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<Loan> findByUserId(@PathVariable Long userId) {
        return loanService.findByUserId(userId);
    }

    @GetMapping("/book/{bookId}")
    public List<Loan> findByBookId(@PathVariable Long bookId) {
        return loanService.findByBookId(bookId);
    }

    @GetMapping("/vencidos")
    public List<Loan> findVencidos() {
        return loanService.findEmprestimosVencidos();
    }

    @PostMapping
    public ResponseEntity<Loan> save(@RequestBody Loan loan) {
        return ResponseEntity.ok(loanService.save(loan));
    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity<Loan> devolver(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(loanService.devolver(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loanService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}