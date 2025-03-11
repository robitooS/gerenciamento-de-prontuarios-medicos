/**
 * @author higor.robinn on 07/03/2025.
 */

package br.org.santacasa.prontuario_api.controllers;

import br.org.santacasa.prontuario_api.dto.patient.PatientCreateDTO;
import br.org.santacasa.prontuario_api.dto.patient.PatientResponseDTO;
import br.org.santacasa.prontuario_api.service.paciente.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pacientes")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PreAuthorize("hasAnyRole('MEDICO', 'ADMIN')")
    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientCreateDTO patientCreateDTO) {
        PatientResponseDTO patientCreated = patientService.createPatient(patientCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientCreated);
    }


}
