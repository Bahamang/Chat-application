package com.example.chatty.models.entity;
import lombok.Data;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Data
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "sent_to_id")
    private Long sentToId;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "message_date")
    LocalDateTime messageDate;

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
