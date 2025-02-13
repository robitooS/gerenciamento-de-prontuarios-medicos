/**
 * @author higor.robinn on 07/02/2025.
 */

package br.org.santacasa.prontuario_api.util.validations.validators;
import br.org.santacasa.prontuario_api.exceptions.custom.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class NomeValidator implements Validator<String> {

    @Override
    public void validate(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValidationException("O nome não pode estar em branco.", "NOME_VAZIO");
        }
        nome = sanitizeNome(nome);
        if(nome.length() < 3) {
            throw new ValidationException("O nome deve conter pelo menos 3 caracteres.", "NOME_CURTO");
        }
        if(!nome.matches("^[A-Za-zÀ-ÿ' ]+$")) {
            throw new ValidationException("O nome não pode conter caracteres especiais.", "NOME_INVALIDO");
        }
    }

    private String sanitizeNome(String nome) {
        return nome.replaceAll("[^A-Za-zÀ-ÿ' ]", "").trim().replaceAll("\\s+", " ");
    }
}

