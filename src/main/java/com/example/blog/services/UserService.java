package com.example.blog.services;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog.models.User;
import com.example.blog.repositories.UserRepo;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(String username, String password) {
        if (userRepo.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        String hashPassword = passwordEncoder.encode(password);
        User user = new User(username, hashPassword);
        return userRepo.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public boolean authenticateUser(String username, String password) {
        Optional<User> checkUser = userRepo.findByUsername(username);

        if (checkUser.isPresent()) {
            User user = checkUser.get();
            return passwordEncoder.matches(password, user.getPassword());
        }

        return false;
    }
}
