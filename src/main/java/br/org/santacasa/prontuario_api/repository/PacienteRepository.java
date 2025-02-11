/**
 * @author higor.robinn on 21/01/2025.
 */

package br.org.santacasa.prontuario_api.repository;

import br.org.santacasa.prontuario_api.models.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByCpf(String cpf);
    Page<Paciente> findByNome(String nome, Pageable pageable);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
