package com.poliscrypts.project.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poliscrypts.project.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	void deleteCompanyById(Long id);

	Optional<Company> findCompanyById(Long id);

}
