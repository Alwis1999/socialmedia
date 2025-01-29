import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Welcome from "./components/Welcome";
import Login from "./components/Login";
import FriendListComponent from "./components/FriendListComponent";
import ChatRoomComponent from "./components/ChatRoomComponent";
import Dashboard from './components/Dashboard';


const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/auth/welcome" element={<Welcome />} />
        <Route path="/auth/login" element={<Login />} />
        <Route path="/chat/chatroom" element={<ChatRoomComponent/>} />
        <Route path="/friends/myfriends" element={<FriendListComponent />} />
        <Route path="/dashboard" element={<Dashboard />} />
      </Routes>
    </Router>
  );
};

export default App;
