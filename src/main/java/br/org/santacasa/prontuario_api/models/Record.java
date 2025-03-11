/**
 * @author higor.robinn on 16/01/2025.
 */

package br.org.santacasa.prontuario_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Representa a entidade Prontuário no sistema de gerenciamento de prontuários médicos.
 * Contém informações sobre o prontuário de um patient, incluindo histórico médico.
 */

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "records")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT") // Histórico médico do patient
    private String historico;

    @CreationTimestamp
    @Column(nullable = false) // Data de criação do prontuário
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY) // Relacionamento com Patient (muitos para um)
    @JoinColumn(name = "patient_id", nullable = false, foreignKey = @ForeignKey(name = "fk_record_patient"))
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY) // Relacionamento com Usuário (Médico ou Estagiário)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_record_user")) // Chave estrangeira
    private User user;

}
