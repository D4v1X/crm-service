package utils;

import com.google.inject.AbstractModule;
import repositories.CustomerRepository;
import repositories.UserRepository;
import repositories.impl.CustomerRepositoryImpl;
import repositories.impl.UserRepositoryImpl;
import services.AuthenticationService;
import services.CustomerService;
import services.UserService;
import services.impl.AuthenticationServiceImpl;
import services.impl.CustomerServiceImpl;
import services.impl.UserServiceImpl;

public class GuiceDependencyModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CustomerService.class).to(CustomerServiceImpl.class);
        bind(CustomerRepository.class).to(CustomerRepositoryImpl.class);

        bind(UserService.class).to(UserServiceImpl.class);
        bind(UserRepository.class).to(UserRepositoryImpl.class);

        bind(AuthenticationService.class).to(AuthenticationServiceImpl.class);
    }

}
