package br.com.palpitou.service;

import br.com.palpitou.dto.CriarUserRequest;
import br.com.palpitou.dto.UserResponse;
import br.com.palpitou.entity.User;
import br.com.palpitou.mapper.UserMapper;
import br.com.palpitou.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse salvar(CriarUserRequest request) {

    User user = userMapper.toEntity(request);

    User userSalvo =  userRepository.save(user);
     
    return userMapper.toResponse(userSalvo);
    }

}
