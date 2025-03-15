/**
 * @author higor.robinn on 07/03/2025.
 */

package br.org.santacasa.prontuario_api.controllers;

import br.org.santacasa.prontuario_api.dto.patient.PatientCreateDTO;
import br.org.santacasa.prontuario_api.dto.patient.PatientResponseDTO;
import br.org.santacasa.prontuario_api.dto.patient.PatientUpdateDTO;
import br.org.santacasa.prontuario_api.service.paciente.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/pacientes")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PreAuthorize("hasAnyRole('MEDICO', 'ADMIN')")
    @PostMapping("/criar")
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientCreateDTO patientCreateDTO) {
        PatientResponseDTO patientCreated = patientService.createPatient(patientCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientCreated);
    }

    @PreAuthorize("hasAnyRole('MEDICO', 'ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<Page<PatientResponseDTO>> listPatients(@RequestParam (defaultValue = "0") int page,
                                                                 @RequestParam (defaultValue = "10") int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Os parâmetros 'page' e 'size' devem ser números positivos.");
        }

        Page<PatientResponseDTO> patients = patientService.listAll(PageRequest.of(page, size));
        return ResponseEntity.ok(patients);
    }

    @PutMapping("/alterar/{id}")
    @PreAuthorize("hasAnyRole('MEDICO', 'ADMIN')")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable Long id,
                                                            @Valid @RequestBody PatientUpdateDTO patientUpdateDTO) {

        PatientResponseDTO patientResponseDTO = patientService.atualizarPaciente(id, patientUpdateDTO);
        return ResponseEntity.ok(patientResponseDTO);
    }

    @PreAuthorize("hasAnyRole('MEDICO', 'ADMIN')")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {

        patientService.deletarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
