package com.amdrejr.springsecurityjwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amdrejr.springsecurityjwt.entities.User;
import java.util.Optional;

// Repositório para manipular a tabela USERS da entidade User.
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
