/**
 * @author higor.robinn on 05/02/2025.
 */

package br.org.santacasa.prontuario_api.util.validations;

import br.org.santacasa.prontuario_api.dto.usuarioDTO.UsuarioDTO;
import br.org.santacasa.prontuario_api.exceptions.custom.ValidationException;
import br.org.santacasa.prontuario_api.repository.UsuarioRepository;
import br.org.santacasa.prontuario_api.util.validations.validators.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidator {

    private final UsuarioRepository usuarioRepository;
    private final CpfValidator cpfValidator;
    private final NomeValidator nomeValidator;
    private final SenhaValidator senhaValidator;

    @Autowired
    public UsuarioValidator(SenhaValidator senhaValidator,
                            UsuarioRepository usuarioRepository,
                            CpfValidator cpfValidator,
                            NomeValidator nomeValidator){
        this.cpfValidator = cpfValidator;
        this.nomeValidator = nomeValidator;
        this.usuarioRepository = usuarioRepository;
        this.senhaValidator = senhaValidator;
    }

    public void validate(UsuarioDTO usuarioDTO) {
        nomeValidator.validate(usuarioDTO.getNome());
        senhaValidator.validate(usuarioDTO.getSenha());

        validateEmailUniqueness(usuarioDTO.getEmail());
    }

    private void validateEmailUniqueness(String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new ValidationException("E-mail já cadastrado. Escolha outro.", "EMAIL_DUPLICADO");
        }
    }
}
