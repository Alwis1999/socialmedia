package com.example.socialmedia.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

@Data
@Document
public class Comment {
    @Id
    private String id;
    private String postId;
    private String comment;
    private String user;
    private LocalDateTime commentAt;

    public Comment(String comment, String postId) {
        this.postId = postId;
        this.comment = comment;

        // Get username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            user = authentication.getName(); // This will return the username
        }
        else {
            user = null;
        }
        this.commentAt = LocalDateTime.now();
    }
}
