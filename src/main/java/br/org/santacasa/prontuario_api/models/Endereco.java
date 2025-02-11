/**
 * @author higor.robinn on 16/01/2025.
 */
package br.org.santacasa.prontuario_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * Representa a entidade Endereço no sistema de gerenciamento de prontuários médicos.
 * Contém informações de localização associadas a um paciente.
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "endereco") // Define o nome da tabela no banco de dados
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o ID automaticamente
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
    @Column(name = "endereco_id", updatable = false, nullable = false) // Nome da coluna explícito
    private Long id;

    @NotBlank(message = "O logradouro é obrigatório.")
    @Size(max = 150, message = "O logradouro não pode ultrapassar 150 caracteres.")
    @Column(name = "logradouro", nullable = false, length = 150) // Nome da coluna explícito
    private String logradouro;

    @NotBlank(message = "O número é obrigatório.")
    @Size(max = 10, message = "O número não pode ultrapassar 10 caracteres.")
    @Column(name = "numero", nullable = false, length = 10) // Nome da coluna explícito
    private String numero;

    @Size(max = 50, message = "O complemento não pode ultrapassar 50 caracteres.")
    @Column(name = "complemento", length = 50) // Nome da coluna explícito
    private String complemento;

    @NotBlank(message = "O bairro é obrigatório.")
    @Size(max = 100, message = "O bairro não pode ultrapassar 100 caracteres.")
    @Column(name = "bairro", nullable = false, length = 100) // Nome da coluna explícito
    private String bairro;

    @NotBlank(message = "A cidade é obrigatória.")
    @Size(max = 100, message = "A cidade não pode ultrapassar 100 caracteres.")
    @Column(name = "cidade", nullable = false, length = 100) // Nome da coluna explícito
    private String cidade;

    @NotBlank(message = "O estado é obrigatório.")
    @Size(min = 2, max = 2, message = "O estado deve ter 2 caracteres (UF).")
    @Column(name = "estado", nullable = false, length = 2) // Nome da coluna explícito
    private String estado;

    @NotBlank(message = "O CEP é obrigatório.")
    @Size(min = 8, max = 8, message = "O CEP deve ter exatamente 8 caracteres.")
    @Column(name = "cep", nullable = false, length = 8) // Nome da coluna explícito
    private String cep;

    /**
     * Construtor personalizado para facilitar a criação de endereços.
     *
     * @param logradouro Logradouro do endereço
     * @param numero Número do endereço
     * @param complemento Complemento do endereço
     * @param bairro Bairro do endereço
     * @param cidade Cidade do endereço
     * @param estado Estado do endereço
     * @param cep CEP do endereço
     */
    public Endereco (String logradouro, String numero, String complemento, String bairro, String cidade, String estado, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }
}


