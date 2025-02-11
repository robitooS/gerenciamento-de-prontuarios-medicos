/**
 * @author higor.robinn on 05/02/2025.
 */

package br.org.santacasa.prontuario_api.exceptions.custom;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção para validações de dados.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class ValidationException extends RuntimeException {
    private final String errorCode;

    public ValidationException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
