<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 3fbec058762d04386dbff9150d439978da76e527
    package com.example.socialmedia.controller;

    import com.example.socialmedia.dto.ChatRoomRequestDTO;
    import com.example.socialmedia.entity.ChatMessage;
    import com.example.socialmedia.entity.ChatRoom;
    import com.example.socialmedia.repository.ChatMessageRepository;
    import com.example.socialmedia.service.ChatService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;
    import java.time.LocalDateTime;

    @RestController
    @RequestMapping("/ws")
    public class ChatController {

        private final ChatMessageRepository chatMessageRepository;

        @Autowired
        private ChatService chatService;

        // Constructor injection for repository
        public ChatController(ChatMessageRepository chatMessageRepository) {
            this.chatMessageRepository = chatMessageRepository;
        }

        // Create or fetch a chat room for two users
        @PostMapping("/create")
        public ChatRoom createChatRoom(@RequestBody ChatRoomRequestDTO chatRoomRequest) {
            return chatService.createChatRoomIfNotExists(chatRoomRequest.getToUserID());
        }

        // Get or create a chat room for two users
        @PostMapping("/chatroom")
        public ChatRoom getOrCreateChatRoom(@RequestBody ChatRoomRequestDTO requestDTO) {
            return chatService.getOrCreateChatRoom(requestDTO.getToUserID());
        }

        @PostMapping("/send")
        public ChatMessage sendMessage(@RequestBody ChatMessage message, @RequestParam String chatRoomId) {
            System.out.println("\n\n\n"+"this method id run"+"\n\n\n");
            message.setChatRoomId(chatRoomId);
            message.setTimestamp(LocalDateTime.now()); // Ensure timestamp is set

            // Save the message in ChatMessage repository
            ChatMessage savedMessage = chatMessageRepository.save(message);

            // Save the message to the ChatRoom
            chatService.saveMessageToChatRoom(chatRoomId, savedMessage);

            return savedMessage;
        }

    }
<<<<<<< HEAD
=======
=======
package com.example.socialmedia.controller;

import com.example.socialmedia.entity.ChatMessage;
import com.example.socialmedia.repository.ChatMessageRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ChatController {
    private final ChatMessageRepository chatMessageRepository;

    public ChatController(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        message.setTimestamp(LocalDateTime.now());
        chatMessageRepository.save(message);
        return message;
    }
}
>>>>>>> 4e77c164fe132508d1b54630b330c53dac3a55bc
>>>>>>> 3fbec058762d04386dbff9150d439978da76e527
