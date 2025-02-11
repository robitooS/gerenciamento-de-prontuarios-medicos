/**
 * @author higor.robinn on 20/01/2025.
 */

package br.org.santacasa.prontuario_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {

    @NotBlank(message = "Nome obrigatório")
    private String nome;

    @NotBlank(message = "Email obrigatório")
    private String email;

    @NotBlank(message = "Telefone obrigatório")
    private String telefone;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos")
    private String cpf;

    @NotNull(message = "Data de nascimento obrigatória")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotNull(message = "Endereço é obrigatório")
    private EnderecoDTO endereco;

}
