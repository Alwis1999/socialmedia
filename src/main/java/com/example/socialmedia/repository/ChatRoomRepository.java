package com.example.socialmedia.repository;
import com.example.socialmedia.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    // get room using room id
    ChatRoom findByRoomId(String roomId);
}