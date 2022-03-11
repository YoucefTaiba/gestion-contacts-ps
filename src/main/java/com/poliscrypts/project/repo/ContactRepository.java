package com.poliscrypts.project.repo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poliscrypts.project.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	void deleteContactById(Long id);

	Optional<Contact> findContactById(Long id);

	@Query("select c from Contact c where c.nom like :x")
	public Page<Contact> searche(@Param("x") String mc, Pageable pageable);
}
