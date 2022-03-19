package com.poliscrypts.project.service;

import java.util.List;

import com.poliscrypts.project.model.Role;
import com.poliscrypts.project.model.User;

public interface UserService {

	User saveUser(User user);

	User findUserById(Long id);

	Role saveRole(Role role);

	void addRoleToUser(String username, String rolename);

	User getUser(String username);

	List<User> getUsers();
}
