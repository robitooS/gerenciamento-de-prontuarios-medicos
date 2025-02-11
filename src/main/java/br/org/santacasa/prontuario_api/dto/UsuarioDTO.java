/**
 * @author higor.robinn on 20/01/2025.
 */

package br.org.santacasa.prontuario_api.dto;

import br.org.santacasa.prontuario_api.models.TipoUsuario;
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
    private String email;
    private TipoUsuario tipoUsuario;
    private String senha;

}
