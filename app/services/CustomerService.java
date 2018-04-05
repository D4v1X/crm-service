package services;

import jooq.objects.tables.pojos.Customer;
import models.CustomerResource;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public interface CustomerService {

    /**
     * Stores an user in the database.
     *
     * @param create           the {@link DSLContext} with which to access and query the database using
     *                         the typesafe SQL Java DSL provided by JOOQ
     * @param customerResource the customer coming from the client-side
     * @return an empty future that, when successfully completed, means that the customer was successfully created
     */
    CompletionStage<Void> create(DSLContext create, CustomerResource customerResource);

    /**
     * Retrieves all the {@link CustomerResource} objects making use of the {@link DSLContext} provided
     * to access the database
     *
     * @param create the {@link DSLContext} with which to access and query the database using
     *               the typesafe SQL Java DSL provided by JOOQ
     * @return a Future that, when redeemed, will contain the {@link List} of {@link CustomerResource}s
     */
    CompletionStage<List<CustomerResource>> findAll(DSLContext create);
}
