package utils;

import java.math.BigInteger;
import java.security.SecureRandom;

public class TokenUtil {

    private static int MAXIMUM_BIT_LENGTH = 130;
    private static int RADIX = 32;


    public static String generateRandomToken() {
        return new BigInteger(MAXIMUM_BIT_LENGTH, new SecureRandom()).toString(RADIX);
    }

}
