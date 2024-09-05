package med.voll.apimed.validation;

import med.voll.apimed.domain.ValidacaoExeption;
import med.voll.apimed.dto.AgendamentoDTO;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;
@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta{
    public void validar(AgendamentoDTO dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAbertura = dataConsulta.getHour() < 7;
        var depoisDoEncerramento = dataConsulta.getHour() > 18;

        if (domingo || antesDaAbertura || depoisDoEncerramento) throw new ValidacaoExeption("Consulta fora do horário de fuincionamento");
    }
}
