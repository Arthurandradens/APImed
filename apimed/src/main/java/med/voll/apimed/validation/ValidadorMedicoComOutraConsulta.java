package med.voll.apimed.validation;

import med.voll.apimed.domain.ValidacaoExeption;
import med.voll.apimed.dto.AgendamentoDTO;
import med.voll.apimed.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsulta implements ValidadorAgendamentoConsulta{
    @Autowired
    ConsultaRepository repository;
    public void validar(AgendamentoDTO dados){

        var medicoPossuiConsultaNesseHorario = repository.existsByMedicoIdAndDataAndMotivoIsNull(dados.idMedico(),dados.data());
        if (medicoPossuiConsultaNesseHorario){
            throw new ValidacaoExeption("O médico não está disponível nesse horário");
        }
    }
}
