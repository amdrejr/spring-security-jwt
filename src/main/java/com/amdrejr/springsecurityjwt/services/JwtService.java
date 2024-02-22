package com.amdrejr.springsecurityjwt.services;

import java.time.Instant;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    // JwtEnconde da dependência oauth2-resource-server 
    private final JwtEncoder encoder;

    // Injeção de dependência do JwtEncoder
    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    // Token gerado a partir do JwtEncoder
    // Será usado para autenticar o usuário.
    public String generateToken(Authentication authentication) {
        // O token possui tempo de expiração.
        // Então, definimos o horário atual:
        Instant now = Instant.now();
        // e o tempo para que o token expire:
        Long expiry = 3600l; // 1 hora

        // Extraímos as roles (níveis de autoridade) do usuário do objeto authentication:
        String userRoles = authentication.getAuthorities().toString();
        
        var claims = JwtClaimsSet.builder()
            .issuer("spring-security-jwt") // emissor do token
            .issuedAt(now) // horário de emissão
            .expiresAt(now.plusSeconds(expiry)) // horário de expiração
            .subject(authentication.getName()) // nome do usuário
            .claim("roles", userRoles) // níveis de autoridade do usuário
            .build(); // construímos o token a partir das informações acima
        
        // Por fim, usamos o param encoder (JwtEncoder) para codificar os claims em um token JWT e o retornamos:
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue(); 
        // O método encode retorna um objeto JwtEncoding, e usamos getTokenValue() para obter a representação em String do token.
    }
}
