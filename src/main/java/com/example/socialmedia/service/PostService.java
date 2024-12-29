package com.example.socialmedia.service;

import com.example.socialmedia.entity.BaseFile;
import com.example.socialmedia.entity.Comment;
import com.example.socialmedia.entity.Post;
import com.example.socialmedia.entity.UserInfo;
import com.example.socialmedia.repository.PostRepository;
import com.example.socialmedia.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService extends BaseFile {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    // Create a new post
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // Add a comment to a post
    public Post addCommentToPost(String postId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        if (post.getComments() == null) {
            post.setComments(new ArrayList<>());
        }
        post.getComments().add(comment);

        return postRepository.save(post);
    }

    public List<Post> getAllPostsByUsername(String username) {
        List<Post> allPosts = postRepository.findAll();
        return allPosts.stream()
                .filter(post -> isFriend(post.getUser()))
                .toList();
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
    public void deletePost(String id) {
        postRepository.deleteById(id);
    }

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

    
}