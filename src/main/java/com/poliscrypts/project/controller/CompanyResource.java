package com.poliscrypts.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poliscrypts.project.model.Company;
import com.poliscrypts.project.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("api/company")
public class CompanyResource {
	private final CompanyService companyService;

	@Autowired
	public CompanyResource(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping("/all")
	public ResponseEntity<Map<String, Object>> getAllCompanys(@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
		Pageable paging = PageRequest.of(page, size);
		List<Company> lisCompanys = new ArrayList<Company>();
		Page<Company> pageCompanys;
		try {
			if (name == null || name.isEmpty()) {
				pageCompanys = companyService.findAllCompanys(paging);
			} else {
				pageCompanys = companyService.findCompanyByName(name, paging);
			}
			Map<String, Object> response = new HashMap<>();
			lisCompanys = pageCompanys.getContent();
			response.put("companys", lisCompanys);
			response.put("currentPage", pageCompanys.getNumber());
			response.put("totalItems", pageCompanys.getTotalElements());
			response.put("totalPages", pageCompanys.getTotalPages());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable("id") Long id) {
		Company company = companyService.findCompanyById(id);
		return new ResponseEntity<>(company, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Company> addCompany(@RequestBody Company newCompany) {
		return new ResponseEntity<>(companyService.addCompany(newCompany), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Optional<Company>> updateCompany(@PathVariable Long id, @RequestBody Company company) {
		Optional<Company> updateCompany = companyService.updateCompany(id, company);
		return new ResponseEntity<>(updateCompany, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Company> deleteCompany(@PathVariable("id") Long id) {
		companyService.deleteCompany(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
