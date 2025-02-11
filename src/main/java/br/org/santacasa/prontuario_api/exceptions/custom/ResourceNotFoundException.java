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
    private final Long resourceId;
    private final String resourceType;
    private final String errorCode;

    public ResourceNotFoundException(String resourceType, Long resourceId, String errorCode) {
        super(resourceType + " não encontrado(a) com ID: " + resourceId);
        this.resourceType = resourceType;
        this.resourceId = resourceId;
        this.errorCode = errorCode;
    }
}

