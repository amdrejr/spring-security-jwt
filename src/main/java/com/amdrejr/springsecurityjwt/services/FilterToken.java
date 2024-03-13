package com.amdrejr.springsecurityjwt.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.amdrejr.springsecurityjwt.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, 
            HttpServletResponse response, 
            FilterChain filterChain
        ) throws ServletException, IOException {
            String token;

            var authorizationHeader = request.getHeader("Authorization");

            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.replace("Bearer ", "");
                var subject = this.jwtService.getSubject(token);

                var user = this.userRepository.findByUsername(subject);
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.get().getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                token = null;
            }

            filterChain.doFilter(request, response);
    }

}
