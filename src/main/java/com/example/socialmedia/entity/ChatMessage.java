package com.example.socialmedia.entity;

<<<<<<< HEAD

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Document(collection = "chatmessages")
public class ChatMessage {
    @Id
    private String id;
    private String senderId;
    private String messageContent;
    private String receiverId;
    private LocalDateTime timestamp;
    private String chatRoomId; // Reference to the ChatRoom

    // Constructors, Getters, Setters
    public ChatMessage() {}

    public ChatMessage(String senderId, String messageContent, LocalDateTime timestamp, String chatRoomId) {
        this.senderId = senderId;
        this.messageContent = messageContent;
        this.timestamp = timestamp;
        this.chatRoomId = chatRoomId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    @Id
    private String id;
    private String senderId; // ID of the sender
    private String receiverId; // ID of the receiver
    private String message;
    private LocalDateTime timestamp;
>>>>>>> 4e77c164fe132508d1b54630b330c53dac3a55bc
}
