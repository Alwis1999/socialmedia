package com.example.socialmedia.service;

import com.example.socialmedia.entity.Post;
import com.example.socialmedia.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< Updated upstream
=======

import java.time.LocalDateTime;
import java.util.ArrayList;
>>>>>>> Stashed changes
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // Create a new post
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // Get all posts
    /*public List<Post> getAllPosts() {
        return postRepository.findAll();
    }*/

    public List<Post> getAllPosts(String username) {
        return postRepository.findByUser(username);
    }

    // Get post by ID
    public Optional<Post> getPostById(String id) {
        return postRepository.findById(id);
    }

    // Get post by user
    public List<Post> getPostByUser(String user) {
        return postRepository.findByUser(user);
    }


    // Update post
    public Post updatePost(String id, Post postDetails) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setContent(postDetails.getContent());
        post.setUser(postDetails.getUser());
        return postRepository.save(post);
    }

    // Delete post
    public void deletePost(String id) {
        postRepository.deleteById(id);
    }
<<<<<<< Updated upstream
=======

    public boolean isFriend(String postOwner) {
        UserInfo loggedInUser = loggerUser();
        Optional<UserInfo> postOwnerUser = userInfoRepository.findByUsername(postOwner);
        return postOwnerUser.map(user ->
                loggedInUser.getFriends().contains(user.getId())).orElse(false) ||
                postOwner.equals(loggedInUser.getUsername());
    }

    public List<Post> getPostsForFriends(String username) {
        List<Post> allPosts = postRepository.findAll();
        return allPosts.stream()
                .filter(post -> isFriend(post.getUser()))
                .toList();
    }

    public List<Post> getMyPosts() {
        String loggedInUsername = loggedUsername();
        return postRepository.findAll().stream()
                .filter(post -> post.getUser().equals(loggedInUsername) || isFriend(post.getUser()))
                .toList();
    }

    public boolean canViewPosts(String postOwner) {
        UserInfo loggedInUser = loggerUser();
        return isFriend(postOwner); // Use the `isFriend` method you already have.
    }

    public List<Post> getMyFeed() {
        String loggedInUsername = loggedUsername();

        // Retrieve all posts by friends and the logged-in user
        List<Post> posts = postRepository.findAll().stream()
                .filter(post -> post.getUser().equals(loggedInUsername) || isFriend(post.getUser()))
                .toList();

        // Sort posts by the most recent activity (comments or updates)
        posts.sort((p1, p2) -> {
            LocalDateTime lastActivity1 = getLastActivityTime(p1);
            LocalDateTime lastActivity2 = getLastActivityTime(p2);
            return lastActivity2.compareTo(lastActivity1); // Descending order
        });

        return posts;
    }

    private LocalDateTime getLastActivityTime(Post post) {
        LocalDateTime lastCommentTime = post.getComments() != null && !post.getComments().isEmpty() ?
                post.getComments().stream()
                        .map(Comment::getCommentAt)
                        .max(LocalDateTime::compareTo)
                        .orElse(post.getCreatedAt()) : post.getCreatedAt();

        // Use the later of the last comment time or the post creation time
        return lastCommentTime.isAfter(post.getCreatedAt()) ? lastCommentTime : post.getCreatedAt();
    }



>>>>>>> Stashed changes
}