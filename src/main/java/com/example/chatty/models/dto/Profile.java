package com.example.chatty.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Profile {

    private Long id;
    private Long userId;
    private String username;
    private Long photoId;
    private Media photo;
    private String description;
    private String language;
    private String friendsId;
    private String occupation;
    private String firstName;
    private String lastName;
    private LocalDateTime birthDate;
    private LocalDateTime registrationDate;
    private LocalDateTime lastLogin;
    private boolean isActive;
    private LocalDateTime created;
    private LocalDateTime updated;
    private boolean disabled;
    private boolean deleted;

}
