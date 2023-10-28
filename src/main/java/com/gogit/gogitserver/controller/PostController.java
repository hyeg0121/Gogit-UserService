package com.gogit.gogitserver.controller;

import com.gogit.gogitserver.dto.AddPostRequest;
import com.gogit.gogitserver.entity.Post;
import com.gogit.gogitserver.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody AddPostRequest post) {
        return postService.createPost(post);
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAllPost() {
        return ResponseEntity.ok().body(postService.getAllPosts());
    }

    @GetMapping("/{postID}")
    public ResponseEntity<Post> findPostById(@PathVariable Long postID) {
        Post post = postService.getPostById(postID);

        if (post != null) {
            return ResponseEntity.ok().body(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
