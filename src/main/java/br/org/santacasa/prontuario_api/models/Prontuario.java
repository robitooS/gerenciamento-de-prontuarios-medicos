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
 * Contém informações sobre o prontuário de um paciente, incluindo histórico médico.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "prontuario") // Define o nome da tabela no banco de dados
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o ID automaticamente
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
    @Column(name = "id", updatable = false, nullable = false) // Nome da coluna explícito
    private Long id;

    @NotBlank(message = "O histórico médico é obrigatório.")
    @Column(name = "historico", nullable = false, columnDefinition = "TEXT") // Histórico médico do paciente
    private String historico;

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false) // Data de criação do prontuário
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY) // Relacionamento com Paciente (muitos para um)
    @JoinColumn(name = "paciente_id", nullable = false, foreignKey = @ForeignKey(name = "fk_prontuario_paciente"))
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY) // Relacionamento com Usuário (Médico ou Estagiário)
    @JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "fk_prontuario_usuario")) // Chave estrangeira
    private Usuario usuario;

    /**
     * Construtor personalizado para facilitar a criação de prontuários.
     *
     * @param historico Histórico médico do paciente
     * @param dataCriacao Data de criação do prontuário
     * @param paciente Paciente associado ao prontuário
     * @param usuario Médico ou estagiário responsável pelo prontuário
     */
    public Prontuario(String historico, Paciente paciente, Usuario usuario) {
        this.historico = historico;
        this.paciente = paciente;
        this.usuario = usuario;
    }
}
