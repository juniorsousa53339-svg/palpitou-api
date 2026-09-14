package br.com.palpitou.dto.request;

import br.com.palpitou.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequest {
    private String nome;
    private String email;
    private String senha;
    private Role role;
}
