package com.amdrejr.springsecurityjwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amdrejr.springsecurityjwt.entities.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> { }
