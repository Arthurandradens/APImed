package med.voll.apimed.service;

import med.voll.apimed.domain.ValidacaoExeption;
import med.voll.apimed.dto.AgendamentoDTO;
import med.voll.apimed.dto.ConsultaCancelamentoDTO;
import med.voll.apimed.dto.ConsultaResponseDTO;
import med.voll.apimed.model.Consulta;
import med.voll.apimed.model.Medico;
import med.voll.apimed.repository.ConsultaRepository;
import med.voll.apimed.repository.MedicoRepository;
import med.voll.apimed.repository.PacienteRepository;
import med.voll.apimed.validation.ValidadorAgendamentoConsulta;
import med.voll.apimed.validation.ValidadorCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendaDeConsultasService {
    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorAgendamentoConsulta> validadores;
    @Autowired
    private List<ValidadorCancelamentoConsulta> validadoresCancelamento;
    public ConsultaResponseDTO agendar(AgendamentoDTO dados){
        if (!pacienteRepository.existsById(dados.idPaciente())) throw new ValidacaoExeption("ID do paciente informado não existe");

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) throw new ValidacaoExeption("ID do médico informado não existe");

        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        if (medico == null) throw new ValidacaoExeption("Não existe médico disponível para essa data");

        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var consulta = new Consulta(null,paciente,medico,dados.data(),null);
        consultaRepository.save(consulta);

        return new ConsultaResponseDTO(consulta);
    }

    private Medico escolherMedico(AgendamentoDTO dados) {
        Medico medico;
        if (dados.idMedico() != null){
             medico = medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) throw new ValidacaoExeption("Especialidade é obrigatória quando nenhum médico é selecionado");

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(),dados.data());
    }

    public void cancelaConsulta(ConsultaCancelamentoDTO dados) {
        if (!consultaRepository.existsById(dados.id())) {
            throw new ValidacaoExeption("Id da consulta informado não existe!");
        }
        var consulta = consultaRepository.getReferenceById(dados.id());
        validadoresCancelamento.forEach(v -> v.validar(consulta));
        consulta.cancelar(dados.motivo());
    }
}
