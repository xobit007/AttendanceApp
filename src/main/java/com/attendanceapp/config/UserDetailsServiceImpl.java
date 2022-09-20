package com.attendanceapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.attendanceapp.entity.Admin;
import com.attendanceapp.entity.Login;
import com.attendanceapp.entity.User;
import com.attendanceapp.repository.AdminRepository;
import com.attendanceapp.repository.LoginRepository;
import com.attendanceapp.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login login = this.loginRepository.findByUsername(username);

		if (login == null) {
			throw new UsernameNotFoundException("User not found");
		}
				
		return new UserDetailsImpl(login);
	}

}
