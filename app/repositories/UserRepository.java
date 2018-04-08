package repositories;


import enums.Role;
import jooq.objects.tables.pojos.User;
import models.users.UserSummary;
import org.jooq.DSLContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    /**
     * Stores a new {@link User} in the database
     *
     * @param create the {@link DSLContext} with which to access and query the database using
     *               the typesafe SQL Java DSL provided by JOOQ
     * @param user   the new {@link User} to store.
     */
    void create(DSLContext create, User user);

    /**
     * Retrieves all the available {@link User}s
     *
     * @param create the {@link DSLContext} with which to access and query the database using
     *               the typesafe SQL Java DSL provided by JOOQ
     * @return the {@link List} of {@link UserSummary}s
     */
    List<UserSummary> findAll(DSLContext create);

    /**
     * Find the {@link User} by a given ID passed as argument
     *
     * @param create the {@link DSLContext} with which to access and query the database using
     *               the typesafe SQL Java DSL provided by JOOQ
     * @param userId the {@link User#id}
     * @return the {@link User} if it exists or an empty {@link Optional} otherwise
     */
    Optional<User> findById(DSLContext create, Integer userId);

    /**
     * Updates the {@link User} with the information that contains in {@link UserSummary}
     *
     * @param create the {@link DSLContext} with which to access and query the database using
     *               the typesafe SQL Java DSL provided by JOOQ
     * @param user   contains the information that will be used to update the {@link User}.
     */
    void update(DSLContext create, User user);

    /**
     * Delete the specified {@link User}.
     *
     * @param create the {@link DSLContext} with which to access and query the database using
     *               the typesafe SQL Java DSL provided by JOOQ
     * @param userId the {@link User} identifier
     */
    void delete(DSLContext create, Integer userId);

    /**
     * Change the {@link Role} of the {@link User#id} specified.
     *
     * @param create the {@link DSLContext} with which to access and query the database using
     *               the typesafe SQL Java DSL provided by JOOQ
     * @param userId the {@link User} identifier
     * @param role   the new {@link Role} for specified {@link User#id}
     */
    void updateRole(DSLContext create, Integer userId, Role role);

    /**
     * Find the {@link User} by a given email passed as argument
     *
     * @param create the {@link DSLContext} with which to access and query the database using
     *               the typesafe SQL Java DSL provided by JOOQ
     * @param email  the {@link User#email}
     * @return the {@link User} if it exists or an empty {@link Optional} otherwise
     */
    Optional<User> findByEmail(DSLContext create, String email);

    /**
     * Change the {@link User#token} and {@link User#tokenExpiration} of the {@link User#id} specified.
     *
     * @param create          the {@link DSLContext} with which to access and query the database using
     *                        the typesafe SQL Java DSL provided by JOOQ
     * @param userId          the {@link User#id}
     * @param token           the new {@link User#token} for specified {@link User#id}
     * @param tokenExpiration the new {@link User#tokenExpiration} for specified {@link User#id}
     */
    void updateUserToken(DSLContext create, Integer userId, String token, LocalDateTime tokenExpiration);

    /**
     * Delete {@link User#token} of the specified {@link User}.
     *
     * @param create the {@link DSLContext} with which to access and query the database using
     *               the typesafe SQL Java DSL provided by JOOQ
     * @param userId the {@link User#id}
     */
    void deleteUserTokenByUserId(DSLContext create, Integer userId);

    /**
     * Find the {@link User} by a given userToken passed as argument
     *
     * @param create    the {@link DSLContext} with which to access and query the database using
     *                  the typesafe SQL Java DSL provided by JOOQ
     * @param userToken the {@link User#token}
     * @param userUuid  the {@link User#uuid}
     * @return the {@link User} if it exists or an empty {@link Optional} otherwise
     */
    Optional<User> findByTokenAndUuid(DSLContext create, String userToken, UUID userUuid);
}
