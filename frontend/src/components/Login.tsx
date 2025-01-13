import React, { useState } from "react";
import axios from "axios"; // For making HTTP requests
import "../styles/Login.css";

const Login: React.FC = () => {
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [error, setError] = useState<string | null>(null);

  const handleLogin = async (event: React.FormEvent) => {
    event.preventDefault();

    try {
      const response = await axios.post("http://localhost:8080/auth/login", {
        username,
        password,
      });

      const token = response.data.token; // Assuming the response contains the token as 'token'
      localStorage.setItem("token", token); // Save the token
      alert("Login successful!");
      // Redirect to a protected page or dashboard
      window.location.href = "/dashboard";
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
