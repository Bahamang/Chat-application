package com.example.chatty.controller;

import com.example.chatty.models.entity.UserEntity;
import com.example.chatty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    public List<UserEntity> getAll() {
        return userService.getListOfUsers();
    }

    @GetMapping("/{userId}")
    public UserEntity getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/create")
    public void createUser(@RequestBody UserEntity user) {
        userService.createUser(user);
    }

    @PutMapping("/{userId}/disable")
    public void disableUser(@PathVariable Long userId, @RequestBody UserEntity user) {
        UserEntity existingUser = userService.getUserById(userId);
        existingUser.setDisabled(user.isDisabled());
        userService.disableUser(existingUser);
    }

    @PostMapping("/change-password")
    public void changePassword(@RequestBody String password) {
        String pas = "{noop}"+password;
        userService.changePassword(pas);
    }

    @PostMapping("/password-check")
    public boolean checkPassword(@RequestBody String password) {
        String pas = "{noop}"+password;
        return userService.checkPassword(pas);
    }


}
