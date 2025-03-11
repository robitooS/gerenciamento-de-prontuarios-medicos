/**
 * @author higor.robinn on 05/03/2025.
 */

package br.org.santacasa.prontuario_api.util.validations;

import br.org.santacasa.prontuario_api.dto.address.AddressDTO;
import br.org.santacasa.prontuario_api.util.validations.validators.addressValidators.AddressFieldsValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressValidation {

    AddressFieldsValidator addressFieldsValidator;

    @Autowired
    public AddressValidation(AddressFieldsValidator addressFieldsValidator) {
        this.addressFieldsValidator = addressFieldsValidator;
    }

    public void validate(@Valid AddressDTO addressDTO) {
        addressFieldsValidator.validate(addressDTO);
    }
}
