package services;

import enums.Role;
import exceptions.ValidationException;
import models.customers.CustomerSummary;
import models.customers.NewCustomerRequest;
import models.customers.UpdateCustomerRequest;
import models.users.NewUserRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jooq.DSLContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repositories.CustomerRepository;
import repositories.UserRepository;
import repositories.impl.CustomerRepositoryImpl;
import repositories.impl.UserRepositoryImpl;
import services.impl.CustomerServiceImpl;
import services.impl.UserServiceImpl;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomerServiceTest extends WithApplicationAndH2 {

    private UserService userService;
    private UserRepository userRepository;
    private CustomerService customerService;
    private CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        startDatabase();
        startPlay();

        userService = app.injector().instanceOf(UserServiceImpl.class);
        userRepository = app.injector().instanceOf(UserRepositoryImpl.class);
        customerService = app.injector().instanceOf(CustomerServiceImpl.class);
        customerRepository = app.injector().instanceOf(CustomerRepositoryImpl.class);
    }

    @After
    public void tearDown() throws Exception {
        stopDatabase();
        stopPlay();
    }


    @Test
    public void testWhenCreatingAnCustomer() {
        //given
        DSLContext create = acquireJooqDslcontext();
        NewUserRequest newUserRequest = new NewUserRequest(
                "user"
                , "user@gmail.com"
                , "user"
                , Role.USER
        );
        userService.create(create, newUserRequest).toCompletableFuture().join();

        NewCustomerRequest newCustomerRequest = new NewCustomerRequest(
                "Customer Name"
                , "Customer Surname"
                , "data:image/png;base64,iVBORw........kAAAAASUVORK5CYII="
        );
        //when
        customerService.create(create, newCustomerRequest, 1).toCompletableFuture().join();

        //then
        assertTrue(!customerService.findAll(create).toCompletableFuture().join().isEmpty());
        assertEquals(1, customerService.findAll(create).toCompletableFuture().join().size());
    }

    @Test(expected = ValidationException.class)
    public void testWhenCreatingAnCustomerWithoutName() throws Throwable {
        //given
        DSLContext create = acquireJooqDslcontext();
        NewUserRequest newUserRequest = new NewUserRequest(
                "user"
                , "user@gmail.com"
                , "user"
                , Role.USER
        );
        userService.create(create, newUserRequest).toCompletableFuture().join();

        NewCustomerRequest newCustomerRequest = new NewCustomerRequest(
                null
                , "Customer surname"
                , "data:image/png;base64,iVBORw........kAAAAASUVORK5CYII="
        );
        //when
        try {
            customerService.create(create, newCustomerRequest, 1).toCompletableFuture().join();
        } catch (Exception e) {
            throw ExceptionUtils.getRootCause(e);
        }

        //then -> ValidationException
    }

    @Test(expected = ValidationException.class)
    public void testWhenCreatingAnUserWithoutPassword() throws Throwable {
        //given
        DSLContext create = acquireJooqDslcontext();
        NewUserRequest newUserRequest = new NewUserRequest(
                "user"
                , "user@gmail.com"
                , "user"
                , Role.USER
        );
        userService.create(create, newUserRequest).toCompletableFuture().join();

        NewCustomerRequest newCustomerRequest = new NewCustomerRequest(
                "Customer name"
                , null
                , "data:image/png;base64,iVBORw........kAAAAASUVORK5CYII="
        );
        //when
        try {
            customerService.create(create, newCustomerRequest, 1).toCompletableFuture().join();
        } catch (Exception e) {
            throw ExceptionUtils.getRootCause(e);
        }

        //then -> ValidationException
    }

    @Test
    public void testByDefaultNoCustomersAvailable() {
        //given
        DSLContext create = acquireJooqDslcontext();

        //when
        List<CustomerSummary> customerSummaries = customerService.findAll(create).toCompletableFuture().join();

        //then
        assertTrue(customerSummaries.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenUpdateAnCustomerUnAvailable() throws Throwable {
        //given
        DSLContext create = acquireJooqDslcontext();
        UpdateCustomerRequest updateUserRequest = new UpdateCustomerRequest(
                "CN"
                , "CS"
                , "data:image/png;base64,iVBORw........kAAAAASUVORK5CYII="
        );
        //when
        try {
            customerService.update(create, 1, updateUserRequest, 1).toCompletableFuture().join();
        } catch (Exception e) {
            throw ExceptionUtils.getRootCause(e);
        }
        //then -> IllegalArgumentException
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenDeleteAnCustomerUnAvailable() throws Throwable {
        //given
        DSLContext create = acquireJooqDslcontext();

        //when
        try {
            customerService.delete(create, 1).toCompletableFuture().join();
        } catch (Exception e) {
            throw ExceptionUtils.getRootCause(e);
        }

        //then -> IllegalArgumentException
    }

}