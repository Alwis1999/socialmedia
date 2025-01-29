import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Welcome from "./components/Welcome";
import Login from "./components/Login";
import SignUp from "./components/SignUp";
import FriendListComponent from "./components/FriendListComponent";
import ChatRoomComponent from "./components/ChatRoomComponent";
import Dashboard from "./components/Dashboard";
import UserPosts from "./components/UserPosts";

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/auth/welcome" element={<Welcome />} />
        <Route path="/auth/login" element={<Login />} />
        <Route path="/auth/signup" element={<SignUp />} />
        <Route path="/chat/chatroom" element={<ChatRoomComponent />} />
        <Route path="/friends/myfriends" element={<FriendListComponent />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/posts/myposts" element={<UserPosts />} />
      </Routes>
    </Router>
  );
};

export default App;
