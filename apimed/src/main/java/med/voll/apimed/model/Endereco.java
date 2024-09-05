package med.voll.apimed.model;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.apimed.dto.EnderecoDTO;


@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(EnderecoDTO dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void atualizarInformacoes(EnderecoDTO data) {
        if (data.logradouro() != null) this.logradouro = data.logradouro();

        if (data.bairro() != null) this.bairro = data.bairro();

        if (data.cep() != null) this.cep = data.cep();

        if (data.numero() != null) this.numero = data.numero();

        if (data.complemento() != null) this.complemento = data.complemento();

        if (data.cidade() != null) this.cidade = data.cidade();

        if (data.uf() != null) this.uf = data.uf();
    }
}
