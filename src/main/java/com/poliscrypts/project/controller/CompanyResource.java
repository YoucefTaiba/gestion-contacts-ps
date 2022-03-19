package com.poliscrypts.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poliscrypts.project.model.Company;
import com.poliscrypts.project.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyResource {
	private final CompanyService companyService;

	@Autowired
	public CompanyResource(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Company>> getAllCompanys() {
		List<Company> lisCompanys = companyService.findAllCompanys();
		return new ResponseEntity<>(lisCompanys, HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable("id") Long id) {
		Company company = companyService.findCompanyById(id);
		return new ResponseEntity<>(company, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Company> addCompany(@RequestBody Company newCompany) {
		Company company = companyService.addCompany(newCompany);
		return new ResponseEntity<>(company, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
		Company updateCompany = companyService.addCompany(company);
		return new ResponseEntity<>(updateCompany, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Company> deleteCompany(@PathVariable("id") Long id) {
		companyService.deleteCompany(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
