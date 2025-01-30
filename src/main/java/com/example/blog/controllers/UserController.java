package com.example.blog.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.models.User;
import com.example.blog.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        try {
            User newUser = userService.registerUser(username, password);
            return ResponseEntity.ok(Map.of("message", "User registered Successfully", "userId", newUser.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        boolean isAuthenticated = userService.authenticateUser(username, password);

        if (isAuthenticated) {
            return ResponseEntity.ok(Map.of("message", "Login successful"));
        } else {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid username or password"));
        }
    }

}
