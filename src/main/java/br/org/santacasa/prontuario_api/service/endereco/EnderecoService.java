/**
 * @author higor.robinn on 29/01/2025.
 */

package br.org.santacasa.prontuario_api.service.endereco;

import br.org.santacasa.prontuario_api.dto.EnderecoDTO;
import br.org.santacasa.prontuario_api.models.Endereco;
import br.org.santacasa.prontuario_api.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository, ModelMapper modelMapper) {
        this.enderecoRepository = enderecoRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public Endereco salvarEndereco(@Valid EnderecoDTO enderecoDTO) {
        Endereco endereco = modelMapper.map(enderecoDTO, Endereco.class);
        return enderecoRepository.save(endereco);
    }
}
