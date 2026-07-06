package br.com.palpitou.service;

import br.com.palpitou.dto.UserRequest;
import br.com.palpitou.dto.UserResponse;
import br.com.palpitou.entity.User;
import br.com.palpitou.mapper.UserMapper;
import br.com.palpitou.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse salvar(UserRequest request) {

        User user = userMapper.toEntity(request);

        User userSalvo = userRepository.save(user);

        return userMapper.toResponse(userSalvo);
    }

    public UserResponse buscar(Long id) {

        User user =
                userRepository.findById(id).
                        orElseThrow(() ->
                                new RuntimeException
                                        ("Usuario não encontrado!"));

        return userMapper.toResponse(user);
    }

    public List<UserResponse> listarTodos() {

        List<User> users = userRepository.findAll();

        List<UserResponse> resposta =
                users.stream()
                        .map(userMapper::toResponse)
                        .toList();

        return resposta;
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {



        User userBd =
                userRepository.findById(id)
                        .orElseThrow(()
                                -> new RuntimeException
                                ("Usuario não encontrado")
                        );

        userBd.alterarDados(

                userBd.getNome(),
                userBd.getEmail(),
                userBd.getSenha()
        );

        return userMapper.toResponse(
                userRepository.save(userBd));
    }
}
