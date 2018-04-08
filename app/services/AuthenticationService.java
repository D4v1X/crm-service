package services;

import models.users.UserLogin;
import models.users.UserSessionData;
import org.jooq.DSLContext;

import java.util.concurrent.CompletionStage;

public interface AuthenticationService {

    /**
     * Authenticate the given {@link UserLogin#email} and {@link UserLogin#password}
     *
     * @param create    the {@link DSLContext} with which to access and query the database using
     *                  the typesafe SQL Java DSL provided by JOOQ.
     * @param userLogin the necessary information for login.
     * @return a Future that, when redeemed, will contain the {@link UserSessionData}
     */
    CompletionStage<UserSessionData> login(DSLContext create, UserLogin userLogin);

    /**
     * Log-out the given user identity.
     *
     * @param create the {@link DSLContext} with which to access and query the database using
     *               the typesafe SQL Java DSL provided by JOOQ.
     * @param userId the user identity.
     * @return an empty future that, when successfully completed, means that the user was successfully logout
     */
    CompletionStage<Void> logout(DSLContext create, Integer userId);
}
