package controllers;

import annotations.Secured;
import ch.qos.logback.core.status.ErrorStatus;
import com.google.inject.Inject;
import io.swagger.annotations.*;
import jooq.objects.tables.pojos.User;
import models.users.UserLogin;
import models.users.UserSessionData;
import play.libs.Json;
import play.mvc.Result;
import services.AuthenticationService;
import utils.Constants;

import java.util.concurrent.CompletionStage;

@Api(value = "Authentication Controller", produces = "application/json", consumes = "application/json")
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
    @ApiOperation(value = "login a {@link UserLogin} in the application", response = UserSessionData.class , produces = "application/json", consumes = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "UserLogin", dataType = "UserLogin", paramType = "body", required = true,
                    defaultValue = "{\n" +
                            "    \"email\": \"superadmin@gmail.com\",\n" +
                            "    \"password\": \"superadmin\"\n" +
                            "}"),
            @ApiImplicitParam(name = "password", defaultValue = "admin", dataType = "String", paramType = "form", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 401, message = "Invalid Authorization"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
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
    @ApiOperation(value = "logout a in the application")
    @Secured
    public CompletionStage<Result> logout() {
        User userWhoExecuted = (User) ctx().args.get(Constants.USER_WHO_EXECUTED_THE_ACTION);
        return withTransaction(ctx -> authenticationService.logout(ctx, userWhoExecuted.getId()))
                .thenApply(nothing -> ok());
    }

}
