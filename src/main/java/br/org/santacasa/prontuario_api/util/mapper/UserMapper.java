/**
 * @author higor.robinn on 05/02/2025.
 */

package br.org.santacasa.prontuario_api.util.mapper;

import br.org.santacasa.prontuario_api.dto.user.UserCreateDTO;
import br.org.santacasa.prontuario_api.dto.user.UserResponseDTO;
import br.org.santacasa.prontuario_api.dto.user.UserUpdateDTO;
import br.org.santacasa.prontuario_api.models.User;
import org.mapstruct.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toResponseDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "password", qualifiedByName = "encryptPassword")
    User toEntity(UserCreateDTO dto, @Context BCryptPasswordEncoder bCryptPasswordEncoder);

    @Named("encryptPassword")
    static String encryptPassword(String password, @Context BCryptPasswordEncoder bCryptPasswordEncoder) {
        return bCryptPasswordEncoder.encode(password);
    }

    // Atualiza uma entidade Paciente com os dados do DTO de atualização
    void updateUserFromDTO(UserUpdateDTO dto, @MappingTarget User user);
}
