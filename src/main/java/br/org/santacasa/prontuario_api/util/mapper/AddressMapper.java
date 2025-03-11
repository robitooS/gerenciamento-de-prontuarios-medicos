/**
 * @author higor.robinn on 13/02/2025.
 */

package br.org.santacasa.prontuario_api.util.mapper;

import br.org.santacasa.prontuario_api.dto.address.AddressDTO;
import br.org.santacasa.prontuario_api.models.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toEntity(AddressDTO addressDTO);
    AddressDTO toDTO(Address address);

}
