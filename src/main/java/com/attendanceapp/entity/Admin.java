package com.attendanceapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int adminId;
	@Column(unique = true)
	private String username;
	private String password;
	@Column(unique = true)
	private String emailId;
	private String phoneNo;
	private String role = "ROLE_Admin";

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(int adminId, String username, String password, String emailId, String phoneNo, String role) {
		super();
		this.adminId = adminId;
		this.username = username;
		this.password = password;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.role = role;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
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
		return "Admin [adminId=" + adminId + ", username=" + username + ", password=" + password + ", emailId="
				+ emailId + ", phoneNo=" + phoneNo + ", role=" + role + "]";
	}

}
