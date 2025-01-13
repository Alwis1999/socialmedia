import React, { useEffect, useState } from "react";
import { getWelcomeMessage } from "../services/ApiService";
import Footer from "./Footer"; // Import the Footer component
import "../styles/Welcome.css";

const Welcome: React.FC = () => {
  const [message, setMessage] = useState<string>("Loading...");

  useEffect(() => {
    const fetchMessage = async () => {
      try {
        const result = await getWelcomeMessage();
        setMessage(result);
      } catch (error) {
        setMessage("Error fetching message");
      }
    };

    fetchMessage();
  }, []);

  return (
    <div className="welcome-container">
      <div className="text-center p-5">
        <h1 className="display-4">Welcome to EchoNet</h1>
        <img src="/public/logo.png" alt="EchoNet Logo" className="logo" />
        <p className="lead">{message}</p>
        <p className="description">
          Join EchoNet today and be a part of a vibrant, interactive online
          experience that makes staying connected simple and fun!
        </p>
        <div className="button-container">
          <a href="http://localhost:5173/auth/signup">
            <button className="btn btn-primary mt-4">Sign Up</button>
          </a>
          <a href="http://localhost:5173/auth/login">
            <button className="btn btn-secondary mt-4 ml-4">Login</button>
          </a>
        </div>
      </div>
      <Footer /> {/* Add Footer here */}
    </div>
  );
};

export default Welcome;
