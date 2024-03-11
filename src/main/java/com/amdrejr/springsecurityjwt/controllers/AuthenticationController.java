package com.amdrejr.springsecurityjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amdrejr.springsecurityjwt.dto.AuthResponse;
import com.amdrejr.springsecurityjwt.security.UserCredentials;
import com.amdrejr.springsecurityjwt.services.AuthenticationService;

// Endpoint para autenticação
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public String authenticate(Authentication authentication) {
        return authenticationService.authenticate(authentication);
    }

    @PostMapping("/login")
    public AuthResponse authenticate(@RequestBody UserCredentials userCredentials) {
        String token = authenticationService.signin(userCredentials);
        return new AuthResponse(token);
    }
}
