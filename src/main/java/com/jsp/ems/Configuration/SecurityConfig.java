package com.jsp.ems.Configuration;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jsp.ems.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    // Password encryption
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication provider
    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    // Authentication manager
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
    }

    // Security configuration
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            // Disable CSRF for REST APIs
            .csrf(csrf -> csrf.disable())

            // JWT is stateless
            .sessionManagement(session ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            )

            // URL authorization
            .authorizeHttpRequests(auth -> auth

                // Public APIs - no JWT required
                .requestMatchers(
                    "/user/register",
                    "/user/verify-otp",
                    "/auth/**",
                    "/error"
                ).permitAll()

                // GET employee APIs
                .requestMatchers(
                    HttpMethod.GET,
                    "/employees/**"
                ).hasAnyRole("ADMIN", "USER")

                // Other employee APIs - ADMIN only
                .requestMatchers(
                    "/employees/**"
                ).hasRole("ADMIN")

                // Employee APIs - authenticated users
                .requestMatchers(
                    "/employee/**"
                ).authenticated()

                // All remaining APIs require authentication
                .anyRequest().authenticated()
            )

            // Authentication provider
            .authenticationProvider(authenticationProvider())

            // JWT filter
            .addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}






