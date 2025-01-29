import React, { useEffect, useState } from "react";
import { getWelcomeMessage } from "../services/ApiService";
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
import Footer from "./Footer"; // Import the Footer component
>>>>>>> 4e77c164fe132508d1b54630b330c53dac3a55bc
>>>>>>> 3fbec058762d04386dbff9150d439978da76e527
import "../styles/Welcome.css";

const Welcome: React.FC = () => {
  const [message, setMessage] = useState<string>("Loading...");
<<<<<<< HEAD
  const [isLoading, setIsLoading] = useState(true);
=======
<<<<<<< HEAD
  const [isLoading, setIsLoading] = useState(true);
=======
>>>>>>> 4e77c164fe132508d1b54630b330c53dac3a55bc
>>>>>>> 3fbec058762d04386dbff9150d439978da76e527

  useEffect(() => {
    const fetchMessage = async () => {
      try {
        const result = await getWelcomeMessage();
        setMessage(result);
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 3fbec058762d04386dbff9150d439978da76e527
        setIsLoading(false);
      } catch (error) {
        setMessage("Welcome to our community!");
        setIsLoading(false);
<<<<<<< HEAD
=======
=======
      } catch (error) {
        setMessage("Error fetching message");
>>>>>>> 4e77c164fe132508d1b54630b330c53dac3a55bc
>>>>>>> 3fbec058762d04386dbff9150d439978da76e527
      }
    };

    fetchMessage();
  }, []);

  return (
    <div className="welcome-container">
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 3fbec058762d04386dbff9150d439978da76e527
      <div className="welcome-content">
        <img src="/logo.png" alt="EchoNet Logo" className="logo" />
        <h1 className="display-4">Welcome to EchoNet</h1>
        <p className={`lead ${isLoading ? "loading" : ""}`}>{message}</p>
        <p className="description">
          Connect with friends, share moments, and be part of a vibrant
          community. EchoNet brings people together in a safe and engaging
          environment where every conversation matters.
        </p>
        <div className="button-container">
          <a
            href="http://localhost:5173/auth/signup"
            className="auth-button signup-btn"
          >
            Get Started
          </a>
          <a
            href="http://localhost:5173/auth/login"
            className="auth-button login-btn"
          >
            Sign In
          </a>
        </div>
      </div>
<<<<<<< HEAD
=======
=======
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
>>>>>>> 4e77c164fe132508d1b54630b330c53dac3a55bc
>>>>>>> 3fbec058762d04386dbff9150d439978da76e527
    </div>
  );
};

export default Welcome;
