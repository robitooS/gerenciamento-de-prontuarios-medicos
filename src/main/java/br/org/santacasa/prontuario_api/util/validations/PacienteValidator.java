package br.org.santacasa.prontuario_api.util.validations;

import br.org.santacasa.prontuario_api.dto.PacienteDTO;
import br.org.santacasa.prontuario_api.util.validations.validators.Validator;
import br.org.santacasa.prontuario_api.repository.PacienteRepository;
import br.org.santacasa.prontuario_api.exceptions.custom.ValidationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PacienteValidator {

    private final List<Validator<PacienteDTO>> validators;
    private final PacienteRepository pacienteRepository;

    public PacienteValidator(@Qualifier("pacienteValidators") List<Validator<PacienteDTO>> validators,
                             PacienteRepository pacienteRepository) {
        this.validators = validators;
        this.pacienteRepository = pacienteRepository;
    }

    public void validate(PacienteDTO pacienteDTO) {
        // Chama todos os validadores injetados dinamicamente pelo Spring
        validators.forEach(validator -> validator.validate(pacienteDTO));

        // Validação adicional para verificar a unicidade do email
        validateEmailUniqueness(pacienteDTO.getEmail());
    }

    private void validateEmailUniqueness(String email) {
        if (pacienteRepository.existsByEmail(email)) {
            throw new ValidationException("Email já cadastrado.", "EMAIL_DUPLICADO");
        }
    }
}
