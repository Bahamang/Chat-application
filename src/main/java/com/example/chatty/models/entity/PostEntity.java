package com.example.chatty.models.entity;


import lombok.Data;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Data
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "text")
    private String text;

    @Column(name = "media_id")
    private Long mediaId;

    @Column(name = "created")
    LocalDateTime created;

    @Column(name = "updated")
    LocalDateTime updated;

    @Column(name = "disabled")
    private boolean disabled;

    @Column(name = "deleted")
    private boolean deleted;

}
