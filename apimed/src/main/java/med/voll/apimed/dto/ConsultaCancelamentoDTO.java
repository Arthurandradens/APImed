package med.voll.apimed.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.apimed.model.Consulta;
import med.voll.apimed.model.MotivoCancelamento;

public record ConsultaCancelamentoDTO(
        @NotNull
        Long id,
        @NotNull
        MotivoCancelamento motivo
) {
}
