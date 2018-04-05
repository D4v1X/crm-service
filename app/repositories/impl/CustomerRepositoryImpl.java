package repositories.impl;

import models.CustomerResource;
import org.jooq.DSLContext;
import repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

import static jooq.objects.Tables.CUSTOMER;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public void create(DSLContext create, CustomerResource customerResource) {
        create.insertInto(CUSTOMER)
                .columns(CUSTOMER.NAME
                        , CUSTOMER.SURNAME
                        , CUSTOMER.PHOTO)
                .values(customerResource.getName()
                        , customerResource.getSurname()
                        , customerResource.getPhoto())
                .execute();
    }

    @Override
    public List<CustomerResource> findAll(DSLContext create) {
        return create
                .select(CUSTOMER.ID
                        , CUSTOMER.NAME
                        , CUSTOMER.SURNAME
                        , CUSTOMER.PHOTO)
                .from(CUSTOMER)
                .fetchInto(CustomerResource.class);
    }

    @Override
    public Optional<CustomerResource> findById(DSLContext create, Integer customerId) {
        return create
                .select(CUSTOMER.ID
                        , CUSTOMER.NAME
                        , CUSTOMER.SURNAME
                        , CUSTOMER.PHOTO)
                .from(CUSTOMER)
                .where(CUSTOMER.ID.eq(customerId))
                .fetchOptionalInto(CustomerResource.class);
    }
    }

}
