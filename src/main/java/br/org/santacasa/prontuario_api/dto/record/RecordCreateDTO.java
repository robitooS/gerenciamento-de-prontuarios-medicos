/**
 * @author higor.robinn on 03/03/2025.
 */

package br.org.santacasa.prontuario_api.dto.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class RecordCreateDTO {

    @NotBlank(message = "Histórico é obrigatório")
    private String record;

    @NotNull(message = "Id do paciente é obrigatório")
    private Long pacienteId;

    @NotNull(message = "Id do usuário é obrigatório")
    private Long usuarioId;
}
