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

}
