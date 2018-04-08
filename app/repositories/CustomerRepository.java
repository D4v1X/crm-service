package repositories;

import jooq.objects.tables.pojos.Customer;
import models.customers.*;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    /**
     * Stores a new {@link Customer} in the database
     *
     * @param create   the {@link DSLContext} with which to access and query the database using
     *                 the typesafe SQL Java DSL provided by JOOQ
     * @param customer the new {@link Customer} to store.
     */
    void create(DSLContext create, Customer customer);

    /**
     * Retrieves all the available {@link Customer}s
     *
     * @param create the {@link DSLContext} with which to access and query the database using
     *               the typesafe SQL Java DSL provided by JOOQ
     * @return the {@link List} of {@link CustomerSummary}s
     */
    List<CustomerSummary> findAll(DSLContext create);

    /**
     * Find the {@link Customer} by a given ID passed as argument
     *
     * @param create     the {@link DSLContext} with which to access and query the database using
     *                   the typesafe SQL Java DSL provided by JOOQ
     * @param customerId the customer identifier
     * @return the customer if it exists or an empty {@link Optional} otherwise
     */
    Optional<Customer> findById(DSLContext create, Integer customerId);

    /**
     * Find the {@link CustomerWithDetails} by a given ID passed as argument
     *
     * @param create     the {@link DSLContext} with which to access and query the database using
     *                   the typesafe SQL Java DSL provided by JOOQ
     * @param customerId the customer identifier
     * @return the customer if it exists or an empty {@link Optional} otherwise
     */
    Optional<CustomerWithDetails> findCustomerWithDetailsById(DSLContext create, Integer customerId);

    /**
     * Updates the {@link Customer} with the information that contains in {@link Customer}
     *
     * @param create   the {@link DSLContext} with which to access and query the database using
     *                 the typesafe SQL Java DSL provided by JOOQ
     * @param customer contains the information that will be used to update the {@link Customer}.
     */
    void update(DSLContext create, Customer customer);

    /**
     * Delete the specified {@link Customer}.
     *
     * @param create     the {@link DSLContext} with which to access and query the database using
     *                   the typesafe SQL Java DSL provided by JOOQ
     * @param customerId the {@link Customer#id}
     */
    void delete(DSLContext create, Integer customerId);
}
