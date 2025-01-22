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
        this.user = getLoggedUsername();
        this.commentAt = LocalDateTime.now();
    }

    private String getLoggedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName(); // Return the username
        }
        return null; // Return null if not authenticated
    }
}
