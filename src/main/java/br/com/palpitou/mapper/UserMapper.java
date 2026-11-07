package br.com.palpitou.mapper;

import br.com.palpitou.dto.putResponse.PutResponseUser;
import br.com.palpitou.dto.request.UserRequest;
import br.com.palpitou.dto.response.UserResponse;
import br.com.palpitou.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest request) {
        User user = new User();
        user.setEmail(request.email());
        user.setNome(request.nome());
        user.setSenha(request.senha());
        user.setRole(request.role());
        return user;
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
            user.getNome(),
            user.getEmail(),
            user.getSenha(),
            user.getRole()
        );
    }

    public PutResponseUser toPut(User user) {
        return new PutResponseUser(
            user.getNome(),
            user.getEmail(),
            user.getSenha()
        );
    }
}
