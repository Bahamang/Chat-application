package com.example.chatty.repository;

import com.example.chatty.models.entity.MediaEntity;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.chatty.jooq.tables.Media.MEDIA;

@Repository
public class MediaRepository {

    private final DSLContext jooq;

    @Autowired
    public MediaRepository(DSLContext dsl) {
        this.jooq = dsl;
    }



    public MediaEntity getById(Long id) {
        return jooq.selectFrom(MEDIA)
                .where(MEDIA.ID.eq(id))
                .fetchOneInto(MediaEntity.class);
    }

    public void update(MediaEntity entity) {
        jooq.update(MEDIA)
                .set(MEDIA.CONTENT, entity.getContent())
                .set(MEDIA.TYPE, entity.getType())
                .set(MEDIA.UPDATED, LocalDateTime.now())
                .set(MEDIA.DISABLED, false)
                .set(MEDIA.DELETED, false)
                .where(MEDIA.ID.eq(entity.getId()))
                .execute();
    }

    public Long create(MediaEntity entity) {
        Record record =  jooq.insertInto(MEDIA)
                .set(MEDIA.CONTENT, entity.getContent())
                .set(MEDIA.TYPE, entity.getType())
                .set(MEDIA.CREATED, LocalDateTime.now())
                .set(MEDIA.UPDATED, LocalDateTime.now())
                .set(MEDIA.DISABLED, false)
                .set(MEDIA.DELETED, false)
                .returning(MEDIA.ID)
                .fetchOne();

        if(record!=null)
            return record.getValue(MEDIA.ID);
        return 0L;
    }

    public List<MediaEntity> getAll() {
        return jooq.selectFrom(MEDIA)
                .fetchInto(MediaEntity.class);
    }


}
