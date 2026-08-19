package com.jsp.ems.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
public class Employee {
	@Id
	@Email(message="enter valid email id")
	@NotBlank(message="It cannot  be null,empty,space")
	private String email;
	@NotBlank(message="It cannot be null,empty,space")
	private String name;
	@NotBlank(message="it cannot be null,empty,space")
	private String department;
	@Positive(message="salary should >=0")
	private double salary;
	
	

}
