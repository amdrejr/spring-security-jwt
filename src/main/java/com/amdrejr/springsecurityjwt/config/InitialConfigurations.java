package com.amdrejr.springsecurityjwt.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.amdrejr.springsecurityjwt.entities.Role;
import com.amdrejr.springsecurityjwt.entities.User;
import com.amdrejr.springsecurityjwt.services.RoleService;
import com.amdrejr.springsecurityjwt.services.UserService;

@Configuration
public class InitialConfigurations implements CommandLineRunner {
    @Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	// Encriptador de senhas
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
	public void run(String... args) throws Exception {

		// Criando os roles
		Role roleAdmin = new Role(1, "ADMIN");
		Role roleManager = new Role(5, "MANAGER");
		Role roleUser = new Role(10, "USER");
		
		// Salvando no banco
		roleService.save(roleAdmin);
		roleService.save(roleManager);
		roleService.save(roleUser);

		// Criando os usuários
		User admin = createUser("admin", "admin", Arrays.asList(roleService.findById(1)));
		User manager = createUser("manager", "manager", Arrays.asList(roleService.findById(5)));
		User user = createUser("user", "user", Arrays.asList(roleService.findById(10)));
		User teste = createUser("teste", "teste", Arrays.asList(roleService.findById(5), roleService.findById(1)));

		// Salvando no banco
		userService.save(admin);
		userService.save(manager);
		userService.save(user);
		userService.save(teste);

		System.out.println("Usuários: " + userService.findAll());
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
