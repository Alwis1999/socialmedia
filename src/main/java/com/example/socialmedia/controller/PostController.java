package com.example.socialmedia.controller;


import com.example.socialmedia.entity.BaseFile;
import com.example.socialmedia.entity.Post;
import com.example.socialmedia.dto.PostBody;
import com.example.socialmedia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController extends BaseFile {

    @Autowired
    private PostService postService;

    private Post post;

    // Create a new post
    @PostMapping("/upload")
    public Post createPost(@RequestBody PostBody postBody) {
        post = new Post(postBody.getContent());
        return postService.createPost(post);
    }

    // Get logged user posts
    @GetMapping("/myposts")
    public List<Post> getMyPosts() {
        String myUsername;
        myUsername = loggedUsername();
        return postService.getAllPosts(myUsername);
    }

   /* // Get a post by ID
    @GetMapping("/{id}")
    public Optional<Post> getPostById(@PathVariable String id) {
        return postService.getPostById(id);
    }*/

    // Get a post by user
    @GetMapping("/{user}")
    public List<Post> getPostByUser(@RequestBody String user) {
        return postService.getAllPosts(user);
    }

    // Update a post
    @PutMapping("/{id}")
    public Post updatePost(@PathVariable String id, @RequestBody Post postDetails) {
        return postService.updatePost(id, postDetails);
    }

    // Delete a post
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id) {
        postService.deletePost(id);
    }
}