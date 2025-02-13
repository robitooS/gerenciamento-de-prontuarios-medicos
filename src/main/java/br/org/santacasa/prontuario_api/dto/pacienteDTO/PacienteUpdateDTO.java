/**
 * @author higor.robinn on 05/02/2025.
 */

package br.org.santacasa.prontuario_api.dto.pacienteDTO;

import br.org.santacasa.prontuario_api.dto.enderecoDTO.EnderecoDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteUpdateDTO {

    @NotBlank(message = "Nome obrigatório.")
    private String nome;

    @NotBlank(message = "Email obrigatório.")
    private String email;

    @NotBlank(message = "Telefone obrigatório.")
    private String telefone;

    @NotBlank(message = "Endereço obrigatório.")
    private EnderecoDTO endereco;
}
