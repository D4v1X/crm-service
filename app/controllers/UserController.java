package controllers;

import annotations.Secured;
import com.google.inject.Inject;
import enums.Role;
import jooq.objects.tables.pojos.User;
import models.users.NewUserRequest;
import models.users.UpdateUserRequest;
import models.users.UserSummary;
import play.libs.Json;
import play.mvc.Result;
import services.UserService;
import utils.Validation;
import utils.Validator;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

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
    @Secured(rolesAllowed = {Role.ADMIN})
    public CompletionStage<Result> create() {
        NewUserRequest newUserRequest = Json.fromJson(request().body().asJson(), NewUserRequest.class);

        Validator.apply(
                Validation.with(
                        Optional.ofNullable(newUserRequest.getEmail()).isPresent(),
                        "Email is required fields"),
                Validation.with(
                        Optional.ofNullable(newUserRequest.getPassword()).isPresent(),
                        "Password is required fields")
        ).validate();

        return withTransaction(ctx -> userService.create(ctx, newUserRequest))
                .thenApply(nothing -> created());
    }

    /**
     * Retrieves all the available {@link User} objects from the database
     *
     * @return Json object representing the available findAll of users
     */
    @Secured(rolesAllowed = {Role.ADMIN})
    public CompletionStage<Result> findAll() {
        return withTransaction(ctx -> userService.findAll(ctx))
                .thenApply(customers -> ok(Json.toJson(customers)));
    }

    /**
     * Update the {@link User} with the given {@link UserSummary} information.
     *
     * @return HTTP 204 status code if the operation succedeed, or
     *         HTTP 400 in case it's not found or
     *         HTTP 500 in case of error
     */
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
    @Secured(rolesAllowed = {Role.ADMIN})
    public CompletionStage<Result> changeAdminStatus(Integer id) {
        return withTransaction(ctx -> userService.changeAdminStatus(ctx, id))
                .thenApply(nothing -> ok());
    }
}
