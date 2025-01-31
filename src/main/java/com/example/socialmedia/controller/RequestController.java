package com.example.socialmedia.controller;

import com.example.socialmedia.dto.FriendDetailsDTO;
import com.example.socialmedia.dto.FriendRequestDTO;
import com.example.socialmedia.service.FriendService;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/friends")
public class RequestController {


    @Autowired
    private FriendService friendService;

    @PostMapping("/request/send")
    public String sendFriendRequest(@RequestBody FriendRequestDTO requestBody) {
        System.out.println("\n\n#### "+requestBody.getUserId()+" ###\n\n");
        String toUserId = requestBody.getUserId();
        return friendService.sendFriendRequest(toUserId);
    }

    @PostMapping("/request/accept")
    public String acceptFriendRequest(@RequestBody FriendRequestDTO friendRequestDTO) {
        System.out.println("\n\n\n" + friendRequestDTO.getUserId() + "\n\n\n");
        return friendService.acceptFriendRequest(friendRequestDTO.getUserId());
    }

    @GetMapping("/request/my")
    public Set<String> getFriendRequests() {
        return friendService.getFriendRequests();
    }

    @GetMapping("/myfriends")
    public List<FriendDetailsDTO> getMyFriends() {
        return friendService.getMyFriends();
    }

}
