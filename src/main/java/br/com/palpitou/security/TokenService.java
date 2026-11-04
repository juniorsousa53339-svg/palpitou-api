package br.com.palpitou.security;

import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class TokenService {

    // Chave secreta usada para assinar o token (em produção ficaria no application.properties)
    private  final String secret = "palpitou-api-chave-secreta-jwt-super-segura-123456";

    // Metodo auxiliar para converter nossa String secreta em um objeto SecretKey que a biblioteca JJWt entende
    private SecretKey getChave(){

        //Pegamos os bytes da String usando UTF-8 e passamos para Keys.hmacShaKeyFor
        byte[] bytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(bytes);
    }


}
