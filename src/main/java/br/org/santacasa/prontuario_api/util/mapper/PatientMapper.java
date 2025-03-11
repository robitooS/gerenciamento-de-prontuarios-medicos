/**
 * @author higor.robinn on 05/02/2025.
 */

package br.org.santacasa.prontuario_api.util.mapper;

import br.org.santacasa.prontuario_api.dto.patient.PatientCreateDTO;
import br.org.santacasa.prontuario_api.dto.patient.PatientResponseDTO;
import br.org.santacasa.prontuario_api.dto.patient.PatientUpdateDTO;
import br.org.santacasa.prontuario_api.models.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientResponseDTO toResponseDTO(Patient patient);

    Patient toEntity(PatientCreateDTO patientCreateDTO);

    // Atualiza uma entidade Paciente com os dados do DTO de atualização
    void updatePatientFromDTO(PatientUpdateDTO dto, @MappingTarget Patient patient);
}