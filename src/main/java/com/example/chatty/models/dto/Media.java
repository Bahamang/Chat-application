package com.example.chatty.models.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class Media {

    private Long id;
    private byte[] content;
    private String type;
    LocalDateTime created;
    LocalDateTime updated;
    private boolean disabled;
    private boolean deleted;

}
