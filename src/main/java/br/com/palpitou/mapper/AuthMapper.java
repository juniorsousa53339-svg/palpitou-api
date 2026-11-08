package br.com.palpitou.mapper;

import br.com.palpitou.dto.response.LoginResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public LoginResponse toResponse(String token) {
        return new LoginResponse(token);
    }

}
