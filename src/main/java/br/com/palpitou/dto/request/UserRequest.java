package br.com.palpitou.dto.request;

import br.com.palpitou.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequest(
    @NotBlank(message = "O nome é obrigatório")
    String nome,

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    String email,

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, max = 15, message = "A senha deve ter entre 6 e 15 caracteres")
    @Pattern(regexp = "^(?=.*[a-z]).+$", message = "A senha deve conter pelo menos uma letra minúscula")
    String senha,

    @NotNull(message = "A role é obrigatória")
    Role role
) {}
