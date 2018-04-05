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


    /**
     * Find the {@link Customer} by a given ID passed as argument
     *
     * @param create     the {@link DSLContext} with which to access and query the database using
     *                   the typesafe SQL Java DSL provided by JOOQ
     * @param customerId the customer identifier
     * @return a Future that, when redeemed, will contain the customer if it exists or an empty {@link Optional} otherwise
     */
    CompletionStage<Optional<CustomerResource>> findById(DSLContext create, Integer customerId);

    /**
     * Updates the {@link Customer} with the information that contains in {@link CustomerResource}
     *
     * @param create           the {@link DSLContext} with which to access and query the database using
     *                         the typesafe SQL Java DSL provided by JOOQ
     * @param customerResource contains the information that will be used to update the customer.
     * @return an empty future that, when successfully completed, means that the customer was successfully updated
     */
    CompletionStage<Void> update(DSLContext create, CustomerResource customerResource);

    /**
     * Delete the specified customer.
     *
     * @param create     the {@link DSLContext} with which to access and query the database using
     *                   the typesafe SQL Java DSL provided by JOOQ
     * @param customerId the customer identifier
     * @return an empty future that, when successfully completed, means that the customer was successfully deleted
     */
    CompletionStage<Void> delete(DSLContext create, Integer customerId);
}
