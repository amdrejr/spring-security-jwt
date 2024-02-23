package com.amdrejr.springsecurityjwt.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amdrejr.springsecurityjwt.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository repository;

    Logger logger = Logger.getLogger(UserDetailsServiceImpl.class.getName());

    @Override
    public UserDetails loadUserByUsername(String username) {
        logger.info("load User by username: " + username);
        
        return repository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Not found username: " + username));
    }
}
