package med.voll.apimed.validation;

import med.voll.apimed.domain.ValidacaoExeption;
import med.voll.apimed.dto.AgendamentoDTO;
import med.voll.apimed.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta{
    @Autowired
    PacienteRepository repository;
    public void validar(AgendamentoDTO dados){

        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteEstaAtivo){
            throw new ValidacaoExeption("Consulta não pode ser marcada para paciente desativado");
        }
    }
}
