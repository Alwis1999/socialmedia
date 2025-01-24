import React from "react";
import Welcome from "./components/Welcome";
import Login from "./components/Login";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import ChatComponent from "./components/ChatComponent.tsx";

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/auth/welcome" element={<Welcome />} />
          <Route path="/chat" element={<ChatComponent />} />
        <Route path="/auth/login" element={<Login />} />

      </Routes>
    </Router>
  );
};

export default App;
