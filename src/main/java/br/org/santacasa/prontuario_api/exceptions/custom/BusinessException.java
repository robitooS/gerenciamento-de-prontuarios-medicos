/**
 * @author higor.robinn on 05/02/2025.
 */

package br.org.santacasa.prontuario_api.exceptions.custom;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção genérica para erros de regras de negócio.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
public class BusinessException extends RuntimeException {
    private final String errorCode;

    public BusinessException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
