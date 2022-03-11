package com.poliscrypts.project.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poliscrypts.project.exceptions.CompanyNotFoundException;
import com.poliscrypts.project.model.Company;
import com.poliscrypts.project.repo.CompanyRepository;

@Service
@Transactional
public class CompanyService {

	private final CompanyRepository companyRepository;

	@Autowired
	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	public Company addCompany(Company company) {

		return companyRepository.save(company);

	}

	public Company findCompanyById(Long id) {
		return companyRepository.findCompanyById(id)
				.orElseThrow(() -> new CompanyNotFoundException("Company id :" + id + " was not found "));
	}

	public List<Company> findAllCompanys() {
		return companyRepository.findAll();
	}

	public Company updateCompany(Company company) {
		return companyRepository.save(company);
	}

	public void deleteCompany(Long id) {
		companyRepository.deleteCompanyById(id);
	} 
	
}
