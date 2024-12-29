package com.example.Final.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Chat {

    private String sender;
    private String content;
    private LocalDateTime timeStamp;

    public Chat(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timeStamp = LocalDateTime.now();
    }
}