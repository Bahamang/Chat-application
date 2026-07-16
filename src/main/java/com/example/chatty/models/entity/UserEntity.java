package com.example.chatty.models.entity;

import lombok.*;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "created", nullable = false)
    LocalDateTime created;

    @Column(name = "updated")
    LocalDateTime updated;

    @Column(name = "disabled", nullable = false)
    private boolean disabled;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

}