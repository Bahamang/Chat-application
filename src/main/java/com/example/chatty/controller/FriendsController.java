package com.example.chatty.controller;

import com.example.chatty.models.dto.Profile;
import com.example.chatty.service.FriendsService;
import com.example.chatty.service.ProfileService;
import com.example.chatty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendsController {

    @Autowired
    private FriendsService friendsService;

    @Autowired
    UserService userService;

    @PostMapping("/added")
    public List<Profile> friends() {
        return friendsService.getMyFriends(userService.whoAmI().getId());
    }

    @PostMapping("/other")
    public List<Profile> otherUsers() {
        return friendsService.getOthers(userService.whoAmI().getId());
    }

    @PostMapping("/add/{userIdToAdd}")
    public void addUser(@PathVariable Long userIdToAdd) {
        friendsService.addFriend(userService.whoAmI().getId(), userIdToAdd);
    }

    @PostMapping("/remove/{userIdToRemove}")
    public void removeUser(@PathVariable Long userIdToRemove) {
        friendsService.removeFriend(userService.whoAmI().getId(), userIdToRemove);
    }

}
