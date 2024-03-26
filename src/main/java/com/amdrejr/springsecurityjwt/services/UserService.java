package com.amdrejr.springsecurityjwt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amdrejr.springsecurityjwt.dto.UserDTO;
import com.amdrejr.springsecurityjwt.entities.User;
import com.amdrejr.springsecurityjwt.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public void save(User u) {
        if(repository.findByUsername(u.getUsername()) == null)
            repository.save(u);
    }

    public void save(UserDTO u) {
        User user = new User(u.getUsername(), u.getPassword(), u.getRoleId());

        repository.save(user);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
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

    public User update(User u) {
        User entity = findById(u.getId());

        entity.setUsername(u.getUsername());
        entity.setPassword(u.getPassword());
        entity.setAccountNonExpired(u.getAccountNonExpired());
        entity.setAccountNonLocked(u.getAccountNonLocked());
        entity.setCredentialsNonExpired(u.getCredentialsNonExpired());
        entity.setEnabled(u.getEnabled());
        entity.setRoles(u.getRoles());

        repository.save(entity);
        return entity;
    }
}
