package com.amdrejr.springsecurityjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.amdrejr.springsecurityjwt.services.UserService;

@Configuration
public class Configurations implements CommandLineRunner {
    @Autowired
	UserService service;

    @Override
	public void run(String... args) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senha = encoder.encode("senha12345");
		System.out.println("senha -> " + senha);

		// service.save(new User("admin", senha));

		System.out.println("Usuários: " + service.findAll());
	}
}
