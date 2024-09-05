package med.voll.apimed.dto;

import med.voll.apimed.model.Endereco;
import med.voll.apimed.model.Especialidade;
import med.voll.apimed.model.Medico;

public record MedicoResponseDTO(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        Endereco endereco
) {
    public MedicoResponseDTO(Medico medico) {
        this(medico.getId(),medico.getNome(),medico.getEmail(),medico.getCrm(),medico.getTelefone(), medico.getEspecialidade(),medico.getEndereco());
    }
}
