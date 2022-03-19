package com.poliscrypts.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poliscrypts.project.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
