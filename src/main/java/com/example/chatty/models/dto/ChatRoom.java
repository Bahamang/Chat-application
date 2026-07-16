package com.example.chatty.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class ChatRoom {

    private Long id;
    private Long firstUsersId;
    private Long secondUsersId;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private Long senderId;
    private Profile profile;
    LocalDateTime created;
    LocalDateTime updated;
    private boolean disabled;
    private boolean deleted;


}
