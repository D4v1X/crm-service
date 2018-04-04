package services.impl;

import models.CustomerResource;
import org.jooq.DSLContext;
import repositories.CustomerRepository;
import services.CustomerService;

import java.util.concurrent.CompletionStage;

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CompletionStage<Void> create(DSLContext create, CustomerResource customerResource) {
        return customerRepository.create(create, customerResource);
    }

}