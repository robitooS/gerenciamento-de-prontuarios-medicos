/**
 * @author higor.robinn on 02/03/2025.
 */

package br.org.santacasa.prontuario_api.dto.patient;

import br.org.santacasa.prontuario_api.dto.address.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class PatientCreateDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(message = "O nome não pode ultrapassar 100 caracteres")
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos")
    private String cpf;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento deve ser no passado")
    private LocalDate dataNascimento;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    @Valid
    private AddressDTO endereco;
}
