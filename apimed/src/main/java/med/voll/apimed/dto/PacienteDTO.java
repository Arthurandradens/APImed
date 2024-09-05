package med.voll.apimed.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.apimed.model.Endereco;
import med.voll.apimed.model.Paciente;
import org.hibernate.validator.constraints.br.CPF;

public record PacienteDTO(
        @NotBlank
        @Pattern(regexp = "/(https?:\\/\\/|www\\.)/i")
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotNull
        @Valid
        EnderecoDTO endereco
) {
}
