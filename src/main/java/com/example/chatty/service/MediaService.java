package com.example.chatty.service;
import com.example.chatty.models.entity.MediaEntity;
import com.example.chatty.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    public MediaEntity getById(Long id) {
        return mediaRepository.getById(id);
    }

    public void update(MediaEntity entity) {
        mediaRepository.update(entity);
    }

    public Long create(MediaEntity entity) {
        return mediaRepository.create(entity);
    }

    public List<MediaEntity> getAll() {
        return mediaRepository.getAll();
    }
}