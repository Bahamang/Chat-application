package com.example.chatty.models.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class Post {

    private Long id;
    private Profile profile;
    private Long profileId;
    private String text;
    private Media media;
    private Long mediaId;
    LocalDateTime created;
    LocalDateTime updated;
    private boolean disabled;
    private boolean deleted;

}
