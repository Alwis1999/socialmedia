package com.example.socialmedia.controller;

import com.example.socialmedia.entity.Comment;
import com.example.socialmedia.entity.CommentBody;
import com.example.socialmedia.entity.Post;
import com.example.socialmedia.dto.PostBody;
import com.example.socialmedia.service.CommentService;
import com.example.socialmedia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    // Create a new post
    @PostMapping("/upload")
    public Post createPost(@RequestBody PostBody postBody) {
        Post post = new Post(postBody.getContent());
        return postService.createPost(post);
    }

    // Add a comment to a post
    @PostMapping("/{postId}/comments")
    public Post addComment(@PathVariable String postId, @RequestBody CommentBody commentBody) {
        Comment comment = commentService.createComment(commentBody.getContent(), postId);
        return postService.addCommentToPost(postId, comment);
    }

    // Get all posts of the logged-in user
    @GetMapping("/myposts")
    public List<Post> getMyPosts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String myUsername = authentication.getName();
            return postService.getAllPosts(myUsername);
        }
        throw new RuntimeException("User not authenticated");
    }

    // Get a specific user's posts with access control
    @GetMapping("/{user}")
    public List<Post> getPostByUser(@PathVariable String user) {
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
        return postService.getFriendsPosts();
    }

    // Add a comment to a post with friend-check validation
    @PostMapping("/{postId}/comment")
    public String addCommentToPost(@PathVariable String postId, @RequestBody Comment comment) {
        Post post = postService.getPostById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!postService.isFriend(post.getUser())) {
            return "You can only comment on your friends' posts";
        }

        postService.addCommentToPost(postId, comment);
        return "Comment added successfully";
    }
}
