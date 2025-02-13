/**
 * @author higor.robinn on 13/02/2025.
 */

package br.org.santacasa.prontuario_api.util.mapper;

import br.org.santacasa.prontuario_api.dto.enderecoDTO.EnderecoDTO;
import br.org.santacasa.prontuario_api.models.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public EnderecoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Endereco toEntity(EnderecoDTO enderecoDTO) {
        return modelMapper.map(enderecoDTO, Endereco.class);
    }

    public EnderecoDTO toDTO(Endereco endereco) {
        return modelMapper.map(endereco, EnderecoDTO.class);
    }
}
