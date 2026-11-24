package com.jsp.ems.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.ems.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	Optional<User>findByEmail(String email);
	Optional<User>deleteByEmail(String email);

}
