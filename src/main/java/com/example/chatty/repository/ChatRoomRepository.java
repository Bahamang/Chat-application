package com.example.chatty.repository;

import com.example.chatty.models.entity.ChatRoomEntity;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.chatty.jooq.tables.ChatRoom.CHAT_ROOM;
import static com.example.chatty.jooq.tables.Media.MEDIA;

@Repository
public class ChatRoomRepository {

    private final DSLContext jooq;

    @Autowired
    public ChatRoomRepository(DSLContext dsl) {
        this.jooq = dsl;
    }

    public ChatRoomEntity getById(Long chatRoomId) {
        return jooq.selectFrom(CHAT_ROOM)
                .where(CHAT_ROOM.ID.eq(chatRoomId))
                .fetchOneInto(ChatRoomEntity.class);
    }

    public void update(ChatRoomEntity chatRoomEntity) {
        jooq.update(CHAT_ROOM)
                .set(CHAT_ROOM.FIRST_USERS_ID, chatRoomEntity.getFirstUsersId())
                .set(CHAT_ROOM.SECOND_USERS_ID, chatRoomEntity.getSecondUsersId())
                .set(CHAT_ROOM.UPDATED, LocalDateTime.now())
                .set(CHAT_ROOM.DISABLED, false)
                .set(CHAT_ROOM.DELETED, false)
                .where(CHAT_ROOM.ID.eq(chatRoomEntity.getId()))
                .execute();
    }

    public Long create(ChatRoomEntity chatRoomEntity) {
        Record record =  jooq.insertInto(CHAT_ROOM)
                .set(CHAT_ROOM.FIRST_USERS_ID, chatRoomEntity.getFirstUsersId())
                .set(CHAT_ROOM.SECOND_USERS_ID, chatRoomEntity.getSecondUsersId())
                .set(CHAT_ROOM.CREATED, LocalDateTime.now())
                .set(CHAT_ROOM.UPDATED, LocalDateTime.now())
                .set(CHAT_ROOM.DISABLED, false)
                .set(CHAT_ROOM.DELETED, false)
                .returning(CHAT_ROOM.ID)
                .fetchOne();

        if(record!=null)
            return record.getValue(CHAT_ROOM.ID);
        return 0L;
    }

    public List<ChatRoomEntity> getAll() {
        return jooq.selectFrom(CHAT_ROOM)
                .fetchInto(ChatRoomEntity.class);
    }

    public List<ChatRoomEntity> getChatRoomsByFirstAndSecondUserIds(Long firstUserId) {
        return jooq.selectFrom(CHAT_ROOM)
                .where(CHAT_ROOM.FIRST_USERS_ID.eq(firstUserId)
                        .or(CHAT_ROOM.SECOND_USERS_ID.eq(firstUserId)))
                .orderBy(CHAT_ROOM.CREATED.desc())
                .fetchInto(ChatRoomEntity.class);
    }



}
