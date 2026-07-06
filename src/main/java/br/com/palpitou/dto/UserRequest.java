package br.com.palpitou.dto;

import br.com.palpitou.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequest {

    @Column(nullable = false)
    @NotBlank
    private String nome;

    @Column(nullable = false)
    @Email
    @NotNull
    private String email;

    @NotNull
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role role;

}
