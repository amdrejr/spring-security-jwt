package com.amdrejr.springsecurityjwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amdrejr.springsecurityjwt.repositories.UserRepository;
import com.amdrejr.springsecurityjwt.security.UserAuthenticated;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // UserAuthenticated implementa userDetails, 
        // por isso pode ser retornado como tipo UserDetails
        return new UserAuthenticated(
            repository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("username"))
        );
    }
    
}
