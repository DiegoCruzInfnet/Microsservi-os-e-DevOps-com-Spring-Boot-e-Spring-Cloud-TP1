package br.com.biblioteca.loan_service.model.vo;

import br.com.biblioteca.loan_service.model.LoanStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Embeddable
@Getter
public class LoanDetails {

    @NotNull(message = "Data de empréstimo obrigatória")
    @Column(nullable = false)
    private LocalDate dataEmprestimo;

    @NotNull(message = "Data de devolução obrigatória")
    @Column(nullable = false)
    private LocalDate dataDevolucao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;

    public LoanDetails() {
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = LocalDate.now().plusDays(30);
        this.status = LoanStatus.ATIVO;
    }

    public LoanDetails(LocalDate dataEmprestimo, LocalDate dataDevolucao, LoanStatus status) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }
}