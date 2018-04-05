package controllers;

import com.google.inject.Inject;
import jooq.objects.tables.pojos.Customer;
import models.CustomerResource;
import play.libs.Json;
import play.mvc.Result;
import services.CustomerService;

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
     * Stores a new product in the database
     *
     * @return HTTP 201 status code if the operation succedeed,
     *         HTTP 400 in case there's a problem with the input or
     *         HTTP 500 in case of error
     */
    public CompletionStage<Result> create() {
        CustomerResource customerResource = Json.fromJson(request().body().asJson(), CustomerResource.class);
        return withTransaction(ctx -> customerService.create(ctx, customerResource))
                .thenApply(nothing -> created());
    }

    /**
     * Retrieves all the available {@link Customer} objects from the database
     *
     * @return Json object representing the available findAll of customers
     */
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
    public CompletionStage<Result> findById(Integer id) {
        return withTransaction(ctx -> customerService.findById(ctx, id))
                .thenApply(customer -> {
                    if (customer.isPresent())
                        return ok(Json.toJson(customer));
                    else
                        return notFound();
                });
    }

    /**
     * Update the {@link Customer} with the given {@link CustomerResource} information.
     *
     * @return HTTP 204 status code if the operation succedeed, or
     *         HTTP 400 in case it's not found or
     *         HTTP 500 in case of error
     */
    public CompletionStage<Result> update() {
        CustomerResource customerResource = Json.fromJson(request().body().asJson(), CustomerResource.class);
        return withTransaction(ctx -> customerService.update(ctx, customerResource))
                .thenApply(nothing -> noContent());
    }

    /**
     * delete the {@link Customer} with the given {@link Customer#id}
     *
     * @return HTTP 204 status code if the operation succedeed, or
     *         HTTP 400 in case it's not found or
     *         HTTP 500 in case of error
     */
    public CompletionStage<Result> delete(Integer id) {
        return withTransaction(ctx -> customerService.delete(ctx, id))
                .thenApply(nothing -> noContent());
    }

}
