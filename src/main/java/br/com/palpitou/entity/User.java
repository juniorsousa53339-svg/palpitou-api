package br.com.palpitou.entity;

import br.com.palpitou.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String nome;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany
    private Participacao participacao;

    @OneToMany
    private Pagamento pagamento;

    public void alterarDados(
            String nome,
            String email,
            String senha
    ) {

        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

}
