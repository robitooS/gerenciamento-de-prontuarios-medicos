/**
 * @author higor.robinn on 23/01/2025.
 */

package br.org.santacasa.prontuario_api.repository;

import br.org.santacasa.prontuario_api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
}
