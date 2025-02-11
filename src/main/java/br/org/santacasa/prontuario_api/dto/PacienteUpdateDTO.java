/**
 * @author higor.robinn on 05/02/2025.
 */

package br.org.santacasa.prontuario_api.dto;

import jakarta.validation.constraints.Email;
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

    private String nome;

    private String email;
    
    private String telefone;

    private LocalDate dataNascimento;

    private EnderecoDTO endereco;
}
