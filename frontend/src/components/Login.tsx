import React, { useState, useEffect } from "react";
import axios from "axios";
import "../styles/Login.css";

const Login: React.FC = () => {
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [error, setError] = useState<string | null>(null);

  // Check if there is saved login data in localStorage and pre-fill the form
  useEffect(() => {
    const savedUsername = localStorage.getItem("username");
    if (savedUsername) {
      setUsername(savedUsername);
    }
  }, []);

  const handleLogin = async (event: React.FormEvent) => {
    event.preventDefault();

    try {
      const response = await axios.post("http://localhost:8080/auth/login", {
        username,
        password,
      });

      console.log(response.data);

      const token = response.data; // Assuming the response contains the token
      console.log(token);
      localStorage.setItem("token", token); // Save the token to local storage

      // Save the username for future use (but don't store the password)
      localStorage.setItem("username", username);

      alert("Login successful!");
      // Redirect to a protected page or dashboard
      window.location.href = "/api/posts/myfeed";
    } catch (error) {
      setError("Invalid username or password. Please try again.");
    }
  };

  return (
    <div className="login-container">
      <h2>Login to EchoNet</h2>
      <form onSubmit={handleLogin}>
        <div>
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div>
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        {error && <p className="error">{error}</p>}
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default Login;
