package com.example.socialmedia.controller;
import com.example.socialmedia.model.ChatMessage;
import com.example.socialmedia.model.ChatRoom;
import com.example.socialmedia.Playload.MessageRequest;
import com.example.socialmedia.repository.ChatRoomRepository;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/chat")
public class ChatController {

    private final ChatRoomRepository roomRepository;

    public ChatController(ChatRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // Join a chat room
    @PostMapping("/room/{roomId}/join")
    public ChatRoom joinRoom(@PathVariable String roomId, @RequestParam String username) {
        ChatRoom room = roomRepository.findByRoomId(roomId);

        if (room == null) {
            room = new ChatRoom();
            room.setRoomId(roomId);
        }

        if (!room.getParticipants().contains(username)) {
            room.getParticipants().add(username);
        }

        return roomRepository.save(room);
    }

    // Get all participants in a room
    @GetMapping("/room/{roomId}/participants")
    public List<String> getParticipants(@PathVariable String roomId) {
        ChatRoom room = roomRepository.findByRoomId(roomId);

        if (room == null) {
            throw new RuntimeException("Room not found!");
        }

        return room.getParticipants();
    }

    // Send a message to a specific room
    @MessageMapping("/sendMessage/{roomId}") // WebSocket endpoint: /app/sendMessage/{roomId}
    @SendTo("/topic/room/{roomId}") // Subscribers receive messages on this topic
    public ChatMessage sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest request) {

        ChatRoom room = roomRepository.findByRoomId(roomId);

        if (room == null) {
            throw new RuntimeException("Room not found!");
        }

        ChatMessage message = new ChatMessage(request.getSender(), request.getContent(), LocalDateTime.now());
        room.getMessages().add(message);

        roomRepository.save(room);
        return message;
    }

    // Retrieve all messages in a room
    @GetMapping("/room/{roomId}/messages")
    public List<ChatMessage> getMessages(@PathVariable String roomId) {
        ChatRoom room = roomRepository.findByRoomId(roomId);

        if (room == null) {
            throw new RuntimeException("Room not found!");
        }

        return room.getMessages();
    }
}