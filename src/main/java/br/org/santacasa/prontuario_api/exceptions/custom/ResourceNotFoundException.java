/**
 * Exceção lançada quando um recurso não é encontrado no sistema
 * @author higor.robinn on 28/01/2025.
 */

package br.org.santacasa.prontuario_api.exceptions.custom;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando um recurso não é encontrado.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final String field;
    private final String errorCode;

    public ResourceNotFoundException(String message) {
        super(message);
        this.field = null;
        this.errorCode = null;
    }

    public ResourceNotFoundException(String message, String field, String errorCode) {
        super(String.format("%s Campo: %s, Código: %s", message, field, errorCode));
        this.field = field;
        this.errorCode = errorCode;
    }
}

