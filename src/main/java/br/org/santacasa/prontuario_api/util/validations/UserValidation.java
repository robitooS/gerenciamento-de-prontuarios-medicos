/**
 * @author higor.robinn on 05/02/2025.
 */

package br.org.santacasa.prontuario_api.util.validations;

import br.org.santacasa.prontuario_api.dto.user.UserCreateDTO;
import br.org.santacasa.prontuario_api.util.validations.validators.userValidators.SenhaValidator;
import br.org.santacasa.prontuario_api.util.validations.validators.userValidators.UsernameValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidation {

    private final UsernameValidator usernameValidator;
    private final SenhaValidator senhaValidator;

    @Autowired
    public UserValidation(SenhaValidator senhaValidator,
                          UsernameValidator usernameValidator){
        this.usernameValidator = usernameValidator;
        this.senhaValidator = senhaValidator;
    }

    public void validate(@Valid UserCreateDTO usuarioDTO) {
        usernameValidator.validate(usuarioDTO.getUsername());
        senhaValidator.validate(usuarioDTO.getPassword());
    }

}
