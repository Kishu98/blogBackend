package com.example.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.models.User;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
