package com.example.socialmedia.service;

import com.example.socialmedia.entity.BaseFile;
import com.example.socialmedia.entity.UserInfo;
import com.example.socialmedia.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FriendService extends BaseFile {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public String sendFriendRequest(String fromUserId, String toUserId) {
        System.out.println("\n\n"+fromUserId+"\n\n");
        UserInfo fromUser = userInfoRepository.findById(fromUserId).orElseThrow(() ->
                new RuntimeException("User not found"));
        UserInfo toUser = userInfoRepository.findById(toUserId).orElseThrow(() ->
                new RuntimeException("User not found"));

        if(toUser.getFriendsRequest().contains(fromUserId)) {
            return "Friend request already sent";
        }

        toUser.getFriendsRequest().add(fromUserId);
        userInfoRepository.save(toUser);

        return "Friend request sent!!!";

    }

    public String acceptFriendRequest(String fromUserId) {
        UserInfo user = loggerUser();
        UserInfo fromUser = userInfoRepository.findById(fromUserId).orElseThrow(() ->
                new RuntimeException("Requested user not found"));

        if (!user.getFriendsRequest().contains(fromUserId)) {
            return "No such friend request!!!!";
        }


        // Update the users details
        user.getFriendsRequest().remove(fromUserId);
        user.getFriends().add(fromUserId);
        fromUser.getFriends().add(user.getId());

        // Update database
        userInfoRepository.save(user);
        userInfoRepository.save(fromUser);

        return "Friend requested accepted!!!";
    }

    public Set<String> getFriendRequests() {
        UserInfo user = loggerUser();
        return user.getFriendsRequest();
    }
}
