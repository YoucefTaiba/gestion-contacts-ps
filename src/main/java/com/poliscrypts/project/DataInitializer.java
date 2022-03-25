package com.poliscrypts.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.poliscrypts.project.model.Company;
import com.poliscrypts.project.model.Contact;
import com.poliscrypts.project.model.Role;
import com.poliscrypts.project.model.User;
import com.poliscrypts.project.service.CompanyService;
import com.poliscrypts.project.service.ContactService;
import com.poliscrypts.project.service.UserServiceImpl;

@Component
public class DataInitializer implements CommandLineRunner {
	private final UserServiceImpl userRepository;
	private final CompanyService companyService;
	private final ContactService contactService;

	@Autowired
	public DataInitializer(UserServiceImpl userRepository, CompanyService companyService,
			ContactService contactService) {
		this.userRepository = userRepository;
		this.companyService = companyService;
		this.contactService = contactService;
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.saveUser(new User("taiba", "taiba", "123"));
		userRepository.saveUser(new User("bessaha", "bessaha", "123"));
		userRepository.saveRole(new Role("ROLE_MANAGER"));
		userRepository.saveRole(new Role("ROLE_USER"));
		userRepository.addRoleToUser("taiba", "ROLE_MANAGER");
		userRepository.addRoleToUser("taiba", "ROLE_USER");
		userRepository.addRoleToUser("bessaha", "ROLE_USER");
		companyService.addCompany(new Company("company1", "adress", .19F));
		companyService.addCompany(new Company("company2", "adress", .17F));
		contactService.addContact(new Contact("contact1","youcef","adress"));

	}

}