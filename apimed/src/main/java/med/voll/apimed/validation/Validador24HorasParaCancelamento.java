package med.voll.apimed.validation;

import med.voll.apimed.domain.ValidacaoExeption;
import med.voll.apimed.model.Consulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class Validador24HorasParaCancelamento implements ValidadorCancelamentoConsulta{
    @Override
    public void validar(Consulta consulta) {
        var agora = LocalDateTime.now();
        if (Duration.between(consulta.getData(),agora).toHours() < 24){
            throw new ValidacaoExeption("É necessário ter 24 horas de antecedência para cancelar a consulta");
        }
    }
}
