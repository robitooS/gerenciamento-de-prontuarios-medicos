/**
 * @author higor.robinn on 21/01/2025.
 */

package br.org.santacasa.prontuario_api.controllers;

import br.org.santacasa.prontuario_api.dto.pacienteDTO.PacienteCreateDTO;
import br.org.santacasa.prontuario_api.exceptions.custom.ResourceNotFoundException;
import br.org.santacasa.prontuario_api.service.paciente.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<Page<PacienteCreateDTO>> listarPacientes(@RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(pacienteService.listarTodos(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<PacienteCreateDTO> criarPaciente(@Valid @RequestBody PacienteCreateDTO pacienteCreateDTO) {
        PacienteCreateDTO novoPaciente = pacienteService.criarPaciente(pacienteCreateDTO);

        return new ResponseEntity<>(novoPaciente, HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PacienteCreateDTO> getPacienteById(@PathVariable Long id) throws ResourceNotFoundException {
        PacienteCreateDTO paciente = pacienteService.getPacienteById(id);

        return ResponseEntity.ok(paciente);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Page<PacienteCreateDTO>> getPacienteByNome(
            @PathVariable String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<PacienteCreateDTO> pacientes = pacienteService.getPacienteByNome(nome, PageRequest.of(page, size));
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PacienteCreateDTO> getPacienteByCpf(@PathVariable String cpf) throws ResourceNotFoundException {
        PacienteCreateDTO paciente = pacienteService.getPacienteByCpf(cpf);

        return ResponseEntity.ok(paciente);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<PacienteCreateDTO> atualizarPaciente(@PathVariable Long id, @Valid @RequestBody PacienteCreateDTO pacienteDTO) throws ResourceNotFoundException {
//        PacienteCreateDTO pacienteAlterado = pacienteService.alterar(id, pacienteDTO);
//
//        return ResponseEntity.ok(pacienteAlterado);
//    }

}
