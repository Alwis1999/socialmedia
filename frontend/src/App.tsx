import React from "react";
import Welcome from "./components/Welcome";
import Login from "./components/Login";
import Feed from "./components/Feed";
import Signup from "./components/Signup"; // Import the Signup component
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/auth/welcome" element={<Welcome />} />
        <Route path="/auth/login" element={<Login />} />
        <Route path="/auth/signup" element={<Signup />} /> {/* Signup route */}
        <Route path="/api/posts/myfeed" element={<Feed />} />
      </Routes>
    </Router>
  );
};

export default App;
