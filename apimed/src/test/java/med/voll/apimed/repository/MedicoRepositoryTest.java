package med.voll.apimed.repository;

import med.voll.apimed.dto.EnderecoDTO;
import med.voll.apimed.dto.MedicoDTO;
import med.voll.apimed.dto.PacienteDTO;
import med.voll.apimed.model.Consulta;
import med.voll.apimed.model.Especialidade;
import med.voll.apimed.model.Medico;
import med.voll.apimed.model.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {
    @Autowired
    MedicoRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    @DisplayName("Unico Médico cadastrado não esta disponível, retorna null")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        // given or arrange
        var nextMonday = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);
        var medico = cadastrarMedico("medico","medico@gmail.com","123456",Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("paciente","paciente@gmail.com","12309828942");
        cadastrarConsulta(medico,paciente,nextMonday);

        //when or act
        var medicoNull = repository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA,nextMonday);

        //then or assert
        assertThat(medicoNull).isNull();
    }

    @Test
    @DisplayName("Devolve médico disponível na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        var nextMonday = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

      var medico = cadastrarMedico("medico","medico@gmail.com","123456",Especialidade.CARDIOLOGIA);

        var medicoLivre = repository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA,nextMonday);
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        entityManager.persist(new Consulta(null,paciente,medico, data,null));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        entityManager.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        entityManager.persist(paciente);
        return paciente;
    }

    private MedicoDTO dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new MedicoDTO(
                nome,
                email,
                "61999999999",
                crm,
                dadosEndereco(),
                especialidade

        );
    }

    private PacienteDTO dadosPaciente(String nome, String email, String cpf) {
        return new PacienteDTO(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private EnderecoDTO dadosEndereco() {
        return new EnderecoDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}