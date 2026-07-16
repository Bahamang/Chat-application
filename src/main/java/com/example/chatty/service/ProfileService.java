package com.example.chatty.service;

import com.example.chatty.models.dto.Media;
import com.example.chatty.models.dto.Profile;
import com.example.chatty.models.entity.ProfileEntity;
import com.example.chatty.repository.MediaRepository;
import com.example.chatty.repository.ProfileRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private MediaRepository mediaRepository;

    public Profile getById(Long profileId) {
        ProfileEntity tmpProfile = profileRepository.getById(profileId);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        Profile profile = mapper.convertValue(tmpProfile, Profile.class);
        if(profile.getPhotoId()!=null)
            profile.setPhoto(mapper.convertValue(mediaRepository.getById(profile.getPhotoId()), Media.class));
        return profile;
    }

    public List<Profile> getByListId(List<Long> profilesId) {
        List<ProfileEntity> tmpProfile = profileRepository.getByListId(profilesId);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<Profile> profiles = mapper.convertValue(tmpProfile, new ObjectMapper().getTypeFactory().constructCollectionType(List.class, Profile.class));
        for (Profile profile : profiles) {
            if(profile.getPhotoId()!=null)
                profile.setPhoto(mapper.convertValue(mediaRepository.getById(profile.getPhotoId()), Media.class));
        }
        return profiles;
    }

    public List<Profile> getByNotInListId(List<Long> profilesId) {
        List<ProfileEntity> tmpProfile = profileRepository.getByNotInListId(profilesId);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<Profile> profiles = mapper.convertValue(tmpProfile, new ObjectMapper().getTypeFactory().constructCollectionType(List.class, Profile.class));
        for (Profile profile : profiles) {
            if(profile.getPhotoId()!=null)
                profile.setPhoto(mapper.convertValue(mediaRepository.getById(profile.getPhotoId()), Media.class));
        }
        return profiles;
    }



    public void update(ProfileEntity profileEntity) {
        profileRepository.update(profileEntity);
    }

    public void create(ProfileEntity profileEntity) {
        profileRepository.create(profileEntity);
    }

    public List<Profile> getAll() {
        List<ProfileEntity> tmpProfile = profileRepository.getAll();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<Profile> profiles = mapper.convertValue(tmpProfile, new ObjectMapper().getTypeFactory().constructCollectionType(List.class, Profile.class));
        for (Profile profile : profiles) {
            if(profile.getPhotoId()!=null)
                profile.setPhoto(mapper.convertValue(mediaRepository.getById(profile.getPhotoId()), Media.class));
        }
        return profiles;
    }

}