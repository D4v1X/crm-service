package repositories.impl;

import enums.Role;
import exceptions.DatabaseException;
import jooq.objects.tables.pojos.User;
import jooq.objects.tables.records.UserRecord;
import models.users.UserSummary;
import org.jooq.DSLContext;
import repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static jooq.objects.Tables.USER;


public class UserRepositoryImpl implements UserRepository {

    @Override
    public void create(DSLContext create, User user) {
        try {
            UserRecord userRecord = create.newRecord(USER, user);
            create.insertInto(USER)
                    .set(userRecord)
                    .execute();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<UserSummary> findAll(DSLContext create) {
        try {
            return create
                    .select(USER.ID
                            , USER.NAME
                            , USER.EMAIL
                            , USER.ROLE)
                    .from(USER)
                    .where(USER.ACTIVE.eq(true))
                    .fetchInto(UserSummary.class);
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Optional<User> findById(DSLContext create, Integer userId) {
        try {
            return create.selectFrom(USER)
                    .where(USER.ID.eq(userId))
                    .fetchOptionalInto(User.class);
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(DSLContext create, User user) {
        try {
            UserRecord userRecord = create.newRecord(USER, user);
            create.executeUpdate(userRecord);
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(DSLContext create, Integer userId) {

        try {
            /**
             * Delete (Add Cascade)
             * create.delete(USER).where(USER.ID.eq(userId));
             */
            create.update(USER)
                    .set(USER.ACTIVE, false)
                    .where(USER.ID.eq(userId)).execute();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }


    @Override
    public void updateRole(DSLContext create, Integer userId, Role role) {
        try {
            create.update(USER)
                    .set(USER.ROLE, role)
                    .where(USER.ID.eq(userId))
                    .execute();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(DSLContext create, String email) {
        try {
            return create.selectFrom(USER)
                    .where(USER.EMAIL.eq(email))
                    .fetchOptionalInto(User.class);
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void updateUserToken(DSLContext create, Integer userId, String token, LocalDateTime tokenExpiration) {
        try {
            create.update(USER)
                    .set(USER.TOKEN, token)
                    .set(USER.TOKEN_EXPIRATION, tokenExpiration)
                    .where(USER.ID.eq(userId))
                    .execute();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void deleteUserTokenByUserId(DSLContext create, Integer userId) {
        try {
            create.update(USER)
                    .set(USER.TOKEN, (String) null)
                    .set(USER.TOKEN_EXPIRATION, (LocalDateTime) null)
                    .where(USER.ID.eq(userId))
                    .execute();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Optional<User> findByTokenAndUuid(DSLContext create, String userToken, UUID userUuid) {
        try {
            return create.selectFrom(USER)
                    .where(USER.TOKEN.eq(userToken)).and(USER.UUID.eq(userUuid))
                    .fetchOptionalInto(User.class);
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

}
