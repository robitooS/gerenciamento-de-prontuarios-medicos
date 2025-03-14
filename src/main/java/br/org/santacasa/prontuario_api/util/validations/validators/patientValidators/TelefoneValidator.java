/**
 * @author higor.robinn on 07/02/2025.
 */

package br.org.santacasa.prontuario_api.util.validations.validators.patientValidators;

import br.org.santacasa.prontuario_api.exceptions.custom.ValidationException;
import br.org.santacasa.prontuario_api.util.validations.validators.ValidatorI;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class TelefoneValidator implements ValidatorI<String> {

    private static final Pattern TELEFONE_PATTERN = Pattern.compile("^\\(\\d{2}\\)\\d{5}-\\d{4}$");

    @Override
    public void validate(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new ValidationException("O telefone não pode estar em branco.", "TELEFONE_VAZIO");
        }
        telefone = sanitizeTelefone(telefone);
        if (!TELEFONE_PATTERN.matcher(telefone).matches()) {
            throw new ValidationException("O telefone deve estar no formato (XX)XXXXX-XXXX.", "TELEFONE_INVALIDO");
        }
    }

    private String sanitizeTelefone(String telefone) {
        return telefone.replaceAll("[^0-9]", "").replaceFirst("(\\d{2})(\\d{5})(\\d{4})", "($1)$2-$3");
    }
}
