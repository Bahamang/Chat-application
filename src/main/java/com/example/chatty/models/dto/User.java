package com.example.chatty.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

@Data
public class User {

    private Long id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private LocalDateTime created;
    @JsonIgnore
    private LocalDateTime updated;
    @JsonIgnore
    private boolean disabled;
    @JsonIgnore
    private boolean deleted;

}
