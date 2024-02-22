package com.amdrejr.springsecurityjwt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amdrejr.springsecurityjwt.entities.User;
import com.amdrejr.springsecurityjwt.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public void save(User u) {
        repository.save(u);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found, username: " + username));
    }

    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }
    
    public User findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found, id: " + id));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void update(User u) {
        User entity = findById(u.getId());

        entity.setUsername(u.getUsername());
        entity.setPassword(u.getPassword());
        entity.setAccountNonExpired(u.getAccountNonExpired());
        entity.setAccountNonLocked(u.getAccountNonLocked());
        entity.setCredentialsNonExpired(u.getCredentialsNonExpired());
        entity.setEnabled(u.getEnabled());

        repository.save(entity);
    }
}
