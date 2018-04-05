package services.impl;

import com.google.inject.Inject;
import models.CustomerResource;
import org.jooq.DSLContext;
import repositories.CustomerRepository;
import services.CustomerService;

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
    public CompletionStage<Void> create(DSLContext create, CustomerResource customerResource) {
        return CompletableFuture.runAsync(() -> customerRepository.create(create, customerResource));
    }

    @Override
    public CompletionStage<List<CustomerResource>> findAll(DSLContext create) {
        return CompletableFuture.supplyAsync(() -> customerRepository.findAll(create));
    }

    }

}
