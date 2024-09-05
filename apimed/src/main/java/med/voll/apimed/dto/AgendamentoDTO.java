package med.voll.apimed.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.apimed.model.Especialidade;

import java.time.LocalDateTime;

public record AgendamentoDTO(
        @JsonAlias(value = "medico_id")
        Long idMedico,
        @NotNull
        @JsonAlias(value = "paciente_id")
        Long idPaciente,
        @NotNull
        @Future
        LocalDateTime data,
        Especialidade especialidade

) {
}
