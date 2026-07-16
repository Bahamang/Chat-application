package com.example.chatty.service;

import com.example.chatty.models.dto.Media;
import com.example.chatty.models.dto.Profile;
import com.example.chatty.models.entity.ProfileEntity;
import com.example.chatty.repository.MediaRepository;
import com.example.chatty.repository.ProfileRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jooq.tools.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendsService {

    @Autowired
    private ProfileService profileService;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    MediaRepository mediaRepository;


    public List<Profile> getMyFriends(Long profileId) {
        ProfileEntity tmpProfile = profileRepository.getById(profileId);
        List<Long> profilesId = parseStringToLongList(tmpProfile.getFriendsId());
        if(profilesId.isEmpty())
            return new ArrayList<>();
        List<Profile> profiles = profileService.getByListId(profilesId);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        for (Profile profile : profiles) {
            if(profile.getPhotoId()!=null)
                profile.setPhoto(mapper.convertValue(mediaRepository.getById(profile.getPhotoId()), Media.class));
        }
        return profiles;
    }


    public List<Profile> getOthers(Long profileId) {
        ProfileEntity tmpProfile = profileRepository.getById(profileId);
        List<Long> profilesId = parseStringToLongList(tmpProfile.getFriendsId());
        profilesId.add(tmpProfile.getId());
        List<Profile> profiles = profileService.getByNotInListId(profilesId);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        for (Profile profile : profiles) {
            if(profile.getPhotoId()!=null)
                profile.setPhoto(mapper.convertValue(mediaRepository.getById(profile.getPhotoId()), Media.class));
        }
        return profiles;
    }

    public void addFriend(Long profileId, Long userToAddId) {
        ProfileEntity tmpProfile = profileRepository.getById(profileId);

        if(parseStringToLongList(tmpProfile.getFriendsId()).contains(userToAddId))
            return;
        String myFriends = !StringUtils.isBlank(tmpProfile.getFriendsId()) ?
                tmpProfile.getFriendsId()+","+userToAddId
                : userToAddId.toString();
        tmpProfile.setFriendsId(myFriends);
        profileRepository.update(tmpProfile);
    }

    public void removeFriend(Long profileId, Long userToRemoveId) {
        ProfileEntity tmpProfile = profileRepository.getById(profileId);

        if(!parseStringToLongList(tmpProfile.getFriendsId()).contains(userToRemoveId))
            return;

        String result = parseStringToLongList(tmpProfile.getFriendsId()).stream() // Convert list to stream
                .filter(n -> !n.equals(userToRemoveId)) // Filter out the number to remove
                .map(String::valueOf) // Convert each Long to String
                .collect(Collectors.joining(",")); // Join them with commas

        tmpProfile.setFriendsId(result);
        if(tmpProfile.getFriendsId().isBlank())
            tmpProfile.setFriendsId(null);
        profileRepository.update(tmpProfile);
    }


    public static List<Long> parseStringToLongList(String input) {
        // Check if the input string is not null or empty to avoid NullPointerException
        if (input == null || input.isEmpty() || input.equals("null")) {
            return new ArrayList<>(); // Return an empty list if input is null or empty
        }

        return Arrays.stream(input.split(","))
                .map(Long::parseLong) // Convert each string segment to a Long
                .collect(Collectors.toList()); // Collect results into a list
    }


}
