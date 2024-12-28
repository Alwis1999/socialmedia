package com.example.socialmedia.controller;

import com.example.socialmedia.dto.FriendRequestDTO;
import com.example.socialmedia.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/friends")
public class RequestController {

    @Autowired
    private FriendService friendService;

    @PostMapping("/request")
    public String sendFriendRequest(@RequestBody Map<String, String> requestBody) {
        String fromUserId = requestBody.get("fromUserId");
        String toUserId = requestBody.get("toUserId");
        return friendService.sendFriendRequest(fromUserId, toUserId);
    }

    @PostMapping("/accept")
    public String acceptFriendRequest(@RequestBody FriendRequestDTO friendRequestDTO) {
        return friendService.acceptFriendRequest(friendRequestDTO.getFromUserId());
    }

    @GetMapping("/requests")
    public Set<String> getFriendRequests() {
        return friendService.getFriendRequests();
    }
}
