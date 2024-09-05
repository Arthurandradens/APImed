package med.voll.apimed.repository;

import med.voll.apimed.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta,Long> {

//    @Query("""
//        select 1 from Consulta c
//        where
//        c.medico.id = :id and
//        c.data = :data
//""")
boolean existsByMedicoIdAndDataAndMotivoIsNull(Long idMedico, LocalDateTime data);

    boolean existsByPacienteIdAndDataBetween(Long id,LocalDateTime abertura, LocalDateTime fechamento);
}
