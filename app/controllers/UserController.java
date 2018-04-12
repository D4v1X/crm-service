package controllers;

import annotations.Secured;
import com.google.inject.Inject;
import enums.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jooq.objects.tables.pojos.User;
import models.users.NewUserRequest;
import models.users.UpdateUserRequest;
import models.users.UserSessionData;
import models.users.UserSummary;
import play.libs.Json;
import play.mvc.Result;
import services.UserService;
import utils.Validation;
import utils.Validator;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

@Api(value = "User Controller", produces = "application/json", consumes = "application/json")
public class UserController extends BaseController {

    private UserService userService;

    /**
     * @param userService the service implementing the user business logic
     */
    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Stores a new {@link User} in the database
     *
     * @return HTTP 201 status code if the operation succedeed,
     *         HTTP 400 in case there's a problem with the input or
     *         HTTP 500 in case of error
     */
    @ApiOperation(value = "Stores a new {@link User} in the database")
    @Secured(rolesAllowed = {Role.ADMIN})
    public CompletionStage<Result> create() {
        NewUserRequest newUserRequest = Json.fromJson(request().body().asJson(), NewUserRequest.class);
        return withTransaction(ctx -> userService.create(ctx, newUserRequest))
                .thenApply(nothing -> created());
    }

    /**
     * Retrieves all the available {@link User} objects from the database
     *
     * @return Json object representing the available findAll of users
     */
    @ApiOperation(value = "login a {@link UserLogin} in the application", response = UserSummary.class)
    @Secured(rolesAllowed = {Role.ADMIN})
    public CompletionStage<Result> findAll() {
        return withTransaction(ctx -> userService.findAll(ctx))
                .thenApply(users -> ok(Json.toJson(users)));
    }

    /**
     * Update the {@link User} with the given {@link UserSummary} information.
     *
     * @return HTTP 204 status code if the operation succedeed, or
     *         HTTP 400 in case it's not found or
     *         HTTP 500 in case of error
     */
    @ApiOperation(value = "Update the {@link User} with the given {@link UserSummary} information.")
    @Secured(rolesAllowed = {Role.ADMIN})
    public CompletionStage<Result> update(Integer customerId) {
        UpdateUserRequest updateUserRequest = Json.fromJson(request().body().asJson(), UpdateUserRequest.class);
        return withTransaction(ctx -> userService.update(ctx, customerId, updateUserRequest))
                .thenApply(nothing -> noContent());
    }

    /**
     * Delete the {@link User} with the given {@link User#id}
     *
     * @return HTTP 204 status code if the operation succedeed, or
     *         HTTP 400 in case it's not found or
     *         HTTP 500 in case of error
     */
    @ApiOperation(value = "Delete the {@link User} with the given {@link User#id}")
    @Secured(rolesAllowed = {Role.ADMIN})
    public CompletionStage<Result> delete(Integer id) {
        return withTransaction(ctx -> userService.delete(ctx, id))
                .thenApply(nothing -> noContent());
    }

    /**
     * Change the admin status of the specified {@link User#id}.
     *
     * @return HTTP 204 status code if the operation succedeed, or
     *         HTTP 400 in case it's not found or
     *         HTTP 500 in case of error
     */
    @ApiOperation(value = "Change the admin status of the specified {@link User#id}.")
    @Secured(rolesAllowed = {Role.ADMIN})
    public CompletionStage<Result> changeAdminStatus(Integer id) {
        return withTransaction(ctx -> userService.changeAdminStatus(ctx, id))
                .thenApply(nothing -> ok());
    }
}
