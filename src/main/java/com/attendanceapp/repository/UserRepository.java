package com.attendanceapp.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.attendanceapp.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsernameAndPassword(String username, String password);

	public User findByUserId(int id);
	
	public User findByUsername(String username);
	
	@Query(value = "select * from user", nativeQuery = true)
	public ArrayList<User> getUsernameAndUserId();	
	
	@Query(value = "select user_id from user", nativeQuery = true)
	public ArrayList<Integer> getUserIdFromUser();
	
}
