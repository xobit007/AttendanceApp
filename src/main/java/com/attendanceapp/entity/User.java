package com.attendanceapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(unique = true)
	private String username;
	private String password;
	@Column(unique = true)
	private String emailId;
	private String phoneNo;
	private String role = "ROLE_Employee";

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int userId, String username, String password, String emailId, String phoneNo, String role) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", emailId=" + emailId
				+ ", phoneNo=" + phoneNo + ", role=" + role + "]";
	}

}