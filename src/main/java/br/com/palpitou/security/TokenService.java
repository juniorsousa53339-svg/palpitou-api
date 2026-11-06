package br.com.palpitou.security;

import br.com.palpitou.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {
    // Chave secreta usada para assinar o token (em produção ficaria no application.properties)
    private final String secret = "palpitou-api-chave-secreta-jwt-super-segura-123456";

    // Metodo auxiliar para converter nossa String secreta em um objeto SecretKey que a biblioteca JJWt entende
    private SecretKey getChave() {

        //Pegamos os bytes da String usando UTF-8 e passamos para Keys.hmacShaKeyFor
        byte[] bytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String gerarToken(User user) {

        Instant agora = Instant.now();
        Instant expiracao = agora.plus(2, ChronoUnit.HOURS);

        return Jwts.builder() // Inicia a construção do token

                .subject(user.getEmail())                 // Define o "dono" do token
                .issuer("palpitou-api")               // Define quem emitiu
                .issuedAt(Date.from(agora))              // Data de emissão
                .expiration(Date.from(expiracao))       // Data de expiração
                .signWith(getChave())                  // Assina com nossa SecretKey
                .compact();                           // Gera a String final do token
    }

    public String validarToken(String token) {

        return Jwts.parser()                       // Inicia o parser
                .verifyWith(getChave())         // Usa a MESMA chave para verificar a assinatura
                .build()                       // Constrói o parser
                .parseSignedClaims(token)     // Lê e valida o token
                .getPayload()                // Acessa o payload (as claims)
                .getSubject();              // Extrai o subject (o email que guardamos)
    }

}
