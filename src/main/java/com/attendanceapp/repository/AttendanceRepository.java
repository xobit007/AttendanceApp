package com.attendanceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.attendanceapp.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
	
	
	@Query(value = "select * from attendance where user_id=?", nativeQuery = true)
	public Attendance findByUserId(int userId);

	@Modifying
	@Transactional
	@Query(value = "insert into attendance(date, user_id) values(?1, ?2)", nativeQuery = true)
	public int insertDateAndUserId(String date, int userId);

	@Modifying
	@Transactional
	@Query(value = "update attendance set sign_in_time = ?1 WHERE date = ?2 and user_id = ?3 ", nativeQuery = true)
	public Integer updateSigninTime(String time, String date, int userId);
	
	@Modifying
	@Transactional
	@Query(value = "update attendance set sign_out_time = ?1 WHERE date = ?2 and user_id = ?3 ", nativeQuery = true)
	public Integer updateSignoutTime(String time, String date, int userId);
		
	@Query(value = "select * from attendance where date = ?1 and user_id = ?2", nativeQuery = true)
	public Attendance checkIfSignedinAndSignedout(String date, int userId);
	
	@Modifying
	@Transactional
	@Query(value = "update attendance set sign_in_time = 'Absent', sign_out_time = 'Absent' where sign_in_time is null and sign_out_time is null", nativeQuery = true)
	public Integer insertIntoAttendanceIfNull();
}
