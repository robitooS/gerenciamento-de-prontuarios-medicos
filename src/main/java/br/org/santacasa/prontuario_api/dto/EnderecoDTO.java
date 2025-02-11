/**
 * @author higor.robinn on 20/01/2025.
 */

package br.org.santacasa.prontuario_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

    @NotBlank
    @Size(max = 150, message = "Logradouro muito longo")
    private String logradouro;

    @NotBlank
    @Size(max = 10)
    private String numero;

    private String complemento;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    @Pattern(regexp = "AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO",
            message = "UF inválida")
    private String estado;

    @NotBlank
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido")
    private String cep;

}
