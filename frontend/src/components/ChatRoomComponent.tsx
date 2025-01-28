import React, { useEffect, useState, useRef } from "react";
import "../styles/ChatRoomComponent.css";

interface ChatRoom {
  id: string;
  user1Id: string;
  user2Id: string;
  messages: ChatMessage[];
}

interface ChatMessage {
  id: string;
  senderId: string;
  messageContent: string;
  receiverId: string;
  timestamp: string;
  chatRoomId: string;
}

type Friend = {
  username: string;
  objectId: string;
};

const ChatRoomComponent: React.FC = () => {
  const [selectedRoom, setSelectedRoom] = useState<ChatRoom | null>(null);
  const [selectedFriend, setSelectedFriend] = useState<Friend | null>(null);
  const [messages, setMessages] = useState<ChatMessage[]>([]);
  const [input, setInput] = useState("");
  const [friends, setFriends] = useState<Friend[]>([]);
  const loggedUserId = localStorage.getItem("username") || "anonymous";
  const socket = new WebSocket("ws://localhost:8080/ws/chat");

  // Reference for the chat messages container
  const chatMessagesRef = useRef<HTMLDivElement | null>(null);

  const fetchChatRoom = (friend: Friend) => {
    fetch("http://localhost:8080/ws/chatroom", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
      body: JSON.stringify({ toUserID: friend.objectId }),
    })
      .then((response) => {
        if (!response.ok)
          throw new Error("Failed to create or fetch chat room");
        return response.json();
      })
      .then((room: ChatRoom) => {
        setSelectedRoom(room);
        setMessages(room.messages || []);
      })
      .catch((error) => console.error("Error fetching chat room:", error));
  };

  useEffect(() => {
    fetch("http://localhost:8080/api/friends/myfriends", {
      method: "GET",
      headers: {
        Authorization: "Bearer " + localStorage.getItem("token"),
      },
    })
      .then((response) => {
        if (!response.ok) throw new Error("Failed to fetch friends");
        return response.json();
      })
      .then((data) => setFriends(data))
      .catch((error) => console.error("Error fetching friends:", error));
  }, []);

  useEffect(() => {
    socket.onmessage = (event) => {
      const receivedMessage: ChatMessage = JSON.parse(event.data);
      setMessages((prevMessages) => {
        if (receivedMessage.chatRoomId === selectedRoom?.id) {
          return [...prevMessages, receivedMessage]; // Update current room
        } else {
          // Optional: show a notification for messages from other rooms
          alert(
            `New message from another room: ${receivedMessage.messageContent}`
          );
        }
        return prevMessages;
      });
    };

    return () => {
      // Close only on component unmount
      socket.close();
    };
  }, [selectedRoom]);

  useEffect(() => {
    const interval = setInterval(() => {
      if (selectedFriend) {
        fetchChatRoom(selectedFriend);
      }
    }, 5000);

    return () => clearInterval(interval);
  }, [selectedFriend]);

  // Scroll the chat messages container to the bottom when messages change
  useEffect(() => {
    if (chatMessagesRef.current) {
      chatMessagesRef.current.scrollTop = chatMessagesRef.current.scrollHeight;
    }
  }, [messages]);

  const sendMessage = () => {
    if (selectedRoom && selectedFriend) {
      const chatMessage: ChatMessage = {
        id: "", // ID is generated by the database
        senderId: loggedUserId,
        messageContent: input,
        receiverId: selectedFriend.objectId,
        timestamp: new Date().toISOString(),
        chatRoomId: selectedRoom.id,
      };
      socket.send(JSON.stringify(chatMessage)); // Send message via WebSocket
      setMessages([...messages, chatMessage]); // Update messages in the state
      setInput(""); // Clear input field
    }
  };

  const getInitials = (username: string) => {
    return username
      .split(" ")
      .map((word) => word[0])
      .join("")
      .toUpperCase()
      .slice(0, 2);
  };

  const formatMessageTime = (timestamp: string) => {
    const date = new Date(timestamp);

    // Format date
    const dateStr = date.toLocaleDateString("en-US", {
      year: "numeric",
      month: "short",
      day: "numeric",
    });

    // Format time
    const timeStr = date.toLocaleTimeString("en-US", {
      hour: "2-digit",
      minute: "2-digit",
      second: "2-digit",
      hour12: true,
    });

    return `${dateStr} ${timeStr}`;
  };

  return (
    <div className="chat-app-wrapper">
      <div className="chat-room-container">
        <div className="friends-sidebar">
          <h2>Friends</h2>
          <div className="friends-list">
            {friends.map((friend) => (
              <button
                key={`${friend.username}-${friend.objectId}`}
                className={`friend-button ${
                  selectedFriend?.objectId === friend.objectId ? "active" : ""
                }`}
                onClick={() => {
                  setSelectedFriend(friend);
                  fetchChatRoom(friend);
                }}
              >
                <div className="friend-avatar">
                  {getInitials(friend.username)}
                </div>
                {friend.username}
              </button>
            ))}
          </div>
        </div>

        <div className="chat-window">
          {selectedRoom ? (
            <>
              <h3>Chat with {selectedFriend?.username}</h3>
              <div className="chat-messages" ref={chatMessagesRef}>
                {messages.map((msg) => (
                  <div
                    key={`${msg.id}-${msg.timestamp}`}
                    className={`message ${
                      msg.senderId === loggedUserId ? "sent" : "received"
                    }`}
                  >
                    <strong>{msg.senderId}</strong>
                    <div className="message-content">{msg.messageContent}</div>
                    <span className="message-time">
                      {formatMessageTime(msg.timestamp)}
                    </span>
                  </div>
                ))}
              </div>
              <div className="chat-input">
                <input
                  type="text"
                  value={input}
                  onChange={(e) => setInput(e.target.value)}
                  placeholder="Type a message"
                  onKeyPress={(e) => {
                    if (e.key === "Enter") {
                      sendMessage();
                    }
                  }}
                />
                <button onClick={sendMessage}>Send</button>
              </div>
            </>
          ) : (
            <div className="no-chat-selected">
              <p>Select a friend to start messaging</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default ChatRoomComponent;
