package br.org.santacasa.prontuario_api.util.validations;

import br.org.santacasa.prontuario_api.dto.patient.PatientCreateDTO;
import br.org.santacasa.prontuario_api.util.validations.validators.patientValidators.CpfValidator;
import br.org.santacasa.prontuario_api.util.validations.validators.patientValidators.NomeValidator;
import br.org.santacasa.prontuario_api.util.validations.validators.patientValidators.TelefoneValidator;
import br.org.santacasa.prontuario_api.repository.PatientRepository;
import br.org.santacasa.prontuario_api.exceptions.custom.ValidationException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;


@Component
public class PatientValidation{

    private final PatientRepository patientRepository;
    private final CpfValidator cpfValidator;
    private final NomeValidator nomeValidator;
    private final TelefoneValidator telefoneValidator;
    private final AddressValidation addressValidation;

    public PatientValidation(PatientRepository patientRepository,
                             CpfValidator cpfValidator,
                             NomeValidator nomeValidator,
                             TelefoneValidator telefoneValidator,
                             AddressValidation addressValidation) {
        this.patientRepository = patientRepository;
        this.cpfValidator = cpfValidator;
        this.nomeValidator = nomeValidator;
        this.telefoneValidator = telefoneValidator;
        this.addressValidation = addressValidation;
    }

    public void validate(@Valid PatientCreateDTO patientCreateDTO) {
        addressValidation.validate(patientCreateDTO.getEndereco());
        nomeValidator.validate(patientCreateDTO.getNome());
        cpfValidator.validate(patientCreateDTO.getCpf());
        telefoneValidator.validate(patientCreateDTO.getTelefone());

        // Verificar unicidade do email
        validateEmailUniqueness(patientCreateDTO.getEmail());
    }

    private void validateEmailUniqueness(String email) {
        if (patientRepository.existsByEmail(email)) {
            throw new ValidationException("Email já cadastrado.", "EMAIL_DUPLICADO");
        }
    }
}
