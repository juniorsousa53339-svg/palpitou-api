package br.com.palpitou.entity;

import br.com.palpitou.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter @Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String nome;

      @Column(nullable = false)
      @Email
      @NotNull
      private String Email;

       @NotNull
       private String Senha;

      @Enumerated(EnumType.STRING)
      private Role role;

       @Enumerated(EnumType.STRING)
        private Enum Status;
}
