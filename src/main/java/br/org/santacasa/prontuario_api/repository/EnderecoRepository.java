/**
 * @author higor.robinn on 28/01/2025.
 */

package br.org.santacasa.prontuario_api.repository;

import br.org.santacasa.prontuario_api.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
