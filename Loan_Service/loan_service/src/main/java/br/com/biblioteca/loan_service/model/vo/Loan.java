package br.com.biblioteca.loan_service.model;

import br.com.biblioteca.loan_service.model.vo.LoanDetails;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private LoanDetails details;

    private Long bookId;
    private Long userId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Loan() {
        this.details = new LoanDetails();
    }

    public Loan(Long bookId, Long userId) {
        this.details = new LoanDetails();
        this.bookId = bookId;
        this.userId = userId;
    }

    public void devolver() {
        this.details = new LoanDetails(
                details.getDataEmprestimo(),
                details.getDataDevolucao(),
                LoanStatus.DEVOLVIDO
        );
    }
}