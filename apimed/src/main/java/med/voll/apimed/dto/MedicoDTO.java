package med.voll.apimed.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.apimed.model.Endereco;
import med.voll.apimed.model.Especialidade;

public record MedicoDTO(

        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,

        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}",message = "Formato Inválido") //valida que tem de 4 a 6 digitos
        String crm,
        @NotNull
        EnderecoDTO endereco,
        @NotNull
        Especialidade especialidade
) {
}
