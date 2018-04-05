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

    public CompletionStage<Result> list() {
        return CompletableFuture.completedFuture(Results.TODO);
    }

    public CompletionStage<Result> get(Long id) {
        return CompletableFuture.completedFuture(Results.TODO);
    }

    public CompletionStage<Result> update(Long id) {
        return CompletableFuture.completedFuture(Results.TODO);
    }

    public CompletionStage<Result> delete(Long id) {
        return CompletableFuture.completedFuture(Results.TODO);
    }

}
