package services.impl;

import exceptions.AuthenticationException;
import jooq.objects.tables.pojos.User;
import models.users.UserLogin;
import models.users.UserSessionData;
import org.jooq.DSLContext;
import play.Logger;
import repositories.UserRepository;
import services.AuthenticationService;
import utils.Constants;
import utils.EncryptionUtil;
import utils.TokenUtil;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class AuthenticationServiceImpl implements AuthenticationService {

    private UserRepository userRepository;

    @Inject
    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CompletionStage<UserSessionData> login(DSLContext create, UserLogin userLogin) {
        return CompletableFuture.supplyAsync(() -> {

            Optional<User> optionalUser = userRepository.findByEmail(create, userLogin.getEmail());
            User user = optionalUser.orElseThrow(() -> {
                Logger.error("There is no user with the email [" + userLogin.getEmail() +"].");
                return new AuthenticationException("The credentials provided are not correct.");
            });

            String encryptPassword = EncryptionUtil.encryptPassword(user.getSecret(), userLogin.getPassword());
            if (!encryptPassword.equals(user.getPassword())) {
                Logger.error("The user [" + user.getEmail() + "] tries to log-in with the wrong password.");
                throw new AuthenticationException("The credentials provided are not correct.");
            }

            String token = TokenUtil.generateRandomToken();
            LocalDateTime tokenExpiration = LocalDateTime.now(Constants.DEFAULT_TIMEZONE).plus(
                    Constants.TOKEN_EXPIRATION_TIME,
                    Constants.TOKEN_EXPIRATION_UNIT
            );

            userRepository.updateUserToken(create, user.getId(), token, tokenExpiration);

            return new UserSessionData(user.getUuid(), token);

        });
    }

    @Override
    public CompletionStage<Void> logout(DSLContext create, Integer userId) {
        return CompletableFuture.runAsync(() -> userRepository.deleteUserTokenByUserId(create, userId));
    }

}
