/**
 * @author higor.robinn on 02/03/2025.
 */

package br.org.santacasa.prontuario_api.dto.patient;

import br.org.santacasa.prontuario_api.dto.address.AddressDTO;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class PatientResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private AddressDTO address;
    private LocalDate criadoEm;
    private LocalDate atualizadoEm;
}
