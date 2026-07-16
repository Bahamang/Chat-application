package com.example.chatty.repository;

import com.example.chatty.models.entity.PostEntity;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.chatty.jooq.tables.Post.POST;

@Repository
public class PostRepository {

    private final DSLContext jooq;

    @Autowired
    public PostRepository(DSLContext dsl) {
        this.jooq = dsl;
    }

    public PostEntity getById(Long postId) {
        return jooq.selectFrom(POST)
                .where(POST.ID.eq(postId))
                .fetchOneInto(PostEntity.class);
    }

    public void update(PostEntity postEntity) {
        jooq.update(POST)
                .set(POST.PROFILE_ID, postEntity.getProfileId())
                .set(POST.TEXT, postEntity.getText())
                .set(POST.MEDIA_ID, postEntity.getMediaId())
                .set(POST.UPDATED, LocalDateTime.now())
                .where(POST.ID.eq(postEntity.getId()))
                .execute();
    }

    public void create(PostEntity postEntity) {
        jooq.insertInto(POST)
                .set(POST.PROFILE_ID, postEntity.getProfileId())
                .set(POST.TEXT, postEntity.getText())
                .set(POST.MEDIA_ID, postEntity.getMediaId())
                .set(POST.CREATED, LocalDateTime.now())
                .set(POST.UPDATED, LocalDateTime.now())
                .set(POST.DISABLED, false)
                .set(POST.DELETED, false)
                .execute();
    }

    public List<PostEntity> getAll() {
        return jooq.selectFrom(POST)
                .orderBy(POST.CREATED.desc())
                .fetchInto(PostEntity.class);
    }


}
