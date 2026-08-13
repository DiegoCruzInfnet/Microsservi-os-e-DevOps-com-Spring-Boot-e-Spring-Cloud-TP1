package br.com.biblioteca.loan_service;

import br.com.biblioteca.loan_service.model.Loan;
import br.com.biblioteca.loan_service.model.LoanStatus;
import br.com.biblioteca.loan_service.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class LoanRepositoryTest {

    @Autowired
    private LoanRepository loanRepository;

    private Loan loan;

    @BeforeEach
    public void setUp() {
        loan = new Loan(1L, 1L);
    }

    @Test
    public void deveSalvarLoan() {
        Loan salvo = loanRepository.save(loan);
        assertNotNull(salvo.getId());
    }

    @Test
    public void deveBuscarLoanById() {
        Loan salvo = loanRepository.save(loan);
        Optional<Loan> found = loanRepository.findById(salvo.getId());
        assertTrue(found.isPresent());
    }

    @Test
    public void deveBuscarLoanPorUserId() {
        loanRepository.save(loan);
        List<Loan> loans = loanRepository.findByUserId(1L);
        assertFalse(loans.isEmpty());
    }

    @Test
    public void deveBuscarLoanPorBookId() {
        loanRepository.save(loan);
        List<Loan> loans = loanRepository.findByBookId(1L);
        assertFalse(loans.isEmpty());
    }

    @Test
    public void deveVerificarLivroEmprestado() {
        loanRepository.save(loan);
        boolean emprestado = loanRepository
                .existsByBookIdAndDetailsDataDevolucaoGreaterThanEqual(1L, LocalDate.now());
        assertTrue(emprestado);
    }

    @Test
    public void deveContarEmprestimosAtivos() {
        loanRepository.save(loan);
        long qtd = loanRepository
                .countByUserIdAndDetailsStatusAndDetailsDataDevolucaoGreaterThanEqual(
                        1L, LoanStatus.ATIVO, LocalDate.now());
        assertTrue(qtd >= 1);
    }

    @Test
    public void deveRetornarVazioQuandoNaoExiste() {
        List<Loan> loans = loanRepository.findByUserId(999L);
        assertTrue(loans.isEmpty());
    }
}