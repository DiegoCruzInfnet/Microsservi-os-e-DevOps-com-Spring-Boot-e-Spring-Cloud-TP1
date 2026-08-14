package br.com.biblioteca.avaliacao_service.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "avaliacoes")
@Getter
@NoArgsConstructor
public class Avaliacao {

    @Id
    private String id;

    @NotNull
    private Long bookId;

    @NotNull
    private Long userId;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer nota;

    private String comentario;

    @CreatedDate
    private LocalDateTime criadoEm;

    public Avaliacao(Long bookId, Long userId, Integer nota, String comentario) {
        this.bookId = bookId;
        this.userId = userId;
        this.nota = nota;
        this.comentario = comentario;
    }
}