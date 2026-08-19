package com.jsp.ems.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	private final JavaMailSender jms;


	public EmailService(JavaMailSender jms) {
		
		this.jms = jms;
	}
	public void sendOtp(String toEmail,String otp) {
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setTo(toEmail);
		simpleMailMessage.setSubject("OTP VERIFICATION");
		simpleMailMessage.setText("your OTP is "+""+otp);
		jms.send(simpleMailMessage);
	
		
	}

}
