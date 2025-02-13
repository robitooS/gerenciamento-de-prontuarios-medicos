package br.org.santacasa.prontuario_api.util.validations.validators;

import br.org.santacasa.prontuario_api.exceptions.custom.ValidationException;
import br.org.santacasa.prontuario_api.repository.PacienteRepository;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class CpfValidator implements Validator<String> {

    private static final Pattern CPF_PATTERN = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");

    private final PacienteRepository pacienteRepository;

    public CpfValidator(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public void validate(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new ValidationException("O CPF não pode estar em branco.", "CPF_VAZIO");
        }
        if (!CPF_PATTERN.matcher(cpf).matches()) {
            throw new ValidationException("O CPF deve estar no formato 000.000.000-00.", "CPF_INVALIDO");
        }
        if (!isValidCpf(cpf)) {
            throw new ValidationException("O CPF dado é inválido.", "CPF_INEXISTENTE");
        }
        if (pacienteRepository.existsByCpf(cpf)) {
            throw new ValidationException("O CPF já está cadastrado.", "CPF_DUPLICADO");
        }
    }

    // Algoritmo para validação do CPF
    private boolean isValidCpf(String cpf) {
        int[] weightsFirst = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weightsSecond = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int firstDigit = calculateCpfDigit(cpf, weightsFirst);
        int secondDigit = calculateCpfDigit(cpf, weightsSecond);

        return cpf.charAt(9) == (char) (firstDigit + '0') && cpf.charAt(10) == (char) (secondDigit + '0');
    }

    private int calculateCpfDigit(String cpf, int[] weights) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += (cpf.charAt(i) - '0') * weights[i];
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

}
