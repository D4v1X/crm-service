package controllers;

import annotations.Secured;
import com.google.inject.Inject;
import jooq.objects.tables.pojos.Customer;
import jooq.objects.tables.pojos.User;
import models.customers.NewCustomerRequest;
import models.customers.UpdateCustomerRequest;
import play.libs.Json;
import play.mvc.Result;
import services.CustomerService;
import utils.Constants;

import java.util.concurrent.CompletionStage;

public class CustomerController extends BaseController {

    private CustomerService customerService;

    /**
     * @param customerService the service implementing the customer business logic
     */
    @Inject
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Stores a new {@link Customer} in the database
     *
     * @return HTTP 201 status code if the operation succedeed,
     *         HTTP 400 in case there's a problem with the input or
     *         HTTP 500 in case of error
     */
    @Secured
    public CompletionStage<Result> create() {
        NewCustomerRequest newCustomerRequest = Json.fromJson(request().body().asJson(), NewCustomerRequest.class);
        User userWhoExecuted = (User) ctx().args.get(Constants.USER_WHO_EXECUTED_THE_ACTION);
        return withTransaction(ctx -> customerService.create(ctx, newCustomerRequest, userWhoExecuted.getId()))
                .thenApply(nothing -> created());
    }

    /**
     * Retrieves all the available {@link Customer} objects from the database
     *
     * @return Json object representing the available findAll of customers
     */
    @Secured
    public CompletionStage<Result> findAll() {
        return withTransaction(ctx -> customerService.findAll(ctx))
                .thenApply(customers -> ok(Json.toJson(customers)));
    }

    /**
     * Finds the available {@link Customer} with the given {@link Customer#id}
     *
     * @return HTTP 200 response with a Json object representing the customer if it's found or
     *         HTTP 400 in case it's not found
     */
    @Secured
    public CompletionStage<Result> findById(Integer id) {
        return withTransaction(ctx -> customerService.findCustomerWithDetailsById(ctx, id))
                .thenApply(customer -> customer.isPresent() ? ok(Json.toJson(customer)) : notFound());
    }

    /**
     * Update the {@link Customer} with the given {@link UpdateCustomerRequest} information.
     *
     * @return HTTP 204 status code if the operation succedeed, or
     *         HTTP 400 in case it's not found or
     *         HTTP 500 in case of error
     */
    @Secured
    public CompletionStage<Result> update(Integer customerId) {
        UpdateCustomerRequest updateCustomerRequest = Json.fromJson(request().body().asJson(), UpdateCustomerRequest.class);
        User userWhoExecuted = (User) ctx().args.get(Constants.USER_WHO_EXECUTED_THE_ACTION);
        return withTransaction(ctx -> customerService.update(ctx, customerId, updateCustomerRequest, userWhoExecuted.getId()))
                .thenApply(nothing -> noContent());
    }

    /**
     * delete the {@link Customer} with the given {@link Customer#id}
     *
     * @return HTTP 204 status code if the operation succedeed, or
     *         HTTP 400 in case it's not found or
     *         HTTP 500 in case of error
     */
    @Secured
    public CompletionStage<Result> delete(Integer id) {
        return withTransaction(ctx -> customerService.delete(ctx, id))
                .thenApply(nothing -> noContent());
    }

}
