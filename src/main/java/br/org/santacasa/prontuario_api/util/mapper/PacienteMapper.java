/**
 * @author higor.robinn on 05/02/2025.
 */

package br.org.santacasa.prontuario_api.util.mapper;

import br.org.santacasa.prontuario_api.dto.PacienteDTO;
import br.org.santacasa.prontuario_api.models.Paciente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PacienteDTO toDTO(Paciente paciente) {
        return modelMapper.map(paciente, PacienteDTO.class);
    }

    public Paciente toEntity(PacienteDTO pacienteDTO) {
        return modelMapper.map(pacienteDTO, Paciente.class);
    }
}