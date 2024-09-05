package med.voll.apimed.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.apimed.dto.MedicoUpdateDTO;
import med.voll.apimed.dto.PacienteDTO;
import med.voll.apimed.dto.PacienteUpdateDTO;

@Entity
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    public String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private boolean ativo;


    public Paciente(PacienteDTO paciente) {
        this.nome = paciente.nome();
        this.email = paciente.email();
        this.cpf = paciente.cpf();
        this.telefone = paciente.telefone();
        this.endereco = new Endereco(paciente.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(PacienteUpdateDTO data) {
        if (data.nome() != null) this.nome = data.nome();

        if (data.telefone() != null) this.telefone = data.telefone();

        if (data.endereco() != null) this.endereco.atualizarInformacoes(data.endereco());
    }

    public void delete() {
        this.ativo = false;
    }
}
