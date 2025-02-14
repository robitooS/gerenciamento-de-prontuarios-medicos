package br.org.santacasa.prontuario_api.service.paciente;

import br.org.santacasa.prontuario_api.dto.enderecoDTO.EnderecoDTO;
import br.org.santacasa.prontuario_api.dto.pacienteDTO.PacienteCreateDTO;
import br.org.santacasa.prontuario_api.exceptions.custom.ResourceNotFoundException;
import br.org.santacasa.prontuario_api.models.Endereco;
import br.org.santacasa.prontuario_api.models.Paciente;
import br.org.santacasa.prontuario_api.repository.PacienteRepository;
import br.org.santacasa.prontuario_api.service.endereco.EnderecoService;
import br.org.santacasa.prontuario_api.util.mapper.PacienteMapper;
import br.org.santacasa.prontuario_api.util.validations.PacienteUpdateValidator;
import br.org.santacasa.prontuario_api.util.validations.PacienteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService {

    private final PacienteUpdateValidator pacienteUpdateValidator;
    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;
    private final EnderecoService enderecoService;
    private final PacienteValidator pacienteValidator;

    @Autowired
    public PacienteService(PacienteUpdateValidator pacienteUpdateValidator,
                           PacienteRepository pacienteRepository,
                           PacienteMapper pacienteMapper,
                           EnderecoService enderecoService,
                           PacienteValidator pacienteValidator) {
        this.pacienteUpdateValidator = pacienteUpdateValidator;
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
        this.enderecoService = enderecoService;
        this.pacienteValidator = pacienteValidator;
    }

    @Transactional
    public PacienteResponseDTO criarPaciente(PacienteCreateDTO pacienteCreateDTO) {
        pacienteValidator.validate(pacienteCreateDTO);

        try {
            // Converte DTO para entidade e salva o endereço antes de associar ao paciente
            Paciente paciente = pacienteMapper.toEntity(pacienteCreateDTO);
            Endereco enderecoSalvo = enderecoService.salvarEndereco(pacienteCreateDTO.getEndereco());
            paciente.setEndereco(enderecoSalvo);

            // Salva o paciente no banco
            Paciente pacienteSalvo = pacienteRepository.save(paciente);
            return pacienteMapper.toDTO(pacienteSalvo);
        } catch (Exception e) {
            // Log do erro para depuração
            System.err.println("Erro ao criar paciente: " + e.getMessage());

            // Lançar RuntimeException para garantir rollback do @Transactional
            throw new RuntimeException("Erro ao salvar paciente.", e);
        }
    }

    @Transactional(readOnly = true)
    public PacienteResponseDTO getPacienteById(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado!", id, "Paciente"));
        return pacienteMapper.toDTO(paciente);
    }

    @Transactional(readOnly = true)
    public PacienteCreateDTO getPacienteByCpf(String cpf) {
        Paciente paciente = pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com CPF: " + cpf, null, "Paciente"));
        return pacienteMapper.toDTO(paciente);
    }

    @Transactional(readOnly = true)
    public Page<PacienteCreateDTO> getPacienteByNome(String nome, Pageable pageRequest) {
        Page<Paciente> pacientes = pacienteRepository.findByNome(nome, pageRequest);

        if (pacientes.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum paciente encontrado com o nome: " + nome, null, "Paciente");
        }
        return pacientes.map(pacienteMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Page<PacienteCreateDTO> listarTodos(PageRequest pageRequest) {
        return pacienteRepository.findAll(pageRequest)
                .map(pacienteMapper::toDTO);
    }

    @Transactional
    public void deletarPaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente não encontrado com ID: " + id, id, "Paciente");
        }
        pacienteRepository.deleteById(id);
    }
}
