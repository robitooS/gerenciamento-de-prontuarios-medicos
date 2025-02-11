/**
 * @author higor.robinn on 07/02/2025.
 */

package br.org.santacasa.prontuario_api.util.validations.validators;
import br.org.santacasa.prontuario_api.dto.PacienteDTO;
import br.org.santacasa.prontuario_api.exceptions.custom.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class NomeValidator implements Validator<PacienteDTO> {

    @Override
    public void validate(PacienteDTO object) {
        String nome = object.getNome();
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValidationException("O nome não pode estar em branco.", "NOME_VAZIO");
        }
        nome = sanitizeNome(nome);
        if (nome.length() < 3) {
            throw new ValidationException("O nome deve ter pelo menos 3 caracteres.", "NOME_CURTO");
        }
        if (!nome.matches("^[A-Za-zÀ-ÿ' ]+$")) {
            throw new ValidationException("O nome deve conter apenas letras e espaços.", "NOME_INVALIDO");
        }
    }

    private String sanitizeNome(String nome) {
        return nome.replaceAll("[^A-Za-zÀ-ÿ' ]", "").trim().replaceAll("\\s+", " ");
    }
}

