import React from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";
import Welcome from "./components/Welcome";
import Login from "./components/Login";
import SignUp from "./components/SignUp";
import FriendListComponent from "./components/FriendListComponent";
import ChatRoomComponent from "./components/ChatRoomComponent";
import Dashboard from "./components/Dashboard";
import UserPosts from "./components/UserPosts";
import CreatePost from "./components/CreatePost";
import Layout from "./components/Layout";

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        {/* Public routes */}
        <Route path="/" element={<Navigate to="/auth/welcome" replace />} />
        <Route path="/auth/welcome" element={<Welcome />} />
        <Route path="/auth/login" element={<Login />} />
        <Route path="/auth/signup" element={<SignUp />} />

        {/* Protected routes with layout */}
        <Route element={<Layout />}>
          <Route path="/posts/myposts" element={<UserPosts />} />
          <Route path="/posts/create" element={<CreatePost />} />
          <Route path="/chat/chatroom" element={<ChatRoomComponent />} />
          <Route path="/friends/myfriends" element={<FriendListComponent />} />
          <Route path="/feed" element={<Dashboard />} />
          {/* Redirect /dashboard to /feed */}
          <Route path="/dashboard" element={<Navigate to="/feed" replace />} />
        </Route>

        {/* Catch all route - redirect to welcome */}
        <Route path="*" element={<Navigate to="/auth/welcome" replace />} />
      </Routes>
    </Router>
  );
};

export default App;
