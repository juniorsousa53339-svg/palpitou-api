package br.com.palpitou.security;

import br.com.palpitou.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Pegar o cabeçalho
        String header = request.getHeader("Authorization");

        // 2. Verificar se existe e tem formato "Bearer ..."
        if (header != null && header.startsWith("Bearer ")) {

            // 3. Extrair só o token
            String token = header.substring(7);

            // 4. Validar e pegar o email
            String email = tokenService.validarToken(token);

            // 5. Buscar usuário e autenticar no Spring Security
            userRepository.findByEmail(email).ifPresent(user -> {
                var auth = new UsernamePasswordAuthenticationToken(
                        user, null, List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            });
        }

        // 6. Sempre deixar a requisição seguir
        filterChain.doFilter(request, response);
    }
}

