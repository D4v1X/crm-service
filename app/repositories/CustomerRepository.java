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
     *
     * @return a future that, if completed successfully, means that the customerResource was successfully stored
     */
    CompletionStage<Void> create(DSLContext create, CustomerResource customerResource);
}
