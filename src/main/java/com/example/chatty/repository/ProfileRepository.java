package com.example.chatty.repository;

import com.example.chatty.models.entity.ProfileEntity;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.chatty.jooq.tables.Profile.PROFILE;

@Repository
public class ProfileRepository {

    private final DSLContext jooq;

    @Autowired
    public ProfileRepository(DSLContext dsl) {
        this.jooq = dsl;
    }

    public ProfileEntity getById(Long profileId) {
        return jooq.select()
                .from(PROFILE)
                .where(PROFILE.ID.eq(profileId))
                .fetchOneInto(ProfileEntity.class);
    }

    public ProfileEntity getByUserId(Long userId) {
        return jooq.select()
                .from(PROFILE)
                .where(PROFILE.USER_ID.eq(userId))
                .fetchOneInto(ProfileEntity.class);
    }

    public List<ProfileEntity> getByListId(List<Long> profilesId) {
        return jooq.select()
                .from(PROFILE)
                .where(PROFILE.ID.in(profilesId))
                .fetchInto(ProfileEntity.class);
    }

    public List<ProfileEntity> getByNotInListId(List<Long> profilesId) {
        return jooq.select()
                .from(PROFILE)
                .where(PROFILE.ID.notIn(profilesId))
                .fetchInto(ProfileEntity.class);
    }

    public void update(ProfileEntity profileEntity) {
        jooq.update(PROFILE)
                .set(PROFILE.USER_ID, profileEntity.getUserId())
                .set(PROFILE.USERNAME, profileEntity.getUsername())
                .set(PROFILE.PHOTO_ID, profileEntity.getPhotoId())
                .set(PROFILE.DESCRIPTION, profileEntity.getDescription())
                .set(PROFILE.LANGUAGE, profileEntity.getLanguage())
                .set(PROFILE.FRIENDS_ID, profileEntity.getFriendsId())
                .set(PROFILE.OCCUPATION, profileEntity.getOccupation())
                .set(PROFILE.FIRST_NAME, profileEntity.getFirstName())
                .set(PROFILE.LAST_NAME, profileEntity.getLastName())
                .set(PROFILE.BIRTH_DATE, profileEntity.getBirthDate())
                .set(PROFILE.REGISTRATION_DATE, profileEntity.getRegistrationDate())
                .set(PROFILE.LAST_LOGIN, profileEntity.getLastLogin())
                .set(PROFILE.IS_ACTIVE, profileEntity.isActive())
                .set(PROFILE.UPDATED, LocalDateTime.now())
                .where(PROFILE.ID.eq(profileEntity.getId()))
                .execute();
    }

    public void create(ProfileEntity profileEntity) {
        jooq.insertInto(PROFILE)
                .set(PROFILE.USER_ID, profileEntity.getUserId())
                .set(PROFILE.USERNAME, profileEntity.getUsername())
                .set(PROFILE.PHOTO_ID, profileEntity.getPhotoId())
                .set(PROFILE.DESCRIPTION, profileEntity.getDescription())
                .set(PROFILE.LANGUAGE, profileEntity.getLanguage())
                .set(PROFILE.FRIENDS_ID, profileEntity.getFriendsId())
                .set(PROFILE.OCCUPATION, profileEntity.getOccupation())
                .set(PROFILE.FIRST_NAME, profileEntity.getFirstName())
                .set(PROFILE.LAST_NAME, profileEntity.getLastName())
                .set(PROFILE.BIRTH_DATE, profileEntity.getBirthDate())
                .set(PROFILE.REGISTRATION_DATE, profileEntity.getRegistrationDate())
                .set(PROFILE.LAST_LOGIN, profileEntity.getLastLogin())
                .set(PROFILE.IS_ACTIVE, profileEntity.isActive())
                .set(PROFILE.CREATED, LocalDateTime.now())
                .set(PROFILE.UPDATED, LocalDateTime.now())
                .set(PROFILE.DISABLED, false)
                .set(PROFILE.DELETED, false)
                .execute();
    }

    public List<ProfileEntity> getAll() {
        return jooq.selectFrom(PROFILE)
                .fetchInto(ProfileEntity.class);
    }

}
