import React, { useEffect, useState } from "react";
import axios from "axios";
import {
  BiUser,
  BiGroup,
  BiEnvelope,
  BiShield,
  BiCalendar,
} from "react-icons/bi";
import { handleSessionExpired } from "../utils/auth";
import { useNavigate } from "react-router-dom";
import "../styles/Profile.css";

interface ProfileData {
  username: string;
  email: string;
  roles: string;
  createdAt: string;
  friendsCount: number;
}

const Profile: React.FC = () => {
  const [profileData, setProfileData] = useState<ProfileData | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProfileData = async () => {
      try {
        const token = localStorage.getItem("token");
        if (!token) {
          handleSessionExpired(navigate);
          return;
        }

        const response = await axios.get(
          "http://localhost:8080/api/user/profile",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        if (response.data.error) {
          throw new Error(response.data.error);
        }

        setProfileData(response.data);
      } catch (err: any) {
        console.error("Error fetching profile:", err);
        setError(err.response?.data || "Failed to load profile data");
      } finally {
        setLoading(false);
      }
    };

    fetchProfileData();
  }, [navigate]);

  if (loading) {
    return (
      <div className="profile-loading">
        <div className="loading-spinner"></div>
        <p>Loading profile...</p>
      </div>
    );
  }

  if (error) {
    return <div className="profile-error">{error}</div>;
  }

  return (
    <div className="profile-container">
      <div className="profile-card">
        <div className="profile-header">
          <div className="profile-avatar">
            {profileData?.username?.[0]?.toUpperCase()}
          </div>
          <h2>{profileData?.username}</h2>
        </div>

        <div className="profile-info">
          <div className="info-item">
            <BiUser className="info-icon" />
            <div className="info-content">
              <label>Username</label>
              <span>{profileData?.username}</span>
            </div>
          </div>

          <div className="info-item">
            <BiEnvelope className="info-icon" />
            <div className="info-content">
              <label>Email</label>
              <span>{profileData?.email}</span>
            </div>
          </div>

          <div className="info-item">
            <BiShield className="info-icon" />
            <div className="info-content">
              <label>Role</label>
              <span>{profileData?.roles?.replace("ROLE_", "")}</span>
            </div>
          </div>

          <div className="info-item">
            <BiGroup className="info-icon" />
            <div className="info-content">
              <label>Friends</label>
              <span>{profileData?.friendsCount} friends</span>
            </div>
          </div>

          <div className="info-item">
            <i className="info-icon far fa-calendar"></i>
            <div className="info-content">
              <label>Member Since</label>
              <span>
                {new Date(profileData?.createdAt || "").toLocaleDateString()}
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Profile;
