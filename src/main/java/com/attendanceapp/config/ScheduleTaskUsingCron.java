package com.attendanceapp.config;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.attendanceapp.repository.AttendanceRepository;
import com.attendanceapp.repository.UserRepository;
import java.time.*;
import java.time.format.DateTimeFormatter;

@Configuration
@Component
@EnableScheduling
public class ScheduleTaskUsingCron {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AttendanceRepository attendanceRepository;

	@Scheduled(cron = "0 0 0 * * ?")
	public void insertIntoAttendance() {
		LocalDate dt = LocalDate.now();
		DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String date = dft.format(dt);

		ArrayList<Integer> userIdFromUser = userRepository.getUserIdFromUser();

		for (int i = 0; i < userIdFromUser.size(); i++) {
			Integer val = userIdFromUser.get(i);
			attendanceRepository.insertDateAndUserId(date, val);
		}
	}
	
	@Scheduled(cron = "0 0 20 * * ?")
	public void updateAttendanceIfAbsent() {
		attendanceRepository.insertIntoAttendanceIfNull();
	}
}
