package com.attendanceapp;

import java.beans.Expression;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.attendanceapp.entity.Attendance;
import com.attendanceapp.entity.User;
import com.attendanceapp.repository.AttendanceRepository;
import com.attendanceapp.repository.UserRepository;

@SpringBootApplication
public class AttendanceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceAppApplication.class, args);


		
		

	}
	

}