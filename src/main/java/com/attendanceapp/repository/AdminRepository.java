package com.attendanceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.attendanceapp.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	public Admin findByUsername(String username);

}
