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
    }

}
