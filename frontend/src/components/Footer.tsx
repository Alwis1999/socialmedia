import React from "react";
import "../styles/Footer.css"; // Make sure to create this file for styling

const Footer: React.FC = () => {
  return (
    <footer className="footer">
      <p>&copy; 2025 EchoNet. All rights reserved.</p>
      {/* <p>Connect with us:</p> */}
      {/* <div className="social-icons">
        <a href="#" title="Facebook">
          <i className="fab fa-facebook"></i>
        </a>
        <a href="#" title="Twitter">
          <i className="fab fa-twitter"></i>
        </a>
        <a href="#" title="Instagram">
          <i className="fab fa-instagram"></i>
        </a>
      </div> */}
    </footer>
  );
};

export default Footer;
