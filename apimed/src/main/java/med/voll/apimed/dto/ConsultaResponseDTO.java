package med.voll.apimed.dto;

import med.voll.apimed.model.Consulta;

import java.time.LocalDateTime;

public record ConsultaResponseDTO(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
    public ConsultaResponseDTO(Consulta consulta) {
        this(consulta.getId(),consulta.getMedico().getId(),consulta.getPaciente().getId(),consulta.getData());
    }
}
