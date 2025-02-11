/**
 * @author higor.robinn on 21/01/2025.
 */

package br.org.santacasa.prontuario_api.controllers;

import br.org.santacasa.prontuario_api.dto.PacienteDTO;
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
    public ResponseEntity<Page<PacienteDTO>> listarPacientes(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(pacienteService.listarTodos(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<PacienteDTO> criarPaciente(@Valid @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO novoPaciente = pacienteService.criarPaciente(pacienteDTO);

        return new ResponseEntity<>(novoPaciente, HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PacienteDTO> getPacienteById(@PathVariable Long id) throws ResourceNotFoundException {
        PacienteDTO paciente = pacienteService.getPacienteById(id);

        return ResponseEntity.ok(paciente);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Page<PacienteDTO>> getPacienteByNome(
            @PathVariable String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<PacienteDTO> pacientes = pacienteService.getPacienteByNome(nome, PageRequest.of(page, size));
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PacienteDTO> getPacienteByCpf(@PathVariable String cpf) throws ResourceNotFoundException {
        PacienteDTO paciente = pacienteService.getPacienteByCpf(cpf);

        return ResponseEntity.ok(paciente);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<PacienteDTO> atualizarPaciente(@PathVariable Long id, @Valid @RequestBody PacienteDTO pacienteDTO) throws ResourceNotFoundException {
//        PacienteDTO pacienteAlterado = pacienteService.alterar(id, pacienteDTO);
//
//        return ResponseEntity.ok(pacienteAlterado);
//    }

}
