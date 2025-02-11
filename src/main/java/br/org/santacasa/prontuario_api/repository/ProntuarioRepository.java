/**
 * @author higor.robinn on 16/01/2025.
 */

package br.org.santacasa.prontuario_api.repository;

import br.org.santacasa.prontuario_api.models.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
}
