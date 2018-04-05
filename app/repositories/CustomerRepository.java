package repositories;

import jooq.objects.tables.pojos.Customer;
import models.CustomerResource;
import org.jooq.DSLContext;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    /**
     * Stores a new {@link Customer} in the database
     *
     * @param create           the {@link DSLContext} with which to access and query the database using
     *                         the typesafe SQL Java DSL provided by JOOQ
     * @param customerResource the new customerResource to store.
     */
    void create(DSLContext create, CustomerResource customerResource);

    /**
     * Retrieves all the available {@link Customer}s
     *
     * @param create the {@link DSLContext} with which to access and query the database using
     *               the typesafe SQL Java DSL provided by JOOQ
     * @return the {@link List} of {@link CustomerResource}s
     */
    List<CustomerResource> findAll(DSLContext create);

    /**
     * Find the {@link Customer} by a given ID passed as argument
     *
     * @param create     the {@link DSLContext} with which to access and query the database using
     *                   the typesafe SQL Java DSL provided by JOOQ
     * @param customerId the customer identifier
     * @return the customer if it exists or an empty {@link Optional} otherwise
     */
    Optional<CustomerResource> findById(DSLContext create, Integer customerId);
}
