/**
 * @author higor.robinn on 06/02/2025.
 */

package br.org.santacasa.prontuario_api.service.paciente;

import br.org.santacasa.prontuario_api.dto.EnderecoDTO;
import br.org.santacasa.prontuario_api.dto.PacienteDTO;
import br.org.santacasa.prontuario_api.dto.PacienteUpdateDTO;
import br.org.santacasa.prontuario_api.exceptions.custom.ResourceNotFoundException;
import br.org.santacasa.prontuario_api.models.Endereco;
import br.org.santacasa.prontuario_api.models.Paciente;
import br.org.santacasa.prontuario_api.repository.PacienteRepository;
import br.org.santacasa.prontuario_api.service.endereco.EnderecoService;
import br.org.santacasa.prontuario_api.util.mapper.PacienteMapper;
// import br.org.santacasa.prontuario_api.util.validators.PacienteUpdateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteUpdateService {

    private final PacienteRepository pacienteRepository;
//    private final PacienteUpdateValidator pacienteUpdateValidator;
    private final PacienteMapper pacienteMapper;
    private final EnderecoService enderecoService;

    @Autowired
    public PacienteUpdateService(PacienteRepository pacienteRepository,
//                                 PacienteUpdateValidator pacienteUpdateValidator,
                                 PacienteMapper pacienteMapper,
                                 EnderecoService enderecoService) {
        this.pacienteRepository = pacienteRepository;
//        this.pacienteUpdateValidator = pacienteUpdateValidator;
        this.pacienteMapper = pacienteMapper;
        this.enderecoService = enderecoService;
    }

    @Transactional
    public PacienteDTO alterar(Long id, PacienteUpdateDTO pacienteDTO) {
        Paciente pacienteParaAlterar = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado!", id, "Paciente"));

        // Valida os novos dados do paciente
//        pacienteUpdateValidator.validate(pacienteDTO);

        pacienteParaAlterar.setNome(pacienteDTO.getNome());
        pacienteParaAlterar.setTelefone(pacienteDTO.getTelefone());
        pacienteParaAlterar.setEmail(pacienteDTO.getEmail());
        pacienteParaAlterar.setDataNascimento(pacienteDTO.getDataNascimento());

        // Atualiza endereço existente
        EnderecoDTO novoEnderecoDTO = pacienteDTO.getEndereco();
        Endereco enderecoAtualizado = enderecoService.salvarEndereco(novoEnderecoDTO);
        pacienteParaAlterar.setEndereco(enderecoAtualizado);

        Paciente pacienteSalvo = pacienteRepository.save(pacienteParaAlterar);
        return pacienteMapper.toDTO(pacienteSalvo);
    }
}
