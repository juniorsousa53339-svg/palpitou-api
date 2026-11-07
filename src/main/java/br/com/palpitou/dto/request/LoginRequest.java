package br.com.palpitou.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(

        @Email
        @NotNull
        @Column(unique = true)
        String email,

        @NotNull
        String senha
        ) {
}
