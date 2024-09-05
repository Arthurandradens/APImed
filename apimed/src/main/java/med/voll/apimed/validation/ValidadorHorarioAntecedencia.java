package med.voll.apimed.validation;

import med.voll.apimed.domain.ValidacaoExeption;
import med.voll.apimed.dto.AgendamentoDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsulta{

    public void validar(AgendamentoDTO dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();

        if (Duration.between(agora,dataConsulta).toMinutes() < 30 ) {
            throw new ValidacaoExeption("É necessário marcar a consulta com pelo menos 30 minutos de antecedência");
        }
    }
}
