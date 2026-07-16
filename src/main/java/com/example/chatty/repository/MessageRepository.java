package com.example.chatty.repository;

import com.example.chatty.models.entity.MessageEntity;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.chatty.jooq.tables.Message.MESSAGE;

@Repository
public class MessageRepository {

    private final DSLContext jooq;

    @Autowired
    public MessageRepository(DSLContext dsl) {
        this.jooq = dsl;
    }

    public MessageEntity getById(Long messageId) {
        return jooq.selectFrom(MESSAGE)
                .where(MESSAGE.ID.eq(messageId))
                .fetchOneInto(MessageEntity.class);
    }

    public List<MessageEntity> getByChatId(Long chatId) {
        return jooq.selectFrom(MESSAGE)
                .where(MESSAGE.CHAT_ID.eq(chatId))
                .fetchInto(MessageEntity.class);
    }

    public MessageEntity getLastMessage(Long chatId) {
        List<MessageEntity> messageEntities = jooq.selectFrom(MESSAGE)
                .where(MESSAGE.CHAT_ID.eq(chatId))
                .orderBy(MESSAGE.CREATED.desc())
                .limit(1)
                .fetchInto(MessageEntity.class);
        if(!messageEntities.isEmpty())
            return messageEntities.get(0);
        return new MessageEntity();
    }


    public void update(MessageEntity messageEntity) {
        jooq.update(MESSAGE)
                .set(MESSAGE.SENDER_ID, messageEntity.getSenderId())
                .set(MESSAGE.SENT_TO_ID, messageEntity.getSentToId())
                .set(MESSAGE.TEXT, messageEntity.getText())
                .set(MESSAGE.MEDIA_ID, messageEntity.getMediaId())
                .set(MESSAGE.UPDATED,  LocalDateTime.now())
                .where(MESSAGE.ID.eq(messageEntity.getId()))
                .execute();
    }

    public void create(MessageEntity messageEntity) {
        jooq.insertInto(MESSAGE)
                .set(MESSAGE.SENDER_ID, messageEntity.getSenderId())
                .set(MESSAGE.SENT_TO_ID, messageEntity.getSentToId())
                .set(MESSAGE.CHAT_ID, messageEntity.getChatId())
                .set(MESSAGE.MESSAGE_DATE,  LocalDateTime.now())
                .set(MESSAGE.TEXT, messageEntity.getText())
                .set(MESSAGE.MEDIA_ID, messageEntity.getMediaId())
                .set(MESSAGE.CREATED, LocalDateTime.now())
                .set(MESSAGE.UPDATED,  LocalDateTime.now())
                .set(MESSAGE.DISABLED, false)
                .set(MESSAGE.DELETED, false)
                .execute();
    }

    public List<MessageEntity> getAll() {
        return jooq.selectFrom(MESSAGE)
                .fetchInto(MessageEntity.class);
    }

}
