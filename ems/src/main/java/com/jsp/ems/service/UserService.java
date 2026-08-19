package com.jsp.ems.service;


import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.ems.Repository.UserRepository;
import com.jsp.ems.dto.RegisterRequest;
import com.jsp.ems.entity.User;
import com.jsp.ems.util.OtpGenerator;

@Service
public class UserService {
	
	private UserRepository ur;
	private EmailService es;
	private PasswordEncoder passwordEncoder;

	
	
	
	
	public UserService(UserRepository ur, EmailService es, PasswordEncoder passwordEncoder) {
		this.ur = ur;
		this.es = es;
		this.passwordEncoder=passwordEncoder;
	}

	public String register(RegisterRequest rr) {
		Optional<User>ou=ur.findByEmail(rr.getEmail());
		if(ou.isPresent()) {
			return "email_id already exists";
		}else {
			User user=new User();
			user.setName(rr.getName());
			user.setEmail(rr.getEmail());
			user.setPassword(passwordEncoder.encode(rr.getPassword()));
			user.setPassword(rr.getPassword());
			user.setRole("USER_ROLE");
			user.setVerified(false);
			String otp=OtpGenerator.generateOtp();
			user.setOtp(otp);
			user.setOtpexpirytime(LocalDateTime.now().plusMinutes(5));
			ur.save(user);
			
			es.sendOtp(rr.getEmail(),otp);
			return "please check your email for otp";
			
			
		}
	}

	
	
	

}
