package com.example.chatty.controller;

import com.example.chatty.models.dto.Profile;
import com.example.chatty.models.entity.ProfileEntity;
import com.example.chatty.service.ProfileService;
import com.example.chatty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    UserService userService;

    @GetMapping("/{profileId}")
    public Profile getById(@PathVariable Long profileId) {
        return profileService.getById(profileId);
    }

    @GetMapping("/get-my-profile")
    public Profile getMyProfile() {
        return profileService.getById(userService.whoAmI().getId());
    }


    @PostMapping("/create")
    public void create(@RequestBody ProfileEntity profileEntity) {
        profileService.create(profileEntity);
    }

    @PutMapping("/{profileId}")
    public void update(@PathVariable Long profileId, @RequestBody ProfileEntity profileEntity) {
        profileEntity.setId(userService.whoAmI().getId()); // Set the ID in case it's not set in the request body
        profileService.update(profileEntity);
    }

    @GetMapping("/getAll")
    public List<Profile> getAll() {
        return profileService.getAll();
    }

}