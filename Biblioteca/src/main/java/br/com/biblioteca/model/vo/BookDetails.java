package br.com.biblioteca.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookDetails {

    @NotBlank(message = "Informação obrigatória")
    @Size(max = 255, min = 2)
    @Column(nullable = false, length = 255)
    private String titulo;

    @NotBlank(message = "Informação obrigatória")
    @Size(max = 255, min = 2)
    @Column(nullable = false, length = 255)
    private String autor;


    @NotBlank(message = "ISBN é obrigatório")
    @Size(max = 20)
    @Column(nullable = false, unique = true, length = 20)
    private String isbn;

    @Size(max = 100)
    @Column(length = 255)
    private String genero;

    @Size(max = 100)
    @Column(length = 255)
    private String editora;
}
