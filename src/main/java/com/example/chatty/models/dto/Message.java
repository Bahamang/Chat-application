package com.example.chatty.models.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class Message {

    private Long id;
    private Long senderId;
    private Profile senderProfile;
    private Long sentToId;
    private Long chatId;
    private LocalDateTime messageDate;
    private String text;
    private Long mediaId;
    private Media media;
    private LocalDateTime created;
    private LocalDateTime updated;
    private boolean disabled;
    private boolean deleted;

}
