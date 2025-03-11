/**
 * @author higor.robinn on 05/02/2025.
 */

package br.org.santacasa.prontuario_api.service.usuario;

import br.org.santacasa.prontuario_api.dto.user.UserCreateDTO;
import br.org.santacasa.prontuario_api.dto.user.UserResponseDTO;
import br.org.santacasa.prontuario_api.exceptions.custom.ResourceNotFoundException;
import br.org.santacasa.prontuario_api.models.User;
import br.org.santacasa.prontuario_api.repository.UserRepository;
import br.org.santacasa.prontuario_api.util.mapper.UserMapper;
import br.org.santacasa.prontuario_api.util.validations.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidation userValidation;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       UserValidation userValidation,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userValidation = userValidation;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponseDTO cadastrarUsuario(UserCreateDTO userCreateDTO) {
        userValidation.validate(userCreateDTO);

        try {
            User user = userMapper.toEntity(userCreateDTO, passwordEncoder);
            User userSalvo = userRepository.save(user);
            return userMapper.toResponseDTO(userSalvo);
        } catch (Exception e) {
            // Log do erro para depuração
            System.err.println("Erro ao criar usuário: " + e.getMessage());

            // Lançar RuntimeException para garantir rollback do @Transactional
            throw new RuntimeException("Erro ao salvar usuário.", e);
        }

    }

    @Transactional(readOnly = true)
    public UserResponseDTO buscarPorId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado.", "id", "USUARIO_NOT_FOUND"));
        return userMapper.toResponseDTO(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> listarUsuarios() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletarUsuario(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado", "id", "USUARIO_NOT_FOUND");
        }

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            System.err.printf("Erro ao deletar usuário com id {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Erro ao deletar usuário", e);
        }
    }
}

