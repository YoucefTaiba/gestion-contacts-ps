package com.poliscrypts.project.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.poliscrypts.project.model.Role;
import com.poliscrypts.project.model.User;
import com.poliscrypts.project.repo.RoleRepository;
import com.poliscrypts.project.repo.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User findUserById(Long id) {
		return userRepository.getById(id);
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String rolename) {
		User user = userRepository.findByUsername(username);
		Role role = roleRepository.findByName(rolename);
		user.getRoles().add(role);
	}

	@Override
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {

		return userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found in database");
		} else {
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			user.getRoles().forEach(role -> {
				authorities.add(new SimpleGrantedAuthority(role.getName()));
			});
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					authorities);
		}
	}

}
