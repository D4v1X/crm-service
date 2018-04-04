package repositories.impl;

import models.CustomerResource;
import org.jooq.DSLContext;
import repositories.CustomerRepository;

import java.util.concurrent.CompletionStage;

import static jooq.objects.Tables.CUSTOMER;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public CompletionStage<Void> create(DSLContext create, CustomerResource customerResource) {
        return create
                .insertInto(CUSTOMER)
                .columns(CUSTOMER.NAME
                        , CUSTOMER.SURNAME
                        , CUSTOMER.PHOTO)
                .values(customerResource.getName()
                        , customerResource.getSurname()
                        , customerResource.getPhoto())
                .executeAsync()
                .thenRun(() -> {});
    }

}
