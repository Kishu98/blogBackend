package com.example.blog.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.models.Blog;
import com.example.blog.services.BlogService;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        return ResponseEntity.ok(blogService.createBlog(blog));
    }

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        Optional<Blog> blog = blogService.getBlogByID(id);
        return blog.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateblog(@PathVariable Long id, @RequestBody Blog updatedBlog) {
        Optional<Blog> blog = blogService.updateBlog(id, updatedBlog);
        return blog.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable Long id) {
        Optional<Blog> blog = blogService.deleteBlogById(id);
        return blog.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
