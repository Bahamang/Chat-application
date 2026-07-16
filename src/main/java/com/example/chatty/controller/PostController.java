package com.example.chatty.controller;
import com.example.chatty.models.dto.Post;
import com.example.chatty.models.entity.PostEntity;
import com.example.chatty.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{postId}")
    public PostEntity getById(@PathVariable Long postId) {
        return postService.getById(postId);
    }

    @PostMapping("/create")
    public void create(@RequestBody PostEntity postEntity) {
        postService.create(postEntity);
    }

    @PutMapping("/{postId}")
    public void update(@PathVariable Long postId, @RequestBody PostEntity postEntity) {
        postEntity.setId(postId); // Set the ID in case it's not set in the request body
        postService.update(postEntity);
    }

    @GetMapping("/getAll")
    public List<Post> getAll() {
        return postService.getAll();
    }

}