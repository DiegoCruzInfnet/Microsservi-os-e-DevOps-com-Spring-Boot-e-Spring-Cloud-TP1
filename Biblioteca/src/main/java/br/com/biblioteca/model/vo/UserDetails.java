package br.com.biblioteca.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

    @NotBlank(message = "Informação obrigatória")
    @Size(max = 255, min = 2)
    @Column(nullable = false, length = 255)
    private String nome;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email obrigatório")
    @Column(nullable = false, length = 255)
    private String email;

    @NotBlank(message = "Informação obrigatória")
    @Size(max = 11, min = 11)
    @Column(nullable = false, length = 11)
    private String telefone;
}
