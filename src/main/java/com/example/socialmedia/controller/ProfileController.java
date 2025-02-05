package com.example.socialmedia.controller;

import com.example.socialmedia.entity.UserInfo;
import com.example.socialmedia.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class ProfileController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        try {
            UserInfo user = userInfoService.getLoggedInUser();
            Map<String, Object> profileData = new HashMap<>();
            profileData.put("username", user.getUsername());
            profileData.put("email", user.getEmail());
            profileData.put("roles", user.getRoles());
            profileData.put("friendsCount", user.getFriends().size());
            profileData.put("createdAt", user.getCreatedAt());

            return ResponseEntity.ok(profileData);
        } catch (Exception e) {
            e.printStackTrace(); // Add this for debugging
            return ResponseEntity.internalServerError()
                    .body("Error fetching profile data: " + e.getMessage());
        }
    }
} 