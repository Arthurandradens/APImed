package med.voll.apimed.dto;

import med.voll.apimed.model.Endereco;
import med.voll.apimed.model.Especialidade;
import med.voll.apimed.model.Medico;
import med.voll.apimed.model.Paciente;

public record PacienteResponseDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        Endereco endereco
) {
    public PacienteResponseDTO(Paciente paciente) {
        this(paciente.getId(),paciente.getNome(),paciente.getEmail(), paciente.getCpf(), paciente.getEndereco());
    }
}
