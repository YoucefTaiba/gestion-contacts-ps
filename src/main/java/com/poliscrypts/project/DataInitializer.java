package com.poliscrypts.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.poliscrypts.project.model.Role;
import com.poliscrypts.project.model.User;
import com.poliscrypts.project.service.UserServiceImpl;

@Component
public class DataInitializer implements CommandLineRunner {
	private final UserServiceImpl userRepository;

	@Autowired
	public DataInitializer(UserServiceImpl userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.saveUser(new User("taiba","taiba","123"));
		userRepository.saveUser(new User("bessaha","bessaha","123"));
		userRepository.saveRole(new Role("ROLE_MANAGER"));
		userRepository.saveRole(new Role("ROLE_USER"));
		userRepository.addRoleToUser("taiba", "ROLE_MANAGER");
		userRepository.addRoleToUser("taiba", "ROLE_USER");
		userRepository.addRoleToUser("bessaha", "ROLE_USER");

	}

}