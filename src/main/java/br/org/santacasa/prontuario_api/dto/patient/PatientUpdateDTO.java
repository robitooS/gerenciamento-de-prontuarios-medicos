/**
 * @author higor.robinn on 02/03/2025.
 */

package br.org.santacasa.prontuario_api.dto.patient;

import br.org.santacasa.prontuario_api.dto.address.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PatientUpdateDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(message = "O nome não pode ultrapassar 100 caracteres")
    private String nome;

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


