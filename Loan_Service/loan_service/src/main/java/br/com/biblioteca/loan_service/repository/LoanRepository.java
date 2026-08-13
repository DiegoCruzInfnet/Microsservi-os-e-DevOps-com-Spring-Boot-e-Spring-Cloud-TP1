package br.com.biblioteca.loan_service.repository;

import br.com.biblioteca.loan_service.model.Loan;
import br.com.biblioteca.loan_service.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByUserId(Long userId);
    List<Loan> findByBookId(Long bookId);
    List<Loan> findByDetailsDataDevolucaoLessThan(LocalDate data);
    boolean existsByBookIdAndDetailsDataDevolucaoGreaterThanEqual(Long bookId, LocalDate data);
    long countByUserIdAndDetailsStatusAndDetailsDataDevolucaoGreaterThanEqual(Long userId, LoanStatus status, LocalDate data);
}