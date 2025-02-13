package br.org.santacasa.prontuario_api.util.validations;

import br.org.santacasa.prontuario_api.dto.pacienteDTO.PacienteCreateDTO;
import br.org.santacasa.prontuario_api.util.validations.validators.CpfValidator;
import br.org.santacasa.prontuario_api.util.validations.validators.NomeValidator;
import br.org.santacasa.prontuario_api.util.validations.validators.TelefoneValidator;
import br.org.santacasa.prontuario_api.repository.PacienteRepository;
import br.org.santacasa.prontuario_api.exceptions.custom.ValidationException;
import org.springframework.stereotype.Component;


@Component
public class PacienteValidator {

    private final PacienteRepository pacienteRepository;
    private final CpfValidator cpfValidator;
    private final NomeValidator nomeValidator;
    private final TelefoneValidator telefoneValidator;

    public PacienteValidator(PacienteRepository pacienteRepository,
                             CpfValidator cpfValidator,
                             NomeValidator nomeValidator,
                             TelefoneValidator telefoneValidator) {
        this.pacienteRepository = pacienteRepository;
        this.cpfValidator = cpfValidator;
        this.nomeValidator = nomeValidator;
        this.telefoneValidator = telefoneValidator;
    }

    public void validate(PacienteCreateDTO pacienteCreateDTO) {
        nomeValidator.validate(pacienteCreateDTO.getNome());
        cpfValidator.validate(pacienteCreateDTO.getCpf());
        telefoneValidator.validate(pacienteCreateDTO.getTelefone());

        // Validação adicional para verificar a unicidade do email
        validateEmailUniqueness(pacienteCreateDTO.getEmail());
    }

    private void validateEmailUniqueness(String email) {
        if (pacienteRepository.existsByEmail(email)) {
            throw new ValidationException("Email já cadastrado.", "EMAIL_DUPLICADO");
        }
    }
}
