package com.example.socialmedia.dto;

public class PostBody {
    private String content;

    public PostBody(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
