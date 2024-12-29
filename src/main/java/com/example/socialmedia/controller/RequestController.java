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
    public String sendFriendRequest(@RequestBody FriendRequestDTO requestBody) {

        String toUserId = requestBody.getUserId();
        return friendService.sendFriendRequest(toUserId);
    }

    @PostMapping("/accept")
    public String acceptFriendRequest(@RequestBody FriendRequestDTO friendRequestDTO) {
        System.out.println("\n\n\n"+friendRequestDTO.getUserId()+"\n\n\n");
        return friendService.acceptFriendRequest(friendRequestDTO.getUserId());
    }

    @GetMapping("/requests")
    public Set<String> getFriendRequests() {
        return friendService.getFriendRequests();
    }
}
