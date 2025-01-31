import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { BiUser, BiMessageSquare } from "react-icons/bi";
import { handleSessionExpired, checkAuthError } from "../utils/auth";
import "../styles/FriendList.css";

interface Friend {
  username: string;
  objectId: string;
}

const FriendListComponent: React.FC = () => {
  const [friends, setFriends] = useState<Friend[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();
  const BASE_URL = "http://localhost:8080"; // Replace with your actual base URL

  useEffect(() => {
    const fetchFriends = async () => {
      try {
        const token = localStorage.getItem("token");
        if (!token) {
          handleSessionExpired(navigate);
          return;
        }

        const response = await axios.get(`${BASE_URL}/api/friends/myfriends`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setFriends(response.data);
      } catch (err: any) {
        if (!checkAuthError(err, navigate)) {
          setError("Failed to fetch friends list");
        }
      } finally {
        setLoading(false);
      }
    };

    fetchFriends();
  }, [navigate]);

  const handleChatClick = (friendId: string) => {
    navigate(`/chat/chatroom?friendId=${friendId}`);
  };

  if (loading) {
    return (
      <div className="friends-loading">
        <div className="loading-spinner"></div>
        <p>Loading friends list...</p>
      </div>
    );
  }

  if (error) {
    return <div className="friends-error">{error}</div>;
  }

  return (
    <div className="friends-container">
      <h2>My Friends</h2>
      {friends.length === 0 ? (
        <div className="no-friends">
          <BiUser className="no-friends-icon" />
          <p>You haven't added any friends yet.</p>
        </div>
      ) : (
        <div className="friends-list">
          {friends.map((friend) => (
            <div key={friend.objectId} className="friend-card">
              <div className="friend-info">
                <div className="friend-avatar">
                  {friend.username[0].toUpperCase()}
                </div>
                <span className="friend-name">{friend.username}</span>
              </div>
              <button
                className="chat-button"
                onClick={() => handleChatClick(friend.objectId)}
                title="Start chat"
              >
                <BiMessageSquare />
                <span>Chat</span>
              </button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default FriendListComponent;