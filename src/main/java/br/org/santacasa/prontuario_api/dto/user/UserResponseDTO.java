/**
 * @author higor.robinn on 02/03/2025.
 */

package br.org.santacasa.prontuario_api.dto.user;

import br.org.santacasa.prontuario_api.models.Role;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String username;
    private Role role;
    private LocalDate criadoEm;
    private LocalDate atualizadoEm;
}
