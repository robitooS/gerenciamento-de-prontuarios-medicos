/**
 * @author higor.robinn on 16/01/2025.
 */

package br.org.santacasa.prontuario_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


/**
 * Representa a entidade Usuario no sistema de gerenciamento de prontuários médicos.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuario")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 100, message = "O nome não pode ultrapassar 100 caracteres.")
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
    @Column(name = "senha", nullable = false)
    private String senha;

    @NotBlank(message = "O tipo de usuário é obrigatório.")
    @Column(name = "tipo_usuario", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    /**
     * Construtor personalizado.
     *
     * @param nome Nome do usuário
     * @param email Email do usuário
     * @param tipoUsuario Tipo do usuário
     */
    public Usuario(String nome, String email, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
    }
}

