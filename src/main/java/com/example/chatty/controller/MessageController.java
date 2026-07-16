package com.example.chatty.controller;

import com.example.chatty.models.dto.Message;
import com.example.chatty.models.entity.MessageEntity;
import com.example.chatty.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/get-message/{messageId}")
    public MessageEntity getById(@PathVariable Long messageId) {
        return messageService.getById(messageId);
    }

    @GetMapping("/get-by-chat-id/{chatId}")
    public List<Message> getByChatId(@PathVariable Long chatId) {
        return messageService.getByChatId(chatId);
    }

    @PostMapping("/create")
    public void create(@RequestBody MessageEntity messageEntity) {
        messageService.create(messageEntity);
    }

    @PutMapping("/{messageId}")
    public void update(@PathVariable Long messageId, @RequestBody MessageEntity messageEntity) {
        messageEntity.setId(messageId); // Set the ID in case it's not set in the request body
        messageService.update(messageEntity);
    }

    @GetMapping("/getAll")
    public List<Message> getAll() {
        return messageService.getAll();
    }
}