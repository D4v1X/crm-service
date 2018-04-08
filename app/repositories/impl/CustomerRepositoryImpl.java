package repositories.impl;

import exceptions.DatabaseException;
import jooq.objects.tables.pojos.Customer;
import jooq.objects.tables.User;
import jooq.objects.tables.records.CustomerRecord;
import models.customers.CustomerSummary;
import models.customers.CustomerWithDetails;
import org.jooq.DSLContext;
import repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

import static jooq.objects.Tables.CUSTOMER;
import static jooq.objects.Tables.USER;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public void create(DSLContext create, Customer customer) {

        try {
            CustomerRecord customerRecord = create.newRecord(CUSTOMER, customer);
            create.insertInto(CUSTOMER)
                    .set(customerRecord)
                    .execute();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }

    }

    @Override
    public List<CustomerSummary> findAll(DSLContext create) {
        try {
            return create
                    .select(CUSTOMER.ID, CUSTOMER.NAME)
                    .from(CUSTOMER)
                    .fetchInto(CustomerSummary.class);
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Optional<Customer> findById(DSLContext create, Integer customerId) {
        try {
            return create.selectFrom(CUSTOMER)
                    .where(CUSTOMER.ID.eq(customerId))
                    .fetchOptionalInto(Customer.class);
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Optional<CustomerWithDetails> findCustomerWithDetailsById(DSLContext create, Integer customerId) {
        try {
            jooq.objects.tables.Customer customer = CUSTOMER.as("customer");
            User createByUser = USER.as("createByUser");
            User modifiedByUser = USER.as("modifiedByUser");

            return create
                    .select(customer.ID
                            , customer.NAME
                            , customer.SURNAME
                            , customer.PHOTO
                            , createByUser.NAME.as("createdByUser")
                            , customer.CREATED
                            , modifiedByUser.NAME.as("modifiedByUser")
                            , customer.MODIFIED)
                    .from(customer)
                    .leftJoin(createByUser).on(customer.CREATED_BY_USER.eq(createByUser.ID))
                    .leftJoin(modifiedByUser).on(customer.MODIFIED_BY_USER.eq(modifiedByUser.ID))
                    .where(customer.ID.eq(customerId))
                    .fetchOptionalInto(CustomerWithDetails.class);
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(DSLContext create, Customer customer) {
        try {
            CustomerRecord customerRecord = create.newRecord(CUSTOMER, customer);
            create.executeUpdate(customerRecord);
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(DSLContext create, Integer customerId) {
        try {
            create.delete(CUSTOMER)
                    .where(CUSTOMER.ID.eq(customerId))
                    .execute();
        } catch (Exception e) {
            throw new DatabaseException(e);
        }
    }

}
