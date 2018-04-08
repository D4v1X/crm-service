package services;

import jooq.objects.tables.pojos.Customer;
import models.customers.*;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public interface CustomerService {

    /**
     * Stores an {@link Customer} in the database.
     *
     * @param create             the {@link DSLContext} with which to access and query the database using
     *                           the typesafe SQL Java DSL provided by JOOQ
     * @param newCustomerRequest the {@link Customer} information coming from the client-side
     * @param userWhoExecutedId  the user id of used that executed create action.
     * @return an empty future that, when successfully completed, means that the {@link Customer} was successfully created
     */
    CompletionStage<Void> create(DSLContext create, NewCustomerRequest newCustomerRequest, Integer userWhoExecutedId);

    /**
     * Retrieves all the {@link CustomerSummary} objects making use of the {@link DSLContext} provided
     * to access the database
     *
     * @param create the {@link DSLContext} with which to access and query the database using
     *               the typesafe SQL Java DSL provided by JOOQ
     * @return a Future that, when redeemed, will contain the {@link List} of {@link CustomerSummary}s
     */
    CompletionStage<List<CustomerSummary>> findAll(DSLContext create);


    /**
     * Find the {@link Customer} by a given ID passed as argument
     *
     * @param create     the {@link DSLContext} with which to access and query the database using
     *                   the typesafe SQL Java DSL provided by JOOQ
     * @param customerId the {@link Customer#id}
     * @return a Future that, when redeemed, will contain the {@link CustomerWithDetails} if it exists or an empty {@link Optional} otherwise
     */
    CompletionStage<Optional<CustomerWithDetails>> findCustomerWithDetailsById(DSLContext create, Integer customerId);

    /**
     * Updates the {@link Customer} with the information that contains in {@link UpdateCustomerRequest}
     *
     * @param create                the {@link DSLContext} with which to access and query the database using
     *                              the typesafe SQL Java DSL provided by JOOQ
     * @param customerId            the {@link Customer#id}
     * @param updateCustomerRequest contains the information that will be used to update the {@link Customer}.
     * @param userWhoExecutedId     the user id of used that executed create action.
     * @return an empty future that, when successfully completed, means that the {@link Customer} was successfully updated
     */
    CompletionStage<Void> update(DSLContext create, Integer customerId, UpdateCustomerRequest updateCustomerRequest, Integer userWhoExecutedId);

    /**
     * Delete the specified {@link Customer}.
     *
     * @param create     the {@link DSLContext} with which to access and query the database using
     *                   the typesafe SQL Java DSL provided by JOOQ
     * @param customerId the {@link Customer#id}
     * @return an empty future that, when successfully completed, means that the {@link Customer} was successfully deleted
     */
    CompletionStage<Void> delete(DSLContext create, Integer customerId);
}
