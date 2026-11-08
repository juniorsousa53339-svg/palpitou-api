package br.com.palpitou.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(

        @Email
        @NotNull
        String email,

        @NotNull
        String senha
        ) {
}
