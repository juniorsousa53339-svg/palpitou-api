package br.com.palpitou.service;


import br.com.palpitou.dto.request.LoginRequest;
import br.com.palpitou.dto.response.LoginResponse;
import br.com.palpitou.exception.BusinessRuleException;
import br.com.palpitou.mapper.AuthMapper;
import br.com.palpitou.repository.UserRepository;
import br.com.palpitou.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authentication;
    private final TokenService tokenService;
    private final AuthMapper authMapper;
    public LoginResponse login(LoginRequest request) {


        // Tenta autenticar usando o AuthenticationManager (ele chama o UserDetailsServiceImpl por baixo dos panos)
        var authToken = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        authentication.authenticate(authToken);

        var user =
                userRepository.findByEmail(request.email())
                        .orElseThrow(() -> new BusinessRuleException("Usuário não encontrado."));

        String token = tokenService.gerarToken(user);

        return authMapper.toResponse(token);
    }
}
