/**
 * @author higor.robinn on 05/03/2025.
 */

package br.org.santacasa.prontuario_api.util.validations.validators.userValidators;

import br.org.santacasa.prontuario_api.exceptions.custom.ValidationException;
import br.org.santacasa.prontuario_api.util.validations.validators.ValidatorI;
import org.springframework.stereotype.Component;

@Component
public class UsernameValidator implements ValidatorI<String> {

    @Override
    public void validate(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException("O nome de usuário não pode estar em branco.", "USERNAME_VAZIO");
        }
        username = sanitizeUsername(username);

        if (username.length() < 3) {
            throw new ValidationException("O nome de usuário deve conter pelo menos 3 caracteres.", "USERNAME_CURTO");
        }
        if (username.length() > 15) {
            throw new ValidationException("O nome de usuário não pode ter mais de 15 caracteres.", "USERNAME_LONGO");
        }
        if (!username.matches("^[A-Za-z0-9_]+$")) {
            throw new ValidationException("O nome de usuário pode conter apenas letras, números e sublinhados.", "USERNAME_INVALIDO");
        }
    }

    private String sanitizeUsername(String username) {
        return username.trim().replaceAll("[^A-Za-z0-9_]", "");
    }
}
