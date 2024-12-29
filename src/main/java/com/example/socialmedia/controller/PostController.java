package com.example.socialmedia.controller;

import com.example.socialmedia.entity.Comment;
import com.example.socialmedia.entity.Post;
import com.example.socialmedia.dto.PostBody;
import com.example.socialmedia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

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
        return postService.getMyPosts();
    }

    // Get a specific user's posts
    @GetMapping("/{user}")
    public List<Post> getPostByUser(@PathVariable String user) {
        // Verify access
        if (!postService.canViewPosts(user)) {
            throw new RuntimeException("You cannot view posts of this user because you are not friends.");
        }
        return postService.getPostByUser(user);
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

    // Get logged user's friends' posts
    @GetMapping("/friends-posts")
    public List<Post> getFriendsPosts() {
        return postService.getMyPosts();
    }

    // Add a comment to a post
    @PostMapping("/{postId}/comment")
    public String addCommentToPost(@PathVariable String postId, @RequestBody Comment comment) {
        Post post = postService.getPostById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        // Check if logged user is a friend of the post owner
        if (!postService.isFriend(post.getUser())) {
            return "You can only comment on your friends' posts";
        }

        postService.addCommentToPost(postId, comment);
        return "Comment added successfully";
    }
}