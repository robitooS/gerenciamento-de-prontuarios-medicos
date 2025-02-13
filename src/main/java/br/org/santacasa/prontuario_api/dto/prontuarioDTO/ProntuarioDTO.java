/**
 * @author higor.robinn on 20/01/2025.
 */

package br.org.santacasa.prontuario_api.dto.prontuarioDTO;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProntuarioDTO {

    @NotBlank
    private String historico;
    private MedicoResumoDTO medico; // Apenas dados básicos do médico
    private PacienteResumoDTO paciente; // Apenas dados básicos do paciente
}

// DTOs resumidos para relacionamentos
class MedicoResumoDTO {
    private Long id;
    private String nome;
}

class PacienteResumoDTO {
    private Long id;
    private String nome;
}
