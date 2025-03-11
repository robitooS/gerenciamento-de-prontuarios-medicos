/**
 * @author higor.robinn on 23/01/2025.
 */

package br.org.santacasa.prontuario_api.repository;

import br.org.santacasa.prontuario_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
