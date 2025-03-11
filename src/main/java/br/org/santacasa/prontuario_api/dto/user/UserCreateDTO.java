/**
 * @author higor.robinn on 02/03/2025.
 */

package br.org.santacasa.prontuario_api.dto.user;

import br.org.santacasa.prontuario_api.models.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class UserCreateDTO {

    @NotBlank(message = "Username é obrigatório")
    @Size(max = 50, message = "Username não pode ultrapassar 50 caracteres")
    private String username;

    @NotBlank(message = "Password é obrigatória")
    @Size(max = 120, message = "Password não pode ultrapassar 100 caracteres")
    private String password;

    @NotNull(message = "Role é obrigatória")
    private Role role;

}
