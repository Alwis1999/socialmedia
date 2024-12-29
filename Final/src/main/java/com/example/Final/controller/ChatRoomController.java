
package com.example.Final.controller;

import com.example.Final.model.Chat;
import com.example.Final.model.ChatRoom;
import com.example.Final.repository.ChatRoomRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("http://localhost:5173")
public class ChatRoomController {

    private ChatRoomRepository roomRepository;

    public ChatRoomController(ChatRoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // create room
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomId) {

        if (roomRepository.findByRoomId(roomId) != null) {
            // room is already there
            return ResponseEntity.badRequest().body("Room already exists!");

        }

        // create new room
        ChatRoom room = new ChatRoom();
        room.setRoomId(roomId);

        try {
            // Save the chat room
            ChatRoom savedRoom = roomRepository.save(room);

            // Return the saved room
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRoom);
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();

            // Return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while saving the chat room: " + e.getMessage());
        }
    }

    // get room: join
    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(
            @PathVariable String roomId) {

        ChatRoom room = roomRepository.findByRoomId(roomId);
        if (room == null) {
            return ResponseEntity.badRequest()
                    .body("Room not found!!");
        }
        return ResponseEntity.ok(room);
    }

    // get messages of room

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Chat>> getMessages(
            @PathVariable String roomId,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int size) {
        ChatRoom room = roomRepository.findByRoomId(roomId);
        if (room == null) {
            return ResponseEntity.badRequest().build();
        }
        // get messages :
        // pagination
        List<Chat> messages = room.getMessages();
        int start = Math.max(0, messages.size() - (page + 1) * size);
        int end = Math.min(messages.size(), start + size);
        List<Chat> paginatedMessages = messages.subList(start, end);
        return ResponseEntity.ok(paginatedMessages);

    }

}
