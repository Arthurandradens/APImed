package med.voll.apimed.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.apimed.dto.*;
import med.voll.apimed.model.Paciente;
import med.voll.apimed.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {
    @Autowired
    PacienteRepository repository;
    @GetMapping
    public ResponseEntity<Page<PacienteListagemDTO>> index(@PageableDefault(size = 10,sort = {"nome"}) Pageable paginacao){

        var page = repository.findAllByAtivoTrue(paginacao)
                .map(PacienteListagemDTO::new);

        return ResponseEntity.ok(page);
    }
    @PostMapping
    @Transactional
    public ResponseEntity store(@RequestBody PacienteDTO pacienteDTO, UriComponentsBuilder builder){
        try{
            var paciente = new Paciente(pacienteDTO);
            repository.save(paciente);
            var uri = builder.path("/medicos/{id}").buildAndExpand(paciente.getId()).toUri();

            return ResponseEntity.created(uri).body(new PacienteResponseDTO(paciente));
        }catch (NullPointerException exception){
           return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity show(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);

        return ResponseEntity.ok(new PacienteResponseDTO(paciente));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@RequestBody @Valid PacienteUpdateDTO data, @PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.atualizarInformacoes(data);

        return ResponseEntity.ok(new PacienteResponseDTO(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);

        paciente.delete();

        return ResponseEntity.noContent().build();
    }


}
