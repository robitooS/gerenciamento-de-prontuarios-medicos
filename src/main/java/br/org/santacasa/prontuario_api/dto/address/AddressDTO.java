/**
 * @author higor.robinn on 02/03/2025.
 */
 
package br.org.santacasa.prontuario_api.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class AddressDTO {

    @NotBlank(message = "Logradouro é obrigatório")
    @Size(max = 150, message = "Logradouro não pode ultrapassar 150 caracteres")
    private String logradouro;

    @NotBlank(message = "Número é obrigatório")
    @Size(max = 10, message = "Número não pode ultrapassar 10 caracteres")
    private String numero;

    @Size(max = 50, message = "Complemento não pode ultrapassar 50 caracteres")
    private String complemento;

    @NotBlank(message = "Bairro é obrigatório")
    @Size(max = 100, message = "Bairro não pode ultrapassar 100 caracteres")
    private String bairro;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 100, message = "Cidade não pode ultrapassar 100 caracteres")
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    @Size(min = 2, max = 2, message = "Estado deve conter 2 caracteres")
    private String estado;

    @NotBlank(message = "CEP é obrigatório")
    @Size(min = 8, max = 8, message = "CEP deve conter 8 caracteres")
    private String cep;
}
