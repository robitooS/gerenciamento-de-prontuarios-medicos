/**
 * @author higor.robinn on 05/03/2025.
 */

package br.org.santacasa.prontuario_api.util.validations.validators.addressValidators;

import br.org.santacasa.prontuario_api.dto.address.AddressDTO;
import br.org.santacasa.prontuario_api.exceptions.custom.ValidationException;
import br.org.santacasa.prontuario_api.util.validations.validators.ValidatorI;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AddressFieldsValidator implements ValidatorI<AddressDTO> {

    private static final Set<String> ESTADOS_VALIDOS = Set.of(
            "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
            "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
            "RS", "RO", "RR", "SC", "SP", "SE", "TO"
    );

    @Override
    public void validate(AddressDTO endereco) {
        if (endereco == null) {
            throw new ValidationException("O endereço não pode ser nulo.", "ENDERECO_NULO");
        }

        validateCampo(endereco.getLogradouro(), "logradouro", "^[A-Za-zÀ-ÿ0-9' ]+$", 3);
        validateCampo(endereco.getNumero(), "número", "^[0-9]+[A-Za-z]?$", 1);
        validateCampo(endereco.getBairro(), "bairro", "^[A-Za-zÀ-ÿ' ]+$", 3);
        validateCampo(endereco.getCidade(), "cidade", "^[A-Za-zÀ-ÿ' ]+$", 3);
        validateEstado(endereco.getEstado());
        validateCep(endereco.getCep());
    }

    private void validateCampo(String valor, String nomeCampo, String regex, int tamanhoMin) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new ValidationException("O campo " + nomeCampo + " não pode estar em branco.", nomeCampo.toUpperCase() + "_VAZIO");
        }
        valor = sanitize(valor);
        if (valor.length() < tamanhoMin) {
            throw new ValidationException("O campo " + nomeCampo + " deve ter pelo menos " + tamanhoMin + " caracteres.", nomeCampo.toUpperCase() + "_CURTO");
        }
        if (!valor.matches(regex)) {
            throw new ValidationException("O campo " + nomeCampo + " contém caracteres inválidos.", nomeCampo.toUpperCase() + "_INVALIDO");
        }
    }

    private void validateEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new ValidationException("O estado não pode estar em branco.", "ESTADO_VAZIO");
        }
        if (!ESTADOS_VALIDOS.contains(estado.toUpperCase())) {
            throw new ValidationException("O estado informado é inválido.", "ESTADO_INVALIDO");
        }
    }

    private void validateCep(String cep) {
        if (cep == null || cep.trim().isEmpty()) {
            throw new ValidationException("O CEP não pode estar em branco.", "CEP_VAZIO");
        }
        if (!cep.matches("^\\d{8}$")) {
            throw new ValidationException("O CEP deve conter exatamente 8 dígitos numéricos.", "CEP_INVALIDO");
        }
    }

    private String sanitize(String valor) {
        return valor.replaceAll("[^A-Za-zÀ-ÿ0-9' ]", "").trim().replaceAll("\\s+", " ");
    }
}
