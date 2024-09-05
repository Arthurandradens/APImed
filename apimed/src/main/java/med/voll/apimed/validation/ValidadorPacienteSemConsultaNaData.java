package med.voll.apimed.validation;

import med.voll.apimed.domain.ValidacaoExeption;
import med.voll.apimed.dto.AgendamentoDTO;
import med.voll.apimed.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemConsultaNaData implements ValidadorAgendamentoConsulta{
    @Autowired
    ConsultaRepository repository;
    public void validar(AgendamentoDTO dados){
    var abertura = dados.data().withHour(7);
    var fechamento = dados.data().withHour(18);

    var pacientePossuiOutraConsulta = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(),abertura,fechamento);
    if (pacientePossuiOutraConsulta){
        throw new ValidacaoExeption("Paciente já pussui uma consulta para está data");
    }
    }
}
