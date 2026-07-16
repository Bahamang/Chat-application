package com.example.chatty.models.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Table(name = "chat_room")
@Data
@Builder
public class ChatRoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_users_id")
    private Long firstUsersId;

    @Column(name = "second_users_id")
    private Long secondUsersId;

    @Column(name = "created")
    LocalDateTime created;

    @Column(name = "updated")
    LocalDateTime updated;

    @Column(name = "disabled")
    private boolean disabled;

    @Column(name = "deleted")
    private boolean deleted;

}
