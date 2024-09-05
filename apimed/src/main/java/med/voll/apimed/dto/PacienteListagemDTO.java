package med.voll.apimed.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.apimed.model.Paciente;
import org.hibernate.validator.constraints.br.CPF;

public record PacienteListagemDTO(
        Long id,
        String nome,
        String email,
        String cpf
) {

    public PacienteListagemDTO(Paciente paciente){
        this(paciente.getId(), paciente.getNome(),paciente.getEmail(),paciente.getCpf());
    }
}
