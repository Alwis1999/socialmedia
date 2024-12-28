package com.example.socialmedia.dto;

import lombok.Data;

@Data
public class FriendRequestDTO {

    private String fromUserId;

    public FriendRequestDTO(String fromUserId) {
        this.fromUserId = fromUserId;
    }
}
