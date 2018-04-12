package services.impl;

import com.google.inject.Inject;
import jooq.objects.tables.pojos.Customer;
import models.customers.CustomerSummary;
import models.customers.CustomerWithDetails;
import models.customers.NewCustomerRequest;
import models.customers.UpdateCustomerRequest;
import org.jooq.DSLContext;
import play.Logger;
import repositories.CustomerRepository;
import services.CustomerService;
import utils.Validation;
import utils.Validator;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Inject
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CompletionStage<Void> create(DSLContext create, NewCustomerRequest newCustomerRequest, Integer userWhoExecutedId) {
        return CompletableFuture.runAsync(() -> {

            Validator.apply(
                    Validation.with(
                            Optional.ofNullable(newCustomerRequest.getName()).isPresent(),
                            "Name is required fields" ),
                    Validation.with(
                            Optional.ofNullable(newCustomerRequest.getSurname()).isPresent(),
                            "Surname is required fields" )
            ).validate();

            customerRepository.create(create, generateUser(newCustomerRequest, userWhoExecutedId));
        });
    }

    @Override
    public CompletionStage<List<CustomerSummary>> findAll(DSLContext create) {
        return CompletableFuture.supplyAsync(() -> customerRepository.findAll(create));
    }

    @Override
    public CompletionStage<Optional<CustomerWithDetails>> findCustomerWithDetailsById(DSLContext create, Integer customerId) {
        return CompletableFuture.supplyAsync(() -> customerRepository.findCustomerWithDetailsById(create, customerId));
    }

    @Override
    public CompletionStage<Void> update(DSLContext create, Integer customerId, UpdateCustomerRequest updateCustomerRequest, Integer userWhoExecutedId) {
        return CompletableFuture.runAsync(() -> {

            Customer customer = customerRepository.findById(create, customerId)
                    .orElseThrow(() -> {
                        Logger.error("the user [id: " + userWhoExecutedId + " ] tried to update a customer [id: " + customerId + " ] that does not exist." );
                        return new IllegalArgumentException("The customer that is intended to update does not exist." );
                    });

            customerRepository.update(create, mergeCustomerInformation(customer, updateCustomerRequest, userWhoExecutedId));
        });
    }

    @Override
    public CompletionStage<Void> delete(DSLContext create, Integer customerId) {
        return CompletableFuture.runAsync(() -> {

            customerRepository.findById(create, customerId)
                    .orElseThrow(() -> {
                        Logger.error("the user tried to delete a customer [id: " + customerId + " ] that does not exist." );
                        return new IllegalArgumentException("The customer that is intended to delete does not exist." );
                    });

            customerRepository.delete(create, customerId);
        });
    }

    private Customer generateUser(NewCustomerRequest newCustomerRequest, Integer userWhoExecutedId) {
        Customer newCustomer = new Customer();

        newCustomer.setName(newCustomerRequest.getName());
        newCustomer.setSurname(newCustomerRequest.getSurname());
        Optional.ofNullable(newCustomerRequest.getPhoto()).ifPresent(newCustomer::setPhoto);
        newCustomer.setCreatedByUser(userWhoExecutedId);

        return newCustomer;
    }

    private Customer mergeCustomerInformation(Customer customer, UpdateCustomerRequest updateCustomerRequest, Integer userWhoExecutedId) {

        Optional.ofNullable(updateCustomerRequest.getName()).ifPresent(customer::setName);
        Optional.ofNullable(updateCustomerRequest.getSurname()).ifPresent(customer::setSurname);
        Optional.ofNullable(updateCustomerRequest.getPhoto()).ifPresent(customer::setPhoto);

        customer.setModifiedByUser(userWhoExecutedId);
        return customer;
    }

}
