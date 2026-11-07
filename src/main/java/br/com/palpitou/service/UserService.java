package br.com.palpitou.service;

import br.com.palpitou.dto.putRequest.PutRequestUser;
import br.com.palpitou.dto.putResponse.PutResponseUser;
import br.com.palpitou.dto.request.UserRequest;
import br.com.palpitou.dto.response.UserResponse;
import br.com.palpitou.entity.User;
import br.com.palpitou.exception.BusinessRuleException;
import br.com.palpitou.exception.ResourceNotFoundException;
import br.com.palpitou.mapper.UserMapper;
import br.com.palpitou.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    // =========================
    // Métodos auxiliares
    // =========================

    private User buscarUser(Long id) {
        return userRepository.findById(id).
                orElseThrow(() ->
                        new ResourceNotFoundException
                                ("Usuario não encontrado!"));
    }

    private void validarEmailUnico(String novoEmail, String emailAtual) {
        if (userRepository.existsByEmail(novoEmail) && !emailAtual.equals(novoEmail)) {
            throw new BusinessRuleException("E-mail já cadastrado.");
        }
    }


    // =========================
    // CRUD
    // =========================

    public UserResponse salvar(UserRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new BusinessRuleException("E-mail já cadastrado.");
        }

        User user = userMapper.toEntity(request);

        user.setSenha(passwordEncoder.
                encode(request.senha()));

        User userSalvo =
                userRepository.save(user);

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

        validarEmailUnico(request.email(), userBd.getEmail());

        String senhaCriptografada =
                passwordEncoder.encode(request.senha());

        userBd.alterarPerfil(
                request.nome(),
                request.email(),
                senhaCriptografada
        );

        User userAtualizado = userRepository.save(userBd);
        return userMapper.toPut(userAtualizado);
    }

    public UserResponse updateAdmin(Long id, UserRequest request) {
        User userBd = buscarUser(id);

        validarEmailUnico(request.email(), userBd.getEmail());

        String senhaCriptografada = passwordEncoder.encode(request.senha());

        userBd.alterarDadosAdmin(

                request.nome(),
                request.email(),
                senhaCriptografada,
                request.role()
        );

        User userAtualizado = userRepository.save(userBd);
        return userMapper.toResponse(userAtualizado);
    }

    public void delete(Long id) {

        User userBd = buscarUser(id);
        userRepository.delete(userBd);
    }
}
