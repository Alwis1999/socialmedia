package com.example.socialmedia.config;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 3fbec058762d04386dbff9150d439978da76e527
import com.example.socialmedia.handler.ChatWebSocketHandler;
import com.example.socialmedia.repository.ChatMessageRepository;
import com.example.socialmedia.service.ChatRoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatRoomService chatRoomService;
    private final ChatMessageRepository chatMessageRepository;
    private final ObjectMapper objectMapper;

    public WebSocketConfig(ChatRoomService chatRoomService, ChatMessageRepository chatMessageRepository, ObjectMapper objectMapper) {
        this.chatRoomService = chatRoomService;
        this.chatMessageRepository = chatMessageRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ChatWebSocketHandler(chatRoomService, chatMessageRepository, objectMapper), "/ws/chat")
                .setAllowedOrigins("*");
<<<<<<< HEAD
=======
=======
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // Prefix for messages to subscribers
        registry.setApplicationDestinationPrefixes("/app"); // Prefix for messages from clients
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")
                .setAllowedOrigins("http://localhost:5173") // Add frontend origin for CORS
                .withSockJS(); // Enable SockJS fallback
>>>>>>> 4e77c164fe132508d1b54630b330c53dac3a55bc
>>>>>>> 3fbec058762d04386dbff9150d439978da76e527
    }
}
