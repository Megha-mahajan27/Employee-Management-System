package com.jsp.ems.controller;






import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth2")
public class OAuth2Controller {

    // Check Google login details
    @GetMapping("/user")
    public Map<String, Object> getGoogleUser(
            @AuthenticationPrincipal OAuth2User oauth2User) {

        Map<String, Object> response = new HashMap<>();

        if (oauth2User == null) {
            response.put("message", "User not authenticated");
            return response;
        }

        response.put("message", "Google login successful");
        response.put("name", oauth2User.getAttribute("name"));
        response.put("email", oauth2User.getAttribute("email"));
        response.put("picture", oauth2User.getAttribute("picture"));

        return response;
    }
}

