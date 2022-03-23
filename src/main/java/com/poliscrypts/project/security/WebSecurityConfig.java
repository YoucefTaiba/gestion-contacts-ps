package com.poliscrypts.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		CustomAuthentificationFilter customAuthentificationFilter = new CustomAuthentificationFilter(
				authenticationManagerBean()); 
		customAuthentificationFilter.setFilterProcessesUrl("/api/login");
		httpSecurity.cors();
		httpSecurity.csrf().disable();
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.authorizeRequests().antMatchers("/api/login").permitAll();
		httpSecurity.authorizeRequests().antMatchers("/api/user/**").permitAll();
//		httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/company/all").hasAnyAuthority("ROLE_USER");
//		httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/contact/all").hasAnyAuthority("ROLE_USER");
//		httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users").hasAnyAuthority("ROLE_USER");
//		httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/api/user/save").hasAnyAuthority("ROLE_MANAGER");
//		httpSecurity.authorizeRequests().antMatchers(HttpMethod.POST, "/api/role/addtouser")
//				.hasAnyAuthority("ROLE_MANAGER");
		httpSecurity.authorizeRequests().anyRequest().authenticated();
		httpSecurity.addFilter(customAuthentificationFilter);
		httpSecurity.addFilterBefore(new CustomAuthorisationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
 
	
}
