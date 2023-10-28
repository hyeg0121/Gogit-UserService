package com.gogit.gogitserver.service;

import com.gogit.gogitserver.dto.AddPostRequest;
import com.gogit.gogitserver.entity.Post;
import com.gogit.gogitserver.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public ResponseEntity<Post> createPost(AddPostRequest post){
        Post savedPost = postRepository.save(new Post(post.getWriter(), post.getContents()));

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post updatePost(Long postId, AddPostRequest postRequest) {
        if (postRepository.existsById(postId)) {
            Post post = postRepository.findById(postId).get();
            post.setContents(postRequest.getContents());
            return postRepository.save(post);
        } else {
            return null;
        }
    }

}
