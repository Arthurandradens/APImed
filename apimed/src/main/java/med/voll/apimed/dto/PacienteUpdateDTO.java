package med.voll.apimed.dto;

import jakarta.validation.constraints.NotNull;

public record PacienteUpdateDTO(
        String nome,
        String telefone,
        EnderecoDTO endereco
) {
}
