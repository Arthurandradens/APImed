package med.voll.apimed.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.apimed.dto.AgendamentoDTO;
import med.voll.apimed.dto.ConsultaCancelamentoDTO;
import med.voll.apimed.service.AgendaDeConsultasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {
    @Autowired
    AgendaDeConsultasService service;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid AgendamentoDTO dados){
       var consulta = service.agendar(dados);
        return ResponseEntity.ok(consulta);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelarConsulta( @RequestBody @Valid ConsultaCancelamentoDTO dados){
        service.cancelaConsulta(dados);
        return ResponseEntity.noContent().build();
    }
}
