package br.com.palpitou.service;

import br.com.palpitou.dto.PutRequestUser;
import br.com.palpitou.dto.PutResponseUser;
import br.com.palpitou.dto.UserRequest;
import br.com.palpitou.dto.UserResponse;
import br.com.palpitou.entity.Campeonato;
import br.com.palpitou.entity.User;
import br.com.palpitou.mapper.UserMapper;
import br.com.palpitou.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    // =========================
    // Métodos auxiliares
    // =========================

    private User buscarUser(Long id) {
        return userRepository.findById(id).
                orElseThrow(() ->
                        new RuntimeException
                                ("Usuario não encontrado!"));
    }


    // =========================
    // CRUD
    // =========================

    public UserResponse salvar(UserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado.");
        }

        User user = userMapper.toEntity(request);

        User userSalvo = userRepository.save(user);

        return userMapper.toResponse(userSalvo);
    }

    public UserResponse buscar(Long id) {

        User user = buscarUser(id);
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

    public PutResponseUser updateUser(Long id, PutRequestUser request) {

        User userBd = buscarUser(id);

        userBd.alterarPerfil(

                request.getNome(),
                request.getEmail(),
                request.getSenha()
        );

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado.");
        }

        User userAtualizado = userRepository.save(userBd);
        return userMapper.toPut(userAtualizado);
    }

    public  UserResponse updateAdmin(Long id, UserRequest request) {
        User userBd = buscarUser(id);

        userBd.alterarDadosAdmin(

                request.getNome(),
                request.getEmail(),
                request.getSenha(),
                request.getRole()
        );

        User userAtualizado = userRepository.save(userBd);
        return userMapper.toResponse(userAtualizado);
    }

    public void delete(Long id) {

        User userBd = buscarUser(id);
        userRepository.delete(userBd);
    }
}
