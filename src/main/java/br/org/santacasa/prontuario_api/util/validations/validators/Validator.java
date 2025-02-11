/**
 * @author higor.robinn on 05/02/2025.
 */

package br.org.santacasa.prontuario_api.util.validations.validators;

@FunctionalInterface
public interface Validator<T> {
    void validate(T object);
}
