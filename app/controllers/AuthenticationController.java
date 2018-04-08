package controllers;

import annotations.Secured;
import com.google.inject.Inject;
import jooq.objects.tables.pojos.User;
import models.users.UserLogin;
import play.libs.Json;
import play.mvc.Result;
import services.AuthenticationService;
import utils.Constants;

import java.util.concurrent.CompletionStage;

public class AuthenticationController extends BaseController {

    private AuthenticationService authenticationService;

    /**
     * @param authenticationService the service implementing the authentication business logic
     */
    @Inject
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * login a {@link UserLogin} in the application
     *
     * @return HTTP 200 status code if the operation succedeed,
     *         HTTP 401 in case the information of {@link UserLogin} is not valid or
     *         HTTP 500 in case of error
     */
    public CompletionStage<Result> login() {
        UserLogin userLogin = Json.fromJson(request().body().asJson(), UserLogin.class);
        return withTransaction(ctx -> authenticationService.login(ctx, userLogin))
                .thenApply(userSessionData -> ok(Json.toJson(userSessionData)));
    }

    /**
     * logout a in the application
     *
     * @return HTTP 200 status code if the operation succedeed or
     *         HTTP 500 in case of error
     */
    @Secured
    public CompletionStage<Result> logout() {
        User userWhoExecuted = (User) ctx().args.get(Constants.USER_WHO_EXECUTED_THE_ACTION);
        return withTransaction(ctx -> authenticationService.logout(ctx, userWhoExecuted.getId()))
                .thenApply(nothing -> ok());
    }

}
