package com.example.chatty.controller;
import com.example.chatty.models.entity.MediaEntity;
import com.example.chatty.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @GetMapping("/{id}")
    public MediaEntity getById(@PathVariable Long id) {
        return mediaService.getById(id);
    }

    @PostMapping("/create")
    public Long create(@RequestBody MediaEntity entity) {
        return mediaService.create(entity);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody MediaEntity entity) {
        entity.setId(id); // Set the ID in case it's not set in the request body
        mediaService.update(entity);
    }

    @GetMapping("/getAll")
    public List<MediaEntity> getAll() {
        return mediaService.getAll();
    }
}