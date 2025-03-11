/**
 * @author higor.robinn on 07/02/2025.
 */

package br.org.santacasa.prontuario_api.util.validations.validators.userValidators;

import br.org.santacasa.prontuario_api.exceptions.custom.ValidationException;
import br.org.santacasa.prontuario_api.util.validations.validators.ValidatorI;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class SenhaValidator implements ValidatorI<String> {

    // Pelo menos 8 caracteres, 1 minusculo, 1 maiusculo, 1 numero e 1 caracter especial
    private static final Pattern SENHA_PATTERN = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
    );

    @Override
    public void validate(String senha) {
        if (senha == null || senha.trim().isEmpty()) {
            throw new ValidationException("A senha não pode estar em branco.", "SENHA_VAZIA");
        }
        if (!SENHA_PATTERN.matcher(senha).matches()) {
            throw new ValidationException(
                    "A senha deve conter pelo menos: 1 letra maiúscula, 1 letra minúscula, 1 número e 1 caractere especial.",
                    "SENHA_INVALIDA"
            );
        }
    }
}
