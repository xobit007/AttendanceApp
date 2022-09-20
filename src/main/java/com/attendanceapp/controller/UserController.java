package com.attendanceapp.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.attendanceapp.entity.Admin;
import com.attendanceapp.entity.Attendance;
import com.attendanceapp.entity.Login;
import com.attendanceapp.entity.User;
import com.attendanceapp.repository.AdminRepository;
import com.attendanceapp.repository.AttendanceRepository;
import com.attendanceapp.repository.LoginRepository;
import com.attendanceapp.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private AttendanceRepository attendanceRepository;

	@GetMapping("/index")
	public String home() {
		return "index";
	}

	@GetMapping("/userLogin")
	public String login() {
		return "login";
	}

	@GetMapping("/registerUser")
	public String registerUser() {
		return "registerUser";
	}

	@GetMapping("/registerAdmin")
	public String registerAdmin() {
		return "registerAdmin";
	}

	@PostMapping("/createUser")
	public String createUser(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("emailId") String emailId, @RequestParam("phoneNo") String phoneNo) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setEmailId(emailId);
		user.setPhoneNo(phoneNo);
		userRepository.save(user);
		Login login = new Login();
		login.setUsername(user.getUsername());
		login.setPassword(user.getPassword());
		login.setRole("ROLE_Employee");
		loginRepository.save(login);

		return "redirect:/userLogin";
	}

	@PostMapping("/createAdmin")
	public String createAdmin(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("emailId") String emailId, @RequestParam("phoneNo") String phoneNo) {
		Admin admin = new Admin();
		admin.setUsername(username);
		admin.setPassword(bCryptPasswordEncoder.encode(password));
		admin.setEmailId(emailId);
		admin.setPhoneNo(phoneNo);
		adminRepository.save(admin);
		Login login = new Login();
		login.setUsername(admin.getUsername());
		login.setPassword(admin.getPassword());
		login.setRole("ROLE_Admin");
		loginRepository.save(login);
		return "redirect:/userLogin";
	}

	@PostMapping("/authenticate")
	public String authenticate(@RequestParam String username, @RequestParam String password, Model m) {
		Login l = loginRepository.findByUsername(username);
		boolean matches = bCryptPasswordEncoder.matches(password, l.getPassword());
		String role = l.getRole();

		

		if (matches == true && role.equalsIgnoreCase("ROLE_Admin")) {
			return "redirect:/viewUser";
		} else if (matches == true && role.equalsIgnoreCase("ROLE_Employee")) {
			LocalDate dt = LocalDate.now();
			DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String date = dft.format(dt);

			LocalTime tt = LocalTime.now();
			DateTimeFormatter tft = DateTimeFormatter.ofPattern("hh:mm:s a");
			String time = tft.format(tt);
			
			
			User user = userRepository.findByUsername(username);
			int userId = user.getUserId();
			Attendance attendance = attendanceRepository.checkIfSignedinAndSignedout(date, userId);
			String signInTime = attendance.getSignInTime();
			String signOutTime = attendance.getSignOutTime();

			m.addAttribute("date", date);
			m.addAttribute("time", time);
			m.addAttribute("userId", userId);

			if (signInTime != null && signOutTime != null) {
				return "logout";
			} else if (signInTime != null && signOutTime == null) {
				return "signout";
			} else if (signInTime == null && signOutTime == null) {
				return "signin";
			} else {
				return "invalid";
			}

		} else {
			return "invalid";
		}
	}

	@GetMapping("/saveSigninDetails/{userId}")
	public String saveSigninDetails(@PathVariable("userId") int userId, Model m) {

		LocalDate dt = LocalDate.now();
		DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String date = dft.format(dt);

		LocalTime tt = LocalTime.now();
		DateTimeFormatter tft = DateTimeFormatter.ofPattern("hh:mm:s a");
		String time = tft.format(tt);

		m.addAttribute("date", date);
		m.addAttribute("time", time);
		m.addAttribute("userId", userId);

		Integer updateSigninTime = attendanceRepository.updateSigninTime(time, date, userId);

		return "signout";
	}

	@GetMapping("/saveSignoutDetails/{userId}")
	public String saveSignOutDetails(@PathVariable("userId") int userId, Model m) {
		LocalDate dt = LocalDate.now();
		DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String date = dft.format(dt);

		LocalTime tt = LocalTime.now();
		DateTimeFormatter tft = DateTimeFormatter.ofPattern("hh:mm:s a");
		String time = tft.format(tt);

		m.addAttribute("date", date);
		m.addAttribute("time", time);
		m.addAttribute("userId", userId);

		Integer updateSignoutTime = attendanceRepository.updateSignoutTime(time, date, userId);
		return "logout";
	}

	@GetMapping("/getReport/{userId}")
	public String userReport(@PathVariable("userId") int userId, Model m) {
		Attendance attendance = attendanceRepository.findByUserId(userId);
		m.addAttribute("attendance", attendance);
		return "getReport";
	}

	@GetMapping("/viewUser")
	public String viewUser(Model m) {
		ArrayList<User> username = userRepository.getUsernameAndUserId();
		m.addAttribute("user", username);
		return "viewUser";
	}

	@GetMapping("/error")
	public String error() {
		return "error";
	}

}
