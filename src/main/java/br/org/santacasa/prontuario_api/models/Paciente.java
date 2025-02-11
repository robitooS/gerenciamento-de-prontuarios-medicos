/**
 * @author higor.robinn on 16/01/2025.
 */

package br.org.santacasa.prontuario_api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa a entidade Paciente no sistema de gerenciamento de prontuários médicos.
 * Contém informações pessoais e de contato do paciente.
 */

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "paciente") // Define o nome da tabela no banco de dados ("paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o ID automaticamente
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
    @Column(name = "id", updatable = false, nullable = false) // Nome da coluna explícito
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100, message = "O nome não pode ultrapassar 100 caracteres.")
    @Column(name = "nome", nullable = false, length = 100) // Nome da coluna definido no banco
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
    @Column(name = "cpf", nullable = false, unique = true, length = 11) // Define a coluna CPF como única
    private String cpf;

    @NotNull(message = "Data de nascimento é obrigatória")
    @PastOrPresent(message = "Data de nascimento não pode ser no futuro")
    @Column(name = "data_nascimento", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Email(message = "O email deve ser válido.")
    @NotBlank(message = "O email é obrigatório.")
    @Size(max = 150, message = "O email não pode ultrapassar 150 caracteres.")
    @Column(name = "email", nullable = false, unique = true, length = 100) // Define a coluna email como única
    private String email;

    @NotBlank(message = "O telefone é obrigatório.")
    @Size(max = 20, message = "O telefone não pode ultrapassar 20 caracteres.")
    @Column(name = "telefone", nullable = false, length = 20) // Nome explícito para a coluna telefone
    private String telefone;

    @Column(name = "ativo", nullable = false) // Nome explícito para a coluna ativo
    private boolean ativo = true; // Define o valor padrão como ativo

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false) // Define o timestamp de criação
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    @OneToOne
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco endereco;

    @OneToMany(mappedBy = "paciente")
    private List<Prontuario> prontuarios = new ArrayList<>();

    /**
     * Construtor personalizado para facilitar a criação de pacientes.
     *
     * @param nome Nome do paciente
     * @param cpf CPF do paciente
     * @param email Email do paciente
     * @param telefone Telefone do paciente
     * @param endereco Endereço do paciente
     */
    public Paciente(String nome, String cpf, String email, String telefone, Endereco endereco, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }
}

