package services;

import enums.Role;
import exceptions.AuthenticationException;
import exceptions.ValidationException;
import models.users.NewUserRequest;
import models.users.UserLogin;
import models.users.UserSessionData;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jooq.DSLContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import services.impl.AuthenticationServiceImpl;
import services.impl.UserServiceImpl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AuthenticationServiceTest extends WithApplicationAndH2 {

    private UserService userService;
    private AuthenticationService authenticationService;

    @Before
    public void setUp() throws Exception {
        startDatabase();
        startPlay();

        userService = app.injector().instanceOf(UserServiceImpl.class);
        authenticationService = app.injector().instanceOf(AuthenticationServiceImpl.class);
    }

    @After
    public void tearDown() throws Exception {
        stopDatabase();
        stopPlay();
    }

    @Test
    public void testWhenLoginByDefault() {
        //given
        DSLContext create = acquireJooqDslcontext();
        NewUserRequest newUserRequest = new NewUserRequest(
                "user"
                , "user@gmail.com"
                , "user"
                , Role.USER
        );
        userService.create(create, newUserRequest).toCompletableFuture().join();

        UserLogin userLogin = new UserLogin("user@gmail.com", "user");
        //when
        UserSessionData userSessionData = authenticationService.login(create, userLogin).toCompletableFuture().join();

        //then
        assertNotNull(userSessionData);
        assertNotNull(userSessionData.getUuid());
        assertNotNull(userSessionData.getToken());
    }

    @Test(expected = ValidationException.class)
    public void testWhenLoginWithoutEmail() throws Throwable {
        //given
        DSLContext create = acquireJooqDslcontext();
        NewUserRequest newUserRequest = new NewUserRequest(
                "user"
                , "user@gmail.com"
                , "user"
                , Role.USER
        );
        userService.create(create, newUserRequest).toCompletableFuture().join();

        UserLogin userLogin = new UserLogin(null, "user");

        //when
        try {
            UserSessionData userSessionData = authenticationService.login(create, userLogin).toCompletableFuture().join();
        } catch (Exception e) {
            throw ExceptionUtils.getRootCause(e);
        }

        //then -> ValidationException
    }

    @Test(expected = ValidationException.class)
    public void testWhenLoginWithoutPassword() throws Throwable {
        //given
        DSLContext create = acquireJooqDslcontext();
        NewUserRequest newUserRequest = new NewUserRequest(
                "user"
                , "user@gmail.com"
                , "user"
                , Role.USER
        );
        userService.create(create, newUserRequest).toCompletableFuture().join();

        UserLogin userLogin = new UserLogin("user@gmail.com", null);

        //when
        try {
            UserSessionData userSessionData = authenticationService.login(create, userLogin).toCompletableFuture().join();
        } catch (Exception e) {
            throw ExceptionUtils.getRootCause(e);
        }

        //then -> ValidationException
    }

    @Test(expected = AuthenticationException.class)
    public void testWhenLoginWithoutFakeEmail() throws Throwable {
        //given
        DSLContext create = acquireJooqDslcontext();
        NewUserRequest newUserRequest = new NewUserRequest(
                "user"
                , "user@gmail.com"
                , "user"
                , Role.USER
        );
        userService.create(create, newUserRequest).toCompletableFuture().join();

        UserLogin userLogin = new UserLogin("fake@gmail.com", "user");

        //when
        try {
            UserSessionData userSessionData = authenticationService.login(create, userLogin).toCompletableFuture().join();
        } catch (Exception e) {
            throw ExceptionUtils.getRootCause(e);
        }

        //then -> AuthenticationException
    }

    @Test(expected = AuthenticationException.class)
    public void testWhenLoginWithoutFakePassword() throws Throwable {
        //given
        DSLContext create = acquireJooqDslcontext();
        NewUserRequest newUserRequest = new NewUserRequest(
                "user"
                , "user@gmail.com"
                , "user"
                , Role.USER
        );
        userService.create(create, newUserRequest).toCompletableFuture().join();

        UserLogin userLogin = new UserLogin("user@gmail.com", "fake");

        //when
        try {
            UserSessionData userSessionData = authenticationService.login(create, userLogin).toCompletableFuture().join();
        } catch (Exception e) {
            throw ExceptionUtils.getRootCause(e);
        }

        //then -> AuthenticationException
    }

}