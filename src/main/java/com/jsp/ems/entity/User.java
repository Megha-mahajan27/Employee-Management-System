package com.jsp.ems.entity;



import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private String name;
	
	@Email
	@Column(unique=true)
	private String email;
	
	@Size(min=6, message = "Password should have minimum 6 characters")
	private String password;
	
	
	
	private String role;
	private boolean verified;
    private String otp;
    private LocalDateTime otpexpirytime;
}
