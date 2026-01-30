package org.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class AuthController {

    // Simple auth - in production, use proper auth with database
    @Value("${admin.username:admin}")
    private String adminUsername;

    @Value("${admin.password:admin123}")
    private String adminPassword;

    @Value("${company.name:MyMovingCompany}")
    private String companyName;

    // GET /login - serves login page
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    // GET /admin/calendar - serves admin calendar (protected)
    @GetMapping("/admin/calendar")
    public String showAdminCalendar() {
        return "admin-calendar";
    }

    // POST /api/auth/login - authenticate user
    @PostMapping("/api/auth/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            Map<String, String> response = new HashMap<>();
            response.put("token", UUID.randomUUID().toString());
            response.put("companyName", companyName);
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }
}
