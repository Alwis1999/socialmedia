package com.example.socialmedia.entity;

import lombok.Data;

@Data
public class CommentBody {
    private String content;

    public CommentBody(String content) {
        this.content = content;
    }
}
