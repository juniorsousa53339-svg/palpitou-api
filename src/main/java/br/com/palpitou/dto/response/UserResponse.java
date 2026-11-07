package br.com.palpitou.dto.response;

import br.com.palpitou.enums.Role;

public record UserResponse(
    String nome,
    String email,
    String senha,
    Role role
) {}
