package services;

import enums.Role;
import exceptions.ValidationException;
import jooq.objects.tables.pojos.User;
import models.users.NewUserRequest;
import models.users.UpdateUserRequest;
import models.users.UserSummary;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jooq.DSLContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repositories.UserRepository;
import repositories.impl.UserRepositoryImpl;
import services.impl.UserServiceImpl;
import utils.EncryptionUtil;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest extends WithApplicationAndH2 {

    private UserService userService;
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        startDatabase();
        startPlay();

        userService = app.injector().instanceOf(UserServiceImpl.class);
        userRepository = app.injector().instanceOf(UserRepositoryImpl.class);
    }

    @After
    public void tearDown() {
        stopDatabase();
        stopPlay();
    }

    @Test
    public void testWhenCreatingAnUser() {
        //given
        DSLContext create = acquireJooqDslcontext();
        NewUserRequest newUserRequest = new NewUserRequest(
                "user"
                , "user@gmail.com"
                , "user"
                , Role.USER
        );
        //when
        userService.create(create, newUserRequest).toCompletableFuture().join();

        //then
        assertTrue(!userService.findAll(create).toCompletableFuture().join().isEmpty());
        assertEquals(1, userService.findAll(create).toCompletableFuture().join().size());
    }

    @Test
    public void testWhenCreatingAnUserExitsOnDatabase() {
        //given
        DSLContext create = acquireJooqDslcontext();
        NewUserRequest newUserRequest = new NewUserRequest(
                "user"
                , "user@gmail.com"
                , "user"
        );
        //when
        userService.create(create, newUserRequest).toCompletableFuture().join();

        //then
        User user = userRepository.findByEmail(create, "user@gmail.com" ).get();
        assertNotNull(user.getId());
        assertEquals("user", user.getName());
        assertEquals("user@gmail.com", user.getEmail());
        assertEquals(user.getPassword(), EncryptionUtil.encryptPassword(user.getSecret(), "user" ));
    }

    @Test
    public void testWhenCreatingAnUserWithoutRole() {
        //given
        DSLContext create = acquireJooqDslcontext();
        NewUserRequest newUserRequest = new NewUserRequest(
                "user"
                , "user@gmail.com"
                , "user"
        );
        //when
        userService.create(create, newUserRequest).toCompletableFuture().join();

        //then
        userRepository.findByEmail(create, "user@gmail.com" ).map(User::getRole);
        assertEquals(Role.USER, userRepository.findByEmail(create, "user@gmail.com" ).map(User::getRole).get());
    }

    @Test(expected = ValidationException.class)
    public void testWhenCreatingAnUserWithoutEmail() throws Throwable {
        //given
        DSLContext create = acquireJooqDslcontext();
        NewUserRequest newUserRequest = new NewUserRequest(
                "user"
                , null
                , "user"
        );
        //when
        try {
            userService.create(create, newUserRequest).toCompletableFuture().join();
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
                , null
        );

        //when
        try {
            userService.create(create, newUserRequest).toCompletableFuture().join();
        } catch (Exception e) {
            throw ExceptionUtils.getRootCause(e);
        }

        //then -> ValidationException
    }

    @Test
    public void testByDefaultNoUsersAvailable() {
        //given
        DSLContext create = acquireJooqDslcontext();

        //when
        List<UserSummary> rentSummaryList = userService.findAll(create).toCompletableFuture().join();

        //then
        assertTrue(rentSummaryList.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenUpdateAnUserUnAvailable() throws Throwable {
        //given
        DSLContext create = acquireJooqDslcontext();
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(
                "user"
                , "user@gmail.com"
                , Role.USER
        );
        //when
        try {
            userService.update(create, 1, updateUserRequest).toCompletableFuture().join();
        } catch (Exception e) {
            throw ExceptionUtils.getRootCause(e);
        }
        //then -> IllegalArgumentException
    }

    @Test
    public void testWhenUpdateAnUserAvailable() {
        //given
        DSLContext create = acquireJooqDslcontext();
        NewUserRequest newUserRequest = new NewUserRequest(
                "user"
                , "user@gmail.com"
                , "user"
                , Role.USER
        );
        UpdateUserRequest updateUserRequest = new UpdateUserRequest(
                "admin"
                , "admin@gmail.com"
                , Role.ADMIN
        );

        //when
        userService.create(create, newUserRequest).toCompletableFuture().join();
        userService.update(create, 1, updateUserRequest).toCompletableFuture().join();

        //then
        User user = userRepository.findById(create, 1).get();
        assertEquals("admin", user.getName());
        assertEquals("admin@gmail.com", user.getEmail());
        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    public void testWhenDeleteAnUserAvailable() {
        //given
        DSLContext create = acquireJooqDslcontext();
        NewUserRequest newUserRequest = new NewUserRequest(
                "user"
                , "user@gmail.com"
                , "user"
                , Role.USER
        );
        //when
        userService.create(create, newUserRequest).toCompletableFuture().join();
        userService.delete(create, 1).toCompletableFuture().join();

        //then -> void
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWhenDeleteAnUserUnAvailable() throws Throwable {
        //given
        DSLContext create = acquireJooqDslcontext();

        //when
        try {
            userService.delete(create, 1).toCompletableFuture().join();
        } catch (Exception e) {
            throw ExceptionUtils.getRootCause(e);
        }

        //then -> IllegalArgumentException
    }


    @Test(expected = IllegalArgumentException.class)
    public void testWhenChangeAdminStatusAnUserUnAvailable() throws Throwable {
        //given
        DSLContext create = acquireJooqDslcontext();

        //when
        try {
            userService.changeAdminStatus(create, 1).toCompletableFuture().join();
        } catch (Exception e) {
            throw ExceptionUtils.getRootCause(e);
        }

        //then -> IllegalArgumentException
    }

    @Test
    public void testWhenChangeAdminStatusAnUserAvailable() throws Throwable {
        //given
        DSLContext create = acquireJooqDslcontext();
        NewUserRequest newUserRequest = new NewUserRequest(
                "user"
                , "user@gmail.com"
                , "user"
                , Role.USER
        );
        //when
        userService.create(create, newUserRequest).toCompletableFuture().join();
        userService.changeAdminStatus(create, 1).toCompletableFuture().join();

        //then
        assertEquals(Role.ADMIN, userRepository.findById(create, 1).get().getRole());
    }
}