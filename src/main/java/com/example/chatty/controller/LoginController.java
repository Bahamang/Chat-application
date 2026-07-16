package com.example.chatty.controller;

import com.example.chatty.models.dto.ChatRoom;
import com.example.chatty.models.dto.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @GetMapping("/dummy")
    public Boolean login() {
        return Boolean.TRUE;
    }

}
