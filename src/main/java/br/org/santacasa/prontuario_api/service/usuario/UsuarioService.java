/**
 * @author higor.robinn on 05/02/2025.
 */

package br.org.santacasa.prontuario_api.service.usuario;

import br.org.santacasa.prontuario_api.dto.UsuarioDTO;
import br.org.santacasa.prontuario_api.exceptions.custom.ResourceNotFoundException;
import br.org.santacasa.prontuario_api.models.Usuario;
import br.org.santacasa.prontuario_api.repository.UsuarioRepository;
import br.org.santacasa.prontuario_api.util.mapper.UsuarioMapper;
import br.org.santacasa.prontuario_api.util.validations.UsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final UsuarioValidator usuarioValidator;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper,
                          UsuarioValidator usuarioValidator, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.usuarioValidator = usuarioValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UsuarioDTO cadastrarUsuario(UsuarioDTO usuarioDTO) {
        usuarioValidator.validate(usuarioDTO);

        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuarioSalvo);
    }

    @Transactional(readOnly = true)
    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", id, "USUARIO_NOT_FOUND"));
        return usuarioMapper.toDTO(usuario);
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", id, "USUARIO_NOT_FOUND"));

        usuarioValidator.validate(usuarioDTO);
        usuarioExistente.setNome(usuarioDTO.getNome());
        usuarioExistente.setEmail(usuarioDTO.getEmail());
        usuarioExistente.setTipoUsuario(usuarioDTO.getTipoUsuario());

        if (usuarioDTO.getSenha() != null && !usuarioDTO.getSenha().isEmpty()) {
            usuarioExistente.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        }

        Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);
        return usuarioMapper.toDTO(usuarioAtualizado);
    }

    @Transactional
    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário", id, "USUARIO_NOT_FOUND");
        }
        usuarioRepository.deleteById(id);
    }
}

