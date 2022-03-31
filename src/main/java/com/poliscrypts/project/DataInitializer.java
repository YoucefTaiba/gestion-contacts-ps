package com.poliscrypts.project;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.poliscrypts.project.model.Company;
import com.poliscrypts.project.model.Contact;
import com.poliscrypts.project.model.Job;
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
		Company company1 = new Company("company1", "adress", .19F);
		companyService.addCompany(company1);
		Company company2 = new Company("company2", "adress", .17F);
		companyService.addCompany(company2);
		companyService.addCompany(new Company("company3", "adress", .19F));
		companyService.addCompany(new Company("company4", "adress", .17F));
		companyService.addCompany(new Company("company5", "adress", .19F));
		companyService.addCompany(new Company("company6", "adress", .17F));
		Job job = new Job("job1", company1);
		Job job2 = new Job("job2", company2, true, .25F);
		Contact c = new Contact("taiba", "youcef", "adress1");
		contactService.addContact(c);
		Contact c2 = new Contact("taiba", "adam", "adress2");
		contactService.addContact(c2);
		contactService.addJobToContact(c.getId(), job);
		contactService.addJobToContact(c.getId(), job2);
		contactService.findAllJobs(c.getId()).forEach((j) -> {
			System.out.println(j);
		});
		;

	}

}