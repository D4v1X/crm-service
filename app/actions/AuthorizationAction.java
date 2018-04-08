package actions;

import annotations.Secured;
import exceptions.DatabaseException;
import jooq.objects.tables.pojos.User;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import play.Logger;
import play.db.Database;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import repositories.UserRepository;
import utils.Constants;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class AuthorizationAction extends Action<Secured> {

    @Inject
    private Database database;

    private UserRepository userRepository;

    @Inject
    public AuthorizationAction(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {

        Logger.info("Calling AuthorizationAction for {}", ctx);

        Optional<String> optionalOfUserToken = getTokenFromRequest(ctx.request());
        if (!optionalOfUserToken.isPresent())
            return CompletableFuture.completedFuture(
                    Results.unauthorized("The request does not contain the token."));

        Optional<UUID> optionalOfUserUuid = getUuidFromRequest(ctx.request());
        if (!optionalOfUserUuid.isPresent())
            return CompletableFuture.completedFuture(
                    Results.unauthorized("The request does not contain the uuid."));

        try (Connection connection = acquireConnection()) {

            DSLContext dslContext = DSL.using(connection, SQLDialect.POSTGRES, new Settings());

            Optional<User> optionalOfUser = userRepository.findByTokenAndUuid(dslContext, optionalOfUserToken.get(), optionalOfUserUuid.get());
            if (!optionalOfUser.isPresent())
                return CompletableFuture.completedFuture(
                        Results.unauthorized("The combination of uuid and token is not valid."));

            User user = optionalOfUser.get();

            if (expiredToken(user.getTokenExpiration()))
                return CompletableFuture.completedFuture(
                        Results.unauthorized("The token is expired"));

            if (!Arrays.asList(configuration.rolesAllowed()).contains(user.getRole()))
                return CompletableFuture.completedFuture(
                        Results.unauthorized("The user has not enough privileges."));

            ctx.args.put(Constants.USER_WHO_EXECUTED_THE_ACTION, user);

            return delegate.call(ctx);

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

    }

    private boolean expiredToken(LocalDateTime tokenExpiration) {
        return tokenExpiration.isBefore(LocalDateTime.now(Constants.DEFAULT_TIMEZONE));
    }

    /**
     * Method that extracts the token's uuid from the request.
     *
     * @param request {@link Http.Request}
     * @return a {@link Optional} will contain the {@link String} if it exists in the {@link Http.Request} or an empty {@link Optional} otherwise.
     */
    private Optional<String> getTokenFromRequest(Http.Request request) {
        Http.Headers headers = request.getHeaders();
        return headers.get(Constants.USER_TOKEN_HEADER);
    }

    /**
     * Method that extracts the user's uuid from the request.
     *
     * @param request {@link Http.Request}
     * @return a {@link Optional} will contain the {@link UUID} if it exists in the {@link Http.Request} or an empty {@link Optional} otherwise.
     */
    private Optional<UUID> getUuidFromRequest(Http.Request request) {
        try {
            Http.Headers headers = request.getHeaders();
            return headers.get(Constants.USER_UUID_HEADER).map(UUID::fromString);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    /**
     * Tries to obtain a database connection from the datasource configured
     *
     * @return a new {@link Connection}
     */
    private Connection acquireConnection() {

        try {
            Connection connection = database.getDataSource().getConnection();
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

}
