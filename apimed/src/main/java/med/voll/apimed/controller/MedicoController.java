package med.voll.apimed.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.apimed.dto.MedicoDTO;
import med.voll.apimed.dto.MedicoListagemDTO;
import med.voll.apimed.dto.MedicoResponseDTO;
import med.voll.apimed.dto.MedicoUpdateDTO;
import med.voll.apimed.model.Medico;
import med.voll.apimed.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {
    @Autowired
    MedicoRepository repository;

    @GetMapping
    public ResponseEntity<Page<MedicoListagemDTO>> index(@PageableDefault(size = 10,sort = {"nome"}) Pageable paginacao){

        var page = repository.findAllByAtivoTrue(paginacao)
                .map(MedicoListagemDTO::new);

        return ResponseEntity.ok(page);

    }

    @PostMapping
    @Transactional
    public ResponseEntity store(@RequestBody @Valid MedicoDTO json, UriComponentsBuilder builder){
        var medico = new Medico(json);
        repository.save(medico);

        var uri = builder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicoResponseDTO(medico));
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity show(@PathVariable Long id){
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new MedicoResponseDTO(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid MedicoUpdateDTO data){
        var medico = repository.getReferenceById(data.id());
        medico.atualizarInformacoes(data);

        return ResponseEntity.ok(new MedicoResponseDTO(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
            var medico = repository.getReferenceById(id);
            medico.delete();
            return ResponseEntity.noContent().build();
    }
}
