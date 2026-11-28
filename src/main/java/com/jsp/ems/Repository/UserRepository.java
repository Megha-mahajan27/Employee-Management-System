package com.jsp.ems.Repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.ems.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Find user using email
    Optional<User> findByEmail(String email);

    // Delete user using email
    Optional<User> deleteByEmail(String email);

    // Find user using Google ID
    Optional<User> findByGoogleId(String googleId);

}
