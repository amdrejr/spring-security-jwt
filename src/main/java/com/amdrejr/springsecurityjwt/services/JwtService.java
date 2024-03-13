package com.amdrejr.springsecurityjwt.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
        Long expiry = 300L; // 

        // Extraímos as roles (níveis de autoridade) do usuário do objeto authentication:
        String scopes = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

        ArrayList<String> roles = new ArrayList<String>();

        authentication.getAuthorities().forEach(role -> {
            roles.add(role.getAuthority());
        });

        // Constrói o JWT com as informações necessárias, incluindo o escopo com as roles
        var claims = JwtClaimsSet.builder()
            .issuer("spring-security-jwt") // Emissor do token
            .issuedAt(now) // Horário de emissão
            .expiresAt(now.plusSeconds(expiry)) // Horário de expiração
            .subject(authentication.getName()) // Nome do usuário
            .claim("role", scopes) // Inclua as roles no campo "role"
            .claim("roles", scopes)
            .claim("scope", "ROLE_ADMIN") 
            .claim("scopes", scopes) 
            .build(); // Constrói o token a partir das informações acima

        // System.out.println("Claims: " + claims.getClaims().toString());    
        // Por fim, usamos o param encoder (JwtEncoder) para codificar os claims em um token JWT e o retornamos:
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue(); 
        // O método encode retorna um objeto JwtEncoding, e usamos getTokenValue() para obter a representação em String do token.
    }
}   
