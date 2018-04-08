package utils;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class Constants {

    private Constants() {
        throw new IllegalArgumentException("Utility class");
    }

    public static final String USER_UUID_HEADER = "X-USER-UUID";
    public static final String USER_TOKEN_HEADER = "X-USER-TOKEN";

    public static final String PASSWORD_ENCRYPTION_ALGORITHM = "SHA-256";
    public static final String PASSWORD_CHARSET = "UTF-8";

    public static final String USER_WHO_EXECUTED_THE_ACTION = "userSessionData";

    public static final ZoneId DEFAULT_TIMEZONE = ZoneId.of("UTC");

    public static final long TOKEN_EXPIRATION_TIME = 12L;
    public static final ChronoUnit TOKEN_EXPIRATION_UNIT = ChronoUnit.HOURS;

}
