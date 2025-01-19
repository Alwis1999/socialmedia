package com.example.Final.repository;

import com.example.Final.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    // get room using room id
    ChatRoom findByRoomId(String roomId);
}
