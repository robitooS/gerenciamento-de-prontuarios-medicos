package br.org.santacasa.prontuario_api.service.paciente;

import br.org.santacasa.prontuario_api.dto.patient.PatientCreateDTO;
import br.org.santacasa.prontuario_api.dto.patient.PatientResponseDTO;
import br.org.santacasa.prontuario_api.dto.patient.PatientUpdateDTO;
import br.org.santacasa.prontuario_api.exceptions.custom.ResourceNotFoundException;
import br.org.santacasa.prontuario_api.models.Address;
import br.org.santacasa.prontuario_api.models.Patient;
import br.org.santacasa.prontuario_api.repository.PatientRepository;
import br.org.santacasa.prontuario_api.util.mapper.AddressMapper;
import br.org.santacasa.prontuario_api.util.mapper.PatientMapper;
import br.org.santacasa.prontuario_api.util.validations.PatientValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService{

    private final PatientRepository patientRepository;
    @Autowired private final PatientMapper patientMapper;
    private final PatientValidation patientValidation;
    private final AddressMapper addressMapper;

    @Autowired
    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper, PatientValidation patientValidation, AddressMapper addressMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
        this.patientValidation = patientValidation;
        this.addressMapper = addressMapper;
    }

    @Transactional
    public PatientResponseDTO createPatient(@Valid PatientCreateDTO patientCreateDTO) {
        patientValidation.validate(patientCreateDTO);

        try {
            Patient patient = patientMapper.toEntity(patientCreateDTO);
            Patient patientSalvo = patientRepository.save(patient);

            return patientMapper.toResponseDTO(patientSalvo);
        } catch (Exception e) {
            // Log do erro para depuração
            System.err.println("Erro ao criar paciente: " + e.getMessage());

            // Lançar RuntimeException para garantir rollback do @Transactional
            throw new RuntimeException("Erro ao salvar paciente.", e);
        }
    }

    @Transactional(readOnly = true)
    public PatientResponseDTO getPacienteById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado", "id", "PACIENTE_NOT_FOUND"));
        return patientMapper.toResponseDTO(patient);
    }

    @Transactional(readOnly = true)
    public PatientResponseDTO getPacienteByCpf(String cpf) {
        Patient patient = patientRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado", "cpf", "PACIENTE_NOT_FOUND"));
        return patientMapper.toResponseDTO(patient);
    }

    @Transactional(readOnly = true)
    public Page<PatientResponseDTO> getPacienteByNome(String nome, Pageable pageRequest) {
        Page<Patient> pacientes = patientRepository.findByNome(nome, pageRequest);

        if (pacientes.isEmpty()) {
            throw new ResourceNotFoundException("Paciente(s) não encontrado(s) com o(s) nome(s): ", "nome", "PACIENTE_NOT_FOUND");
        }
        return pacientes.map(patientMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public Page<PatientResponseDTO> listarTodos(PageRequest pageRequest) {
        return patientRepository.findAll(pageRequest)
                .map(patientMapper::toResponseDTO);
    }

    @Transactional
    public PatientResponseDTO atualizarPaciente (Long id, @Valid PatientUpdateDTO patientUpdateDTO){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado", "id", "PACIENTE_NOT_FOUND"));
        Address address = addressMapper.toEntity(patientUpdateDTO.getEndereco());

        if (patientUpdateDTO.getNome() != null) {
            patient.setNome(patientUpdateDTO.getNome());
        }
        if (patientUpdateDTO.getEmail() != null) {
            patient.setEmail(patientUpdateDTO.getEmail());
        }
        if (patientUpdateDTO.getDataNascimento() != null) {
            patient.setDataNascimento(patientUpdateDTO.getDataNascimento());
        }
        if (patientUpdateDTO.getTelefone() != null) {
            patient.setTelefone(patientUpdateDTO.getTelefone());
        }
        if(patientUpdateDTO.getEndereco() != null) {
            patient.getAddress().setLogradouro(address.getLogradouro());
            patient.getAddress().setCep(address.getCep());
            patient.getAddress().setBairro(address.getBairro());
            patient.getAddress().setEstado(address.getEstado());
            patient.getAddress().setCidade(address.getCidade());
            patient.getAddress().setComplemento(address.getComplemento());
            patient.getAddress().setNumero(address.getNumero());
        }

        Patient patientSaved = patientRepository.save(patient);
        return patientMapper.toResponseDTO(patientSaved);
    }

    @Transactional
    public void deletarPaciente(Long id) {

        Patient patient = patientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado", "id", "PACIENTE_NOT_FOUND"));
        patientRepository.deleteById(id);
    }
}
