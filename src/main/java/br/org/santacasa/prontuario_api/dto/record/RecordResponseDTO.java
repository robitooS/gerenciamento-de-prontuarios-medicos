/**
 * @author higor.robinn on 03/03/2025.
 */

package br.org.santacasa.prontuario_api.dto.record;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class RecordResponseDTO {

    private Long id;
    private String historico;
    private Long pacienteId;
    private Long usuarioId;
    private LocalDate dataCriacao;

}
