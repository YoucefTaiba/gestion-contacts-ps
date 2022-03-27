package com.poliscrypts.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	public Page<Company> findAllCompanys(Pageable paging) {
		return companyRepository.findAll(paging);
	}

	public Page<Company> findCompanyByName(String name, Pageable paging) {
		return companyRepository.findCompanyByNom(name, paging);
	}

	public Company addCompany(Company company) {

		return companyRepository.save(company);

	}

	public Company findCompanyById(Long id) {
		return companyRepository.findCompanyById(id)
				.orElseThrow(() -> new CompanyNotFoundException("Company id :" + id + " was not found "));
	}

	public Optional<Company> updateCompany(Long id, Company company) {
		return companyRepository.findCompanyById(id).map(old -> {
			Company update = new Company(id, company.getNom(), company.getAdresse(), company.getTva());
			return companyRepository.save(update);
		});
	}

	public void deleteCompany(Long id) {
		companyRepository.deleteCompanyById(id);
	}

}
