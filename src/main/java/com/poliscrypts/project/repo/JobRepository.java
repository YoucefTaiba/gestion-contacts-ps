package com.poliscrypts.project.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poliscrypts.project.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
	void deleteJobById(Long id);
		 
	Optional< Job> findById(Long id);
 
}
