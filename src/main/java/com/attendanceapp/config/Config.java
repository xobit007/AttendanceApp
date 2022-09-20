package com.attendanceapp.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.util.UrlPathHelper;

@Configuration
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().and()
		.httpBasic().and().authorizeRequests().antMatchers("/**").permitAll()
		.antMatchers("/","/userLogin","/verify","/signin","/signout").hasRole("Employee")
		.antMatchers("/","/userLogin","/registerUser","/registerAdmin","/verify","/signin","/signout","/createUser","/createAdmin","/viewUser").hasRole("Admin").anyRequest().authenticated().and().formLogin()
		.and().logout().logoutSuccessHandler(new LogoutSuccessHandler() {
			
			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
					throws IOException, ServletException {
				System.out.println(authentication.getName() + ", you have logged out.");
				UrlPathHelper helper = new UrlPathHelper();
				String contextPath = helper.getContextPath(request);
				response.sendRedirect(contextPath + "/userLogin");
				
			}
		}).permitAll();
	}

}
