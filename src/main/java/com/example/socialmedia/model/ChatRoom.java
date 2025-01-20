package com.example.socialmedia.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
    @Id
    private String id;// Mongo db : unique identifier
    private String roomId;
    private List<ChatMessage> messages = new ArrayList<>();
    private List<String> participants = new ArrayList<>(); // Usernames of participants

}
