package com.jsp.ems.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsp.ems.Repository.UserRepository;
import com.jsp.ems.dto.VerifyOtpRequest;
import com.jsp.ems.entity.User;
import com.jsp.ems.exception.InvalidOtpException;
import com.jsp.ems.exception.UserVerifiedException;

@Service
public class OtpService {
	
	private UserRepository ur;
	public OtpService(UserRepository ur) {
		this.ur = ur;
	
	}

	public String VerifyOtp(VerifyOtpRequest verifyOtpRequest) {
		Optional<User>optionaluser=ur.findByEmail(verifyOtpRequest.getEmail());
		if(optionaluser.isPresent()) {
			User user=optionaluser.get();
			if(user.getOtp()==null) {
				throw new UserVerifiedException("already verified");
			}
			if(!user.getOtp().equals(verifyOtpRequest.getOtp())) {
				throw new InvalidOtpException ("invalid otp");
			}
			
				if(LocalDateTime.now().isAfter(user.getOtpexpirytime())) {
					return "otp expired";
				}
				else {
				user.setVerified(true);
				user.setOtp(null);
				user.setOtpexpirytime(null);
				ur.save(user);
				return"otp verified successfully";
			}
		}else {
		    throw new UsernameNotFoundException("No user found");
		}
		
	}
}
	
	
