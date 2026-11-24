package com.jsp.ems.service;



import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jsp.ems.Repository.UserRepository;
import com.jsp.ems.dto.VerifyOtpRequest;
import com.jsp.ems.entity.User;
import com.jsp.ems.exception.InvalidOtpException;
import com.jsp.ems.exception.OtpExpiredException;
import com.jsp.ems.exception.UserNotFoundException;

@Service
public class OtpService {

    private UserRepository ur;

    public OtpService(UserRepository ur) {
        this.ur = ur;
    }

    public String VerifyOtp(VerifyOtpRequest request) {

        // Find user using email
        Optional<User> optionalUser =
                ur.findByEmail(request.getEmail());

        // Check user exists or not
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException(
                    "No user found with email: " + request.getEmail()
            );
        }

        User user = optionalUser.get();

        // Check OTP is empty
        if (request.getOtp() == null ||
                request.getOtp().isBlank()) {

            return "OTP is required, please enter OTP";
        }

        // Check whether OTP already verified
        if (user.getOtp() == null ||
                user.getOtpexpirytime() == null) {

            return "OTP is already verified";
        }

        // Check OTP matches
        if (!user.getOtp().equals(request.getOtp())) {

            throw new InvalidOtpException("Invalid OTP");
        }

        // Check OTP expiration
        if (LocalDateTime.now().isAfter(user.getOtpexpirytime())) {

            throw new OtpExpiredException("OTP expired");
        }

        // Verify user
        user.setOtp(null);
        user.setOtpexpirytime(null);
        user.setVerified(true);

        // Save updated user
        ur.save(user);

        return "OTP verified successfully";
    }
}
