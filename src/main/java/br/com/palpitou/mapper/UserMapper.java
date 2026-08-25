package br.com.palpitou.mapper;

import br.com.palpitou.dto.PutRequestUser;
import br.com.palpitou.dto.PutResponseUser;
import br.com.palpitou.dto.UserRequest;
import br.com.palpitou.dto.UserResponse;
import br.com.palpitou.entity.User;
import org.springframework.stereotype.Component;

@Component

public class UserMapper {

    public User toEntity(UserRequest request) {

        User user = new User();

        user.setEmail(request.getEmail());
        user.setNome(request.getNome());
        user.setSenha(request.getSenha());
        user.setRole(request.getRole());

        return user;
    }

    public UserResponse toResponse(User user){

        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setNome(user.getNome());
        userResponse.setSenha(user.getSenha());
        userResponse.setRole(user.getRole());

        return userResponse;
    }

    public PutResponseUser toPut(User user){
        PutResponseUser putResponseUser = new PutResponseUser();

        putResponseUser.setEmail(user.getEmail());
        putResponseUser.setNome(user.getNome());
        putResponseUser.setSenha(user.getSenha());

        return putResponseUser;
    }
}
