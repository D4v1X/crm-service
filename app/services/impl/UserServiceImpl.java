package services.impl;

import com.google.inject.Inject;
import enums.Role;
import jooq.objects.tables.pojos.User;
import models.users.NewUserRequest;
import models.users.UpdateUserRequest;
import models.users.UserSummary;
import org.jooq.DSLContext;
import play.Logger;
import repositories.UserRepository;
import services.UserService;
import utils.EncryptionUtil;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Inject
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletionStage<Void> create(DSLContext create, NewUserRequest newUserRequest) {
        return CompletableFuture.runAsync(() -> userRepository.create(create, generateUser(newUserRequest)));
    }


    @Override
    public CompletionStage<List<UserSummary>> findAll(DSLContext create) {
        return CompletableFuture.supplyAsync(() -> userRepository.findAll(create));
    }

    @Override
    public CompletionStage<Void> update(DSLContext create, Integer userId, UpdateUserRequest updateUserRequest) {
        return CompletableFuture.runAsync(() -> {

            User user = userRepository.findById(create, userId)
                    .orElseThrow(() -> {
                        Logger.error("The user [id: " + userId + " ] that is intended to update does not exist.");
                        return new IllegalArgumentException("The user that is intended to update does not exist.");
                    });

            userRepository.update(create, mergeUserInformation(user, updateUserRequest));
        });
    }

    @Override
    public CompletionStage<Void> delete(DSLContext create, Integer userId) {
        return CompletableFuture.runAsync(() -> userRepository.delete(create, userId));
    }

    @Override
    public CompletionStage<Void> changeAdminStatus(DSLContext create, Integer userId) {
        return CompletableFuture.runAsync(() -> {

            User user = userRepository.findById(create, userId)
                    .orElseThrow(() -> {
                        Logger.error("The user [id: " + userId + "] that is intended to change admin status does not exist.");
                        return new IllegalArgumentException("The user that is intended to change admin status does not exist.");
                    });

            if (user.getRole().equals(Role.USER))
                userRepository.updateRole(create, userId, Role.ADMIN);
            else
                userRepository.updateRole(create, userId, Role.USER);
        });
    }

    private User generateUser(NewUserRequest userResource) {
        User newUser = new User();

        newUser.setUuid(UUID.randomUUID());
        newUser.setName(userResource.getName());
        newUser.setEmail(userResource.getEmail());
        newUser.setRole(userResource.getRole());

        String secret = EncryptionUtil.generateSecret();
        newUser.setSecret(secret);
        newUser.setPassword(EncryptionUtil.encryptPassword(secret, userResource.getPassword()));

        return newUser;
    }

    private User mergeUserInformation(User user, UpdateUserRequest updateUserRequest) {
        if (updateUserRequest.getName() != null)
            user.setName(updateUserRequest.getName());
        if (updateUserRequest.getEmail() != null)
            user.setEmail(updateUserRequest.getEmail());
        if (updateUserRequest.getRole() != null)
            user.setRole(updateUserRequest.getRole());
        return user;
    }

}
