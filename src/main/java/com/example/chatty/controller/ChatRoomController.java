package com.example.chatty.controller;

import com.example.chatty.models.dto.ChatRoom;
import com.example.chatty.models.dto.Profile;
import com.example.chatty.models.dto.User;
import com.example.chatty.models.entity.ChatRoomEntity;
import com.example.chatty.service.ChatRoomService;
import com.example.chatty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatrooms")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    UserService userService;

    @GetMapping("/myChat/{chatRoomId}")
    public ChatRoom getById(@PathVariable Long chatRoomId) {
        return chatRoomService.getById(chatRoomId, userService.whoAmI().getId());
    }

    @PostMapping("/create")
    public void create(@RequestBody ChatRoomEntity chatRoomEntity) {
        chatRoomService.create(chatRoomEntity);
    }

    @PutMapping("/update/{chatRoomId}")
    public void update(@PathVariable Long chatRoomId, @RequestBody ChatRoomEntity chatRoomEntity) {
        chatRoomEntity.setId(chatRoomId); // Set the ID in case it's not set in the request body
        chatRoomService.update(chatRoomEntity);
    }

    @GetMapping("/all-my-chats")
    public List<ChatRoom> getChatRoomsByFirstAndSecondUserIds() {
        return chatRoomService.getChatRoomsByFirstAndSecondUserIds(userService.whoAmI().getId());
    }

    @GetMapping("/start-chat")
    public List<Profile> startChat() {
        return chatRoomService.startChat();
    }

    @GetMapping("/create-new-chat/{secondUser}")
    public ChatRoom createNewChat(@PathVariable Long secondUser) {
        return chatRoomService.createNewChat(secondUser);
    }

}