package med.voll.apimed.validation;

import med.voll.apimed.dto.AgendamentoDTO;
import med.voll.apimed.model.Consulta;

public interface ValidadorCancelamentoConsulta {
    void validar(Consulta consulta);
}
