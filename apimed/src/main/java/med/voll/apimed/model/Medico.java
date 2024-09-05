package med.voll.apimed.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.apimed.dto.MedicoDTO;
import med.voll.apimed.dto.MedicoUpdateDTO;

@Entity
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    /*
        essa anotação faz com que um atributo fique numa classe separada mas no
        banco de dados ele considere sendo da mesma tabela. Então nesse caso a classe
        Endereco vai completar a de medico na hora do insert, pra isso o atributo q ira completar
        a classe precisa ser marcado com @Embedded e a classe desse atributo precisa ser marcada com
        @Embeddable
     */
    private Endereco endereco;

    private Boolean ativo;

    public Medico(MedicoDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(MedicoUpdateDTO data) {
        if (data.nome() != null) this.nome = data.nome();

        if (data.telefone() != null) this.telefone = data.telefone();

        if (data.endereco() != null) this.endereco.atualizarInformacoes(data.endereco());
    }

    public void delete() {
        this.ativo = false;
    }
}
