import React from "react";
<<<<<<< HEAD
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Welcome from "./components/Welcome";
import Login from "./components/Login";
import SignUp from "./components/SignUp";
import FriendListComponent from "./components/FriendListComponent";
import ChatRoomComponent from "./components/ChatRoomComponent";
import Dashboard from "./components/Dashboard";
import UserPosts from "./components/UserPosts";
=======
<<<<<<< HEAD
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Welcome from "./components/Welcome";
import Login from "./components/Login";
import FriendListComponent from "./components/FriendListComponent";
import ChatRoomComponent from "./components/ChatRoomComponent";
import Dashboard from './components/Dashboard';

=======
import Welcome from "./components/Welcome";
import Login from "./components/Login";
import Feed from "./components/Feed";
import Signup from "./components/Signup"; // Import the Signup component
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
>>>>>>> 4e77c164fe132508d1b54630b330c53dac3a55bc
>>>>>>> 3fbec058762d04386dbff9150d439978da76e527

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/auth/welcome" element={<Welcome />} />
        <Route path="/auth/login" element={<Login />} />
<<<<<<< HEAD
        <Route path="/auth/signup" element={<SignUp />} />
        <Route path="/chat/chatroom" element={<ChatRoomComponent />} />
        <Route path="/friends/myfriends" element={<FriendListComponent />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/posts/myposts" element={<UserPosts />} />
=======
<<<<<<< HEAD
        <Route path="/chat/chatroom" element={<ChatRoomComponent/>} />
        <Route path="/friends/myfriends" element={<FriendListComponent />} />
        <Route path="/dashboard" element={<Dashboard />} />
=======
        <Route path="/auth/signup" element={<Signup />} /> {/* Signup route */}
        <Route path="/api/posts/myfeed" element={<Feed />} />
>>>>>>> 4e77c164fe132508d1b54630b330c53dac3a55bc
>>>>>>> 3fbec058762d04386dbff9150d439978da76e527
      </Routes>
    </Router>
  );
};

export default App;
