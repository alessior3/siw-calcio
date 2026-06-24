package it.uniroma3.siw.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthRestController {

    @GetMapping("/api/auth/me")
    public Map<String, String> getCurrentUser() {
        Map<String, String> response = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails user = (UserDetails) authentication.getPrincipal();
                response.put("username", user.getUsername());
            } else {
                response.put("username", authentication.getName());
            }
        } else {
            response.put("username", null);
        }
        return response;
    }
}
