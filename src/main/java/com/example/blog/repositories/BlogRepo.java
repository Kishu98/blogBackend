package com.example.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.models.Blog;

public interface BlogRepo extends JpaRepository<Blog, Long> {
    // TODO
    // Search by title of the blog post
}
