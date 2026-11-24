package com.jsp.ems.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ems.dto.RegisterRequest;
import com.jsp.ems.dto.VerifyOtpRequest;
import com.jsp.ems.service.OtpService;
import com.jsp.ems.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService us;
    private final OtpService os;

    public UserController(UserService us, OtpService os) {
        this.us = us;
        this.os = os;
    }
	
	
	@PostMapping("/register")
	public String register(@RequestBody RegisterRequest registerRequest) {
		//return "data inserted";
          return us.register(registerRequest);
	}
	
	@PostMapping("/verify-otp")
	public String verifyotp(@RequestBody VerifyOtpRequest verifyOtpRequest) {
		return os.VerifyOtp(verifyOtpRequest);
	}
	

}
