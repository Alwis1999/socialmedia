package com.example.socialmedia.repository;

import com.example.socialmedia.entity.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findBySenderIdAndReceiverId(String senderId, String receiverId);
<<<<<<< HEAD
=======

    List<ChatMessage> findByReceiverIdAndSenderId(String receiverId, String senderId);
>>>>>>> 4e77c164fe132508d1b54630b330c53dac3a55bc
}
