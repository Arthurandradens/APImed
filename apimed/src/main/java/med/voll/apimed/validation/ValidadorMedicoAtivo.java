package med.voll.apimed.validation;

import med.voll.apimed.domain.ValidacaoExeption;
import med.voll.apimed.dto.AgendamentoDTO;
import med.voll.apimed.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta{
    @Autowired
    private MedicoRepository repository;

    public void validar(AgendamentoDTO dados){
        if (dados.idMedico() == null) return;

        var medicoAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoAtivo){
            throw new ValidacaoExeption("Consulta não pode ser marcada com medico desativado");
        }
    }
}
