package com.poliscrypts.project.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poliscrypts.project.model.Role;
import com.poliscrypts.project.model.User;
import com.poliscrypts.project.service.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class UserRessource {

	private UserServiceImpl userServiceImpl;

	@Autowired
	public UserRessource(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	@GetMapping("/user/all")
	public ResponseEntity<List<User>> findAllUsers() {
		return ResponseEntity.ok(userServiceImpl.getUsers());
	}

	@PostMapping("/user/add")
	public ResponseEntity<User> addUser(@RequestBody User User) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/add").toUriString());
		return ResponseEntity.created(uri).body(userServiceImpl.saveUser(User));
	}

	@PostMapping("/user/addrole")
	public ResponseEntity<User> saveRoleToUser(@RequestBody RoleToUserForm form) {
		userServiceImpl.addRoleToUser(form.getUsername(), form.getRolename());
		return ResponseEntity.ok().build();
	}

	@PostMapping("/role/save")
	public ResponseEntity<Role> addRole(@RequestBody Role role) {
		URI uri = URI
				.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
		return ResponseEntity.created(uri).body(userServiceImpl.saveRole(role));
	}

	@GetMapping("/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
		String authorizationHeader = request.getHeader(org.springframework.http.HttpHeaders.AUTHORIZATION);
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String refresh_token = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("mysectet".getBytes());
				JWTVerifier jwtVerifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = jwtVerifier.verify(refresh_token);
				String username = decodedJWT.getSubject();
				User user = userServiceImpl.getUser(username);
				String acesse_token = JWT.create().withSubject(user.getUsername())
						.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
						.sign(algorithm);
				Map<String, String> tokens = new HashMap<>();
				tokens.put("acesse_token", acesse_token);
				tokens.put("refresh_token", refresh_token);
				response.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
			} catch (Exception e) {
				response.setHeader("error refreshToken", e.getMessage());
				response.setStatus(org.springframework.http.HttpStatus.FORBIDDEN.value());
				Map<String, String> error = new HashMap<>();
				response.setContentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
				try {
					new ObjectMapper().writeValue(response.getOutputStream(), error);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			throw new RuntimeException("refresh token missing");
		}
	}

}

class RoleToUserForm {
	private String username;
	private String rolename;

	public RoleToUserForm(String username, String rolename) {
		this.username = username;
		this.rolename = rolename;
	}

	public String getUsername() {
		return username;
	}

	public String getRolename() {
		return rolename;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
