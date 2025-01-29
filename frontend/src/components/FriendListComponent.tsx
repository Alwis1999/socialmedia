import React, { useEffect, useState } from "react";
import axios from "axios";

interface Friend {
  username: string;
  objectId: string;
}

const FriendListComponent: React.FC = () => {
  const [friends, setFriends] = useState<Friend[]>([]);
  const BASE_URL = "http://localhost:8080"; // Replace with your actual base URL

  const getJwtToken = () => {
    return localStorage.getItem("token");
  };

  useEffect(() => {
    const token = getJwtToken();
    if (!token) {
      console.error("No token found, please log in.");
      return;
    }

    axios
      .get(`${BASE_URL}/api/friends/myfriends`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setFriends(response.data); // Assuming response data is an array of friends
      })
      .catch((error) => {
        console.error("Error fetching friends:", error);
      });
  }, []);

  return (
    <div>
      <h3>My Friends</h3>
      <ul>
        {friends.map((friend) => (
          <li key={friend.objectId}>
            {friend.username} (ID: {friend.objectId})
          </li>
        ))}
      </ul>
    </div>
  );
};

export default FriendListComponent;
