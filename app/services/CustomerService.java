package services;

import models.CustomerResource;
import org.jooq.DSLContext;

import java.util.concurrent.CompletionStage;

public interface CustomerService {

    /**
     * Stores an user in the database.
     *
     * @param create           the {@link DSLContext} with which to access and query the database using
     *                         the typesafe SQL Java DSL provided by JOOQ
     * @param customerResource the customer coming from the client-side
     *
     * @return a Future that, when redeemed, will have stored the user in the underlying database
     */
    CompletionStage<Void> create(DSLContext create, CustomerResource customerResource);

}
