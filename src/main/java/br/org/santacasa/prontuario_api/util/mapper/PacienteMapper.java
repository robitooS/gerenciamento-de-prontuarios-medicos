/**
 * @author higor.robinn on 05/02/2025.
 */

package br.org.santacasa.prontuario_api.util.mapper;

import br.org.santacasa.prontuario_api.dto.pacienteDTO.PacienteCreateDTO;
import br.org.santacasa.prontuario_api.models.Paciente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {


    private final ModelMapper modelMapper;

    @Autowired
    public PacienteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PacienteCreateDTO toDTO(Paciente paciente) {
        return modelMapper.map(paciente, PacienteCreateDTO.class);
    }

    public Paciente toEntity(PacienteCreateDTO pacienteCreateDTO) {
        return modelMapper.map(pacienteCreateDTO, Paciente.class);
    }
}