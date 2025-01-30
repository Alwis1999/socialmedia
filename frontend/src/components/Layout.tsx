import React, { useState } from "react";
import { NavLink, Outlet } from "react-router-dom";
import { BiHomeAlt, BiMessageSquare, BiUser, BiNews } from "react-icons/bi";
import { FiChevronLeft, FiChevronRight } from "react-icons/fi";
import "../styles/Layout.css";

const Layout: React.FC = () => {
  const [isCollapsed, setIsCollapsed] = useState(false);

  const toggleSidebar = () => {
    setIsCollapsed(!isCollapsed);
  };

  return (
    <div className="app-layout">
      <nav className={`sidebar ${isCollapsed ? "collapsed" : ""}`}>
        <div className="logo-container">
          {!isCollapsed && (
            <img src="/logo.png" alt="EchoNet Logo" className="nav-logo" />
          )}
          {!isCollapsed && <h1>EchoNet</h1>}
        </div>
        <div className="nav-links">
          <NavLink to="/posts/myposts" className="nav-item" title="My Posts">
            <BiHomeAlt />
            {!isCollapsed && <span>My Posts</span>}
          </NavLink>
          <NavLink to="/chat/chatroom" className="nav-item" title="Messages">
            <BiMessageSquare />
            {!isCollapsed && <span>Messages</span>}
          </NavLink>
          <NavLink to="/friends/myfriends" className="nav-item" title="Friends">
            <BiUser />
            {!isCollapsed && <span>Friends</span>}
          </NavLink>
          <NavLink to="/feed" className="nav-item" title="My Feed">
            <BiNews />
            {!isCollapsed && <span>My Feed</span>}
          </NavLink>
        </div>
        <button
          className="toggle-button"
          onClick={toggleSidebar}
          title={isCollapsed ? "Expand Sidebar" : "Collapse Sidebar"}
        >
          {isCollapsed ? <FiChevronRight /> : <FiChevronLeft />}
        </button>
      </nav>
      <main className={`main-content ${isCollapsed ? "expanded" : ""}`}>
        <Outlet />
      </main>
    </div>
  );
};

export default Layout;
