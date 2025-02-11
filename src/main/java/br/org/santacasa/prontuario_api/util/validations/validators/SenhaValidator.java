/**
 * @author higor.robinn on 07/02/2025.
 */

package br.org.santacasa.prontuario_api.util.validations.validators;

import br.org.santacasa.prontuario_api.dto.UsuarioDTO;
import br.org.santacasa.prontuario_api.exceptions.custom.ValidationException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
@Qualifier("senhaValidator")
public class SenhaValidator implements Validator<UsuarioDTO> {

    // Pelo menos 8 caracteres, 1 minusculo, 1 maiusculo, 1 numero e 1 caracter especial
    private static final Pattern SENHA_PATTERN = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
    );

    @Override
    public void validate(UsuarioDTO object) {
        String senha = object.getSenha();

        if (senha == null || senha.trim().isEmpty()) {
            throw new ValidationException("A senha não pode estar em branco.", "SENHA_VAZIA");
        }
        if (!SENHA_PATTERN.matcher(senha).matches()) {
            throw new ValidationException(
                    "A senha deve ter no mínimo 8 caracteres, incluindo pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial.",
                    "SENHA_FRACA"
            );
        }
    }
}
