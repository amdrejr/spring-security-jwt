package com.amdrejr.springsecurityjwt.services;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


// Serviço responsável por autenticar o usuário
@Service
public class AuthenticationService {

    private final JwtService jwtService;

    public AuthenticationService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    // Método que autentica o usuário
    public String authenticate(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }
}
