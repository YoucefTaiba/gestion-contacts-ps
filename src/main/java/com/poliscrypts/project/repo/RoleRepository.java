package com.poliscrypts.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poliscrypts.project.model.Role; 

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
