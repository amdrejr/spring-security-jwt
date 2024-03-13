package com.amdrejr.springsecurityjwt.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.amdrejr.springsecurityjwt.entities.User;
import com.amdrejr.springsecurityjwt.security.UserCredentials;

// Serviço responsável por autenticar o usuário
@Service
public class AuthenticationService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    public String signin(UserCredentials userCredentials) {
        String username = userCredentials.getUsername();
        String password = userCredentials.getPassword();

        // Criando um objeto de autenticação
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        var user = (User) auth.getPrincipal();

        return jwtService.generateToken(user);
    }

    // // Método que autentica o usuário
    // public String authenticate(Authentication authentication) {
    //     if(authentication == null) {
    //         throw new IllegalArgumentException("Authentication is null");
    //     }
    //     return jwtService.generateToken(authentication);
    // }
}
