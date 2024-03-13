package com.amdrejr.springsecurityjwt.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.amdrejr.springsecurityjwt.entities.Role;
import com.amdrejr.springsecurityjwt.entities.User;
import com.amdrejr.springsecurityjwt.services.UserService;

@Configuration
public class Configurations implements CommandLineRunner {
    @Autowired
	UserService service;
	
	// Encriptador de senhas
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
	public void run(String... args) throws Exception {

		// Criando os roles
		Role roleAdmin = new Role(1, "ROLE_ADMIN");
		Role roleManager = new Role(5, "ROLE_MANAGER");
		Role roleUser = new Role(10, "ROLE_USER");

		// Criando os usuários
		User admin = createUser("admin", "admin", Arrays.asList(roleAdmin));
		User manager = createUser("manager", "manager", Arrays.asList(roleManager));
		User user = createUser("user", "user", Arrays.asList(roleUser));
		User teste = createUser("teste", "teste", Arrays.asList(roleUser, roleAdmin));

		// Salvando no banco
		service.save(admin);
		service.save(manager);
		service.save(user);
		service.save(teste);

		System.out.println("Usuários: " + service.findAll());
		
		System.out.println("TESTE: " + teste.getAuthorities());
		System.out.println("ADMIN: " + admin.getAuthorities());

	}

	// Método para facilitar a criação do obj User
	private User createUser(String username, String password, List<Role> roles) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(encoder.encode(password));
		user.setRoles(roles);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		return user;
	}
}
