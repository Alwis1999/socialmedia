package com.example.Final.controller;

import com.example.Final.model.Chat;
import com.example.Final.model.ChatRoom;
import com.example.Final.Playload.MessageRequest;
import com.example.Final.repository.ChatRoomRepository;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Controller
@CrossOrigin("http://localhost:5173")
public class ChatController {

    private ChatRoomRepository roomRepository;

    public ChatController(ChatRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // for sending and receiving messages
    @MessageMapping("/sendMessage/{roomId}") // /app/sendMessage/roomId
    @SendTo("/topic/room/{roomId}") // subscribe
    public Chat sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest request) {

        ChatRoom room = roomRepository.findByRoomId(request.getRoomId());
        Chat message = new Chat();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());
        if (room != null) {
            room.getMessages().add(message);
            roomRepository.save(room);
        } else {
            throw new RuntimeException("room not found !!");
        }

        return message;

    }
}
