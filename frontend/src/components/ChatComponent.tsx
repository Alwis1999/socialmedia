import React, { useState, useEffect } from "react";
import client from "../services/chatService";

interface ChatMessage {
    senderId: string;
    receiverId: string;
    message: string;
    timestamp?: string;
}

const ChatComponent: React.FC = () => {
    const [messages, setMessages] = useState<ChatMessage[]>([]);
    const [input, setInput] = useState("");

    useEffect(() => {
        client.onConnect = () => {
            client.subscribe("/topic/messages", (message) => {
                const receivedMessage = JSON.parse(message.body) as ChatMessage;
                setMessages((prevMessages) => [...prevMessages, receivedMessage]);
            });
        };
    }, []);

    const sendMessage = () => {
        const message: ChatMessage = {
            senderId: "user1", // Replace with actual user ID
            receiverId: "user2", // Replace with actual friend ID
            message: input,
        };

        client.publish({
            destination: "/app/chat",
            body: JSON.stringify(message),
        });

        setInput("");
    };

    return (
        <div>
            <h3>Chat</h3>
            <div>
                {messages.map((msg, index) => (
                    <p key={index}>
                        <strong>{msg.senderId}:</strong> {msg.message}
                    </p>
                ))}
            </div>
            <input
                type="text"
                value={input}
                onChange={(e) => setInput(e.target.value)}
            />
            <button onClick={sendMessage}>Send</button>
        </div>
    );
};

export default ChatComponent;
