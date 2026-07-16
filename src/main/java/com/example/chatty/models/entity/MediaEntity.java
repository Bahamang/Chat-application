package com.example.chatty.models.entity;

import lombok.Data;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "media")
@Data
public class MediaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content")
    private byte[] content;

    @Column(name = "type")
    private String type;

    @Column(name = "created")
    LocalDateTime created;

    @Column(name = "updated")
    LocalDateTime updated;

    @Column(name = "disabled")
    private boolean disabled;

    @Column(name = "deleted")
    private boolean deleted;

}
