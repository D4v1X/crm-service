package utils;

import com.google.inject.AbstractModule;
import repositories.CustomerRepository;
import repositories.impl.CustomerRepositoryImpl;
import services.CustomerService;
import services.impl.CustomerServiceImpl;

public class GuiceDependencyModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CustomerService.class).to(CustomerServiceImpl.class);
        bind(CustomerRepository.class).to(CustomerRepositoryImpl.class);
    }

}
