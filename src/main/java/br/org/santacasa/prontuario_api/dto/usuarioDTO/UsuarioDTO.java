/**
 * @author higor.robinn on 20/01/2025.
 */

package br.org.santacasa.prontuario_api.dto.usuarioDTO;

import br.org.santacasa.prontuario_api.models.TipoUsuario;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String nome;

    @Email
    private String email;

    private TipoUsuario tipoUsuario;

    private String senha;

}
