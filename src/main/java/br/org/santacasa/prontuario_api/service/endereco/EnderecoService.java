/**
 * @author higor.robinn on 29/01/2025.
 */

package br.org.santacasa.prontuario_api.service.endereco;

import br.org.santacasa.prontuario_api.dto.enderecoDTO.EnderecoDTO;
import br.org.santacasa.prontuario_api.models.Endereco;
import br.org.santacasa.prontuario_api.repository.EnderecoRepository;
import br.org.santacasa.prontuario_api.util.mapper.EnderecoMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository, EnderecoMapper enderecoMapper) {
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
    }

    @Transactional
    public Endereco salvarEndereco(@Valid EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoMapper.toEntity(enderecoDTO);
        return enderecoRepository.save(endereco);
    }

    @Transactional
    public EnderecoDTO atualizarEndereco(@Valid EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoMapper.toEntity(enderecoDTO);
        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        return enderecoMapper.toDTO(enderecoSalvo);
    }
}
