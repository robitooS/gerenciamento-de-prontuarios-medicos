/**
 * @author higor.robinn on 05/02/2025.
 */

package br.org.santacasa.prontuario_api.util.validations;

import br.org.santacasa.prontuario_api.dto.UsuarioDTO;
import br.org.santacasa.prontuario_api.exceptions.custom.ValidationException;
import br.org.santacasa.prontuario_api.repository.UsuarioRepository;
import br.org.santacasa.prontuario_api.util.validations.validators.SenhaValidator;
import br.org.santacasa.prontuario_api.util.validations.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class UsuarioValidator {

    private final UsuarioRepository usuarioRepository;
    private final List<Validator<UsuarioDTO>> validatorList;
    private final SenhaValidator senhaValidator;

    @Autowired
    public UsuarioValidator(@Qualifier("senhaValidator") SenhaValidator senhaValidator,
                            List<Validator<UsuarioDTO>> validatorList,
                            UsuarioRepository usuarioRepository) {
        this.validatorList = validatorList;
        this.usuarioRepository = usuarioRepository;
        this.senhaValidator = senhaValidator;
    }

    public void validate(UsuarioDTO usuarioDTO) {
        validatorList.forEach(validator -> validator.validate(usuarioDTO));
    }

    private void validateEmailUniqueness(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new ValidationException("E-mail já cadastrado. Escolha outro.", "EMAIL_DUPLICADO");
        }
    }
}
