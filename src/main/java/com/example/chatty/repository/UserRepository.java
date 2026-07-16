package com.example.chatty.repository;
import com.example.chatty.jooq.Tables;
import com.example.chatty.jooq.tables.pojos.Users;

import com.example.chatty.models.entity.UserEntity;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


/// TODO: change user to user entity for communication

@Repository
public class UserRepository {

    private final DSLContext jooq;

    @Autowired
    public UserRepository(DSLContext dsl) {
        this.jooq = dsl;
    }

    public List<UserEntity> getAllUsers() {
        return jooq.selectFrom(Tables.USERS).fetchInto(UserEntity.class);
    }

    public UserEntity getUserById(Long userId) {
        return jooq.select()
                .from(Tables.USERS)
                .where(Tables.USERS.ID.eq(userId))
                .fetchOneInto(UserEntity.class);
    }
    public UserEntity getUserByName(String username) {
        return jooq.select()
                .from(Tables.USERS)
                .where(Tables.USERS.USERNAME.eq(username))
                .fetchOneInto(UserEntity.class);
    }

    public UserEntity getUserByEmail(String username) {
        return jooq.select()
                .from(Tables.USERS)
                .where(Tables.USERS.EMAIL.eq(username))
                .fetchOneInto(UserEntity.class);
    }

    public void createUser(UserEntity user) {
        jooq.insertInto(Tables.USERS)
                .set(Tables.USERS.USERNAME, user.getUsername())
                .set(Tables.USERS.EMAIL, user.getEmail())
                .set(Tables.USERS.PASSWORD, user.getPassword())
                .set(Tables.USERS.DELETED, false)
                .set(Tables.USERS.DISABLED, false)
                .set(Tables.USERS.CREATED, LocalDateTime.now())
                .set(Tables.USERS.UPDATED, LocalDateTime.now())
                .execute();
    }

    public void disableUser(UserEntity user) {
        jooq.update(Tables.USERS)
                .set(Tables.USERS.DISABLED, user.isDisabled())
                .where(Tables.USERS.ID.eq(user.getId()));
    }

    public void changePassword(UserEntity user) {
        jooq.update(Tables.USERS)
                .set(Tables.USERS.PASSWORD, user.getPassword())
                .where(Tables.USERS.ID.eq(user.getId()))
                .execute();
    }

    public List<UserEntity> getUsersNotInSet(Set<Long> ids) {
        return jooq.select().from(Tables.USERS)
                .where(Tables.USERS.ID.notIn(ids))
                .fetchInto(UserEntity.class);
    }

}
