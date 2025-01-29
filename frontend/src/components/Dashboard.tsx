import React from "react";
import "../styles/ForDashboard.css";
import { useNavigate } from "react-router-dom";

const Dashboard: React.FC = () => {
  const navigate = useNavigate();

  const handleNavigation = (path: string) => {
    navigate(path);
  };

  return (
    <div className="dashboard">
      <h1>Welcome to EchoNet</h1>
      <div className="options">
<<<<<<< HEAD
        <button onClick={() => handleNavigation("/posts/myposts")}>
          My Posts
        </button>
        <button onClick={() => handleNavigation("/chat/chatroom")}>
          Chat Room
        </button>
=======
        <button onClick={() => handleNavigation("/chat")}>Chat</button>
        <button onClick={() => handleNavigation("/chat/chatroom")}>Chat Room</button>
>>>>>>> 3fbec058762d04386dbff9150d439978da76e527
        <button onClick={() => handleNavigation("/friends")}>Friends</button>
        <button onClick={() => handleNavigation("/requests")}>
          Friend Requests
        </button>
        <button onClick={() => handleNavigation("/feed")}>My Feed</button>
<<<<<<< HEAD
        <button onClick={() => handleNavigation("/chat/newchatroom")}>
          New Chat room
        </button>
=======
        <button onClick={() => handleNavigation("/chat/newchatroom")}>New Chat room</button>
>>>>>>> 3fbec058762d04386dbff9150d439978da76e527
      </div>
    </div>
  );
};

export default Dashboard;
