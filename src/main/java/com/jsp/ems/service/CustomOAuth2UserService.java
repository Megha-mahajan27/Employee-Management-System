package com.jsp.ems.service;




import java.util.Optional;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.jsp.ems.Repository.UserRepository;
import com.jsp.ems.entity.User;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {

        OAuth2User oauth2User = super.loadUser(userRequest);

        // Get Google user details
        String googleId = oauth2User.getAttribute("sub");
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String picture = oauth2User.getAttribute("picture");

        Boolean emailVerified =
                oauth2User.getAttribute("email_verified");

        // Validate Google details
        if (googleId == null || email == null ||
                !Boolean.TRUE.equals(emailVerified)) {

            throw new OAuth2AuthenticationException(
                    "Google account email is not verified");
        }

        // First check Google ID
        Optional<User> existingGoogleUser =
                userRepository.findByGoogleId(googleId);

        if (existingGoogleUser.isPresent()) {

            User user = existingGoogleUser.get();

            user.setName(name);
            user.setProfilePicture(picture);

            userRepository.save(user);

        } else {

            // Check whether email already exists
            Optional<User> existingEmailUser =
                    userRepository.findByEmail(email);

            if (existingEmailUser.isPresent()) {

                User user = existingEmailUser.get();

                // Link Google account to existing account
                user.setGoogleId(googleId);
                user.setAuthProvider("LOCAL_GOOGLE");
                user.setProfilePicture(picture);

                userRepository.save(user);

            } else {

                // Register new Google user
                User user = new User();

                user.setName(name);
                user.setEmail(email);
                user.setGoogleId(googleId);
                user.setAuthProvider("GOOGLE");
                user.setProfilePicture(picture);

                // Google has verified the email
                user.setVerified(true);

                // Default user role
                user.setRole("ROLE_USER");

                // No local password is needed for Google login
                user.setPassword(null);

                userRepository.save(user);
            }
        }

        return oauth2User;
    }
}
