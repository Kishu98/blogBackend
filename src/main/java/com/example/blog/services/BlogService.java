package com.example.blog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.models.Blog;
import com.example.blog.repositories.BlogRepo;

@Service
public class BlogService {

    private final BlogRepo blogRepo;

    @Autowired
    public BlogService(BlogRepo blogRepo) {
        this.blogRepo = blogRepo;
    }

    public Blog createBlog(Blog blog) {
        return blogRepo.save(blog);
    }

    public List<Blog> getAllBlogs() {
        return blogRepo.findAll();
    }

    public Optional<Blog> getBlogByID(Long id) {
        return blogRepo.findById(id);
    }

    public Optional<Blog> updateBlog(Long id, Blog updatedBlog) {
        return blogRepo.findById(id).map(blog -> {
            blog.setTitle(updatedBlog.getTitle());
            blog.setContent(updatedBlog.getContent());
            return blogRepo.save(blog);
        });
    }

    public Optional<Blog> deleteBlogById(Long id) {
        Optional<Blog> blog = blogRepo.findById(id);
        blogRepo.deleteById(id);
        return blog;
    }
}
