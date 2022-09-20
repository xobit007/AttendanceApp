package com.attendanceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendanceapp.entity.Login;
import com.attendanceapp.entity.User;

public interface LoginRepository extends JpaRepository<Login, Integer>{
	public Login findByUsername(String username);
}
