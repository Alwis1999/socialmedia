package com.example.socialmedia.service;

import com.example.socialmedia.entity.Comment;
import com.example.socialmedia.entity.Post;
import com.example.socialmedia.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}