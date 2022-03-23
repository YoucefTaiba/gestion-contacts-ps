package com.poliscrypts.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;  

@SpringBootApplication
public class GestionContactePsApplication { 
	
	public static void main(String[] args) { 
		SpringApplication.run(GestionContactePsApplication.class, args);
	}

	//Resolve the cyclic problem (circular bean dependency)
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} 
	}
