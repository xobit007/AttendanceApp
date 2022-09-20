package com.attendanceapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.data.annotation.CreatedDate;

@Entity
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int attendanceId;
	@CreatedDate
	private String date;
	private String signInTime;
	private String signOutTime;

	
	@OneToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "userId")
	private User user;

	@Override
	public String toString() {
		return "Attendance [attendanceId=" + attendanceId + ", date=" + date + ", signInTime=" + signInTime
				+ ", signOutTime=" + signOutTime + ", user=" + user + "]";
	}

	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attendance(int attendanceId, String date, String signInTime, String signOutTime, User user) {
		super();
		this.attendanceId = attendanceId;
		this.date = date;
		this.signInTime = signInTime;
		this.signOutTime = signOutTime;
		this.user = user;
	}

	public int getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSignInTime() {
		return signInTime;
	}

	public void setSignInTime(String signInTime) {
		this.signInTime = signInTime;
	}

	public String getSignOutTime() {
		return signOutTime;
	}

	public void setSignOutTime(String signOutTime) {
		this.signOutTime = signOutTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

