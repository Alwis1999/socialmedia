package com.example.socialmedia.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "posts")
public class Post {

    @Id
    private String id;
    private String content;
    private String user;
    private LocalDateTime createdAt;
    private List<Comment> comments; // Initialize the list;

    public Post(String content) {
        this.content = content;

        // Get username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            user = authentication.getName(); // This will return the username
        }
        else {
            user = null;
        }
        this.createdAt = LocalDateTime.now();
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}