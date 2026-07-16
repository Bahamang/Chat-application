package com.example.chatty.service;


import com.example.chatty.models.dto.Media;
import com.example.chatty.models.dto.Post;
import com.example.chatty.models.dto.Profile;
import com.example.chatty.models.entity.PostEntity;
import com.example.chatty.repository.MediaRepository;
import com.example.chatty.repository.PostRepository;
import com.example.chatty.repository.ProfileRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private ProfileService profileService;

    public PostEntity getById(Long postId) {
        return postRepository.getById(postId);
    }

    public void update(PostEntity postEntity) {
        postRepository.update(postEntity);
    }

    public void create(PostEntity postEntity) {
        postRepository.create(postEntity);
    }

    public List<Post> getAll() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<PostEntity> postEntity = postRepository.getAll();
        List<Post> posts = mapper.convertValue(postEntity, new ObjectMapper().getTypeFactory().constructCollectionType(List.class, Post.class));
        for (Post post : posts) {
            if(post.getMediaId()!=null)
                post.setMedia(mapper.convertValue(mediaRepository.getById(post.getMediaId()), Media.class));
            if(post.getProfileId()!=null)
                post.setProfile(profileService.getById(post.getProfileId()));
        }
        return posts;
    }
}