package services;

import jooq.objects.tables.pojos.User;
import models.users.NewUserRequest;
import models.users.UpdateUserRequest;
import models.users.UserSummary;
import org.jooq.DSLContext;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface UserService {

    /**
     * Stores an {@link User} in the database.
     *
     * @param create         the {@link DSLContext} with which to access and query the database using
     *                       the typesafe SQL Java DSL provided by JOOQ
     * @param newUserRequest the new {@link User} coming from the client-side
     * @return an empty future that, when successfully completed, means that the {@link User} was successfully created
     */
    CompletionStage<Void> create(DSLContext create, NewUserRequest newUserRequest);

    /**
     * Retrieves all the {@link UserSummary} objects making use of the {@link DSLContext} provided
     * to access the database
     *
     * @param create the {@link DSLContext} with which to access and query the database using
     *               the typesafe SQL Java DSL provided by JOOQ
     * @return a Future that, when redeemed, will contain the {@link List} of {@link UserSummary}s
     */
    CompletionStage<List<UserSummary>> findAll(DSLContext create);

    /**
     * Updates the {@link User} with the information that contains in {@link UserSummary}
     *
     * @param create            the {@link DSLContext} with which to access and query the database using
     *                          the typesafe SQL Java DSL provided by JOOQ
     * @param userId            the {@link User#id}
     * @param updateUserRequest contains the information that will be used to update the {@link User}.
     * @return an empty future that, when successfully completed, means that the {@link User} was successfully updated
     */
    CompletionStage<Void> update(DSLContext create, Integer userId, UpdateUserRequest updateUserRequest);

    /**
     * Delete the specified {@link User}.
     *
     * @param create the {@link DSLContext} with which to access and query the database using
     *               the typesafe SQL Java DSL provided by JOOQ
     * @param userId the {@link User#id}
     * @return an empty future that, when successfully completed, means that the {@link User} was successfully deleted
     */
    CompletionStage<Void> delete(DSLContext create, Integer userId);

    /**
     * Change the admin status of the specified {@link User}.
     *
     * @param create the {@link DSLContext} with which to access and query the database using
     *               the typesafe SQL Java DSL provided by JOOQ
     * @param userId the {@link User#id}
     * @return an empty future that, when successfully completed, means that the {@link User} was successfully deleted
     */
    CompletionStage<Void> changeAdminStatus(DSLContext create, Integer userId);
}
