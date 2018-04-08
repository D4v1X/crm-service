package utils;

import exceptions.EncryptionException;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.util.UUID;

public class EncryptionUtil {

    public static String generateChecksum(String s) throws EncryptionException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(Constants.PASSWORD_ENCRYPTION_ALGORITHM);
            messageDigest.update(s.getBytes(Constants.PASSWORD_CHARSET));
            return new String(Hex.encodeHex(messageDigest.digest()));
        } catch (Exception e) {
            throw new EncryptionException("Error trying to generate the checksum for the password", e);
        }
    }

    public static String generateSecret() {
        return UUID.randomUUID().toString();
    }

    public static String encryptPassword(String secret, String password) {
        return EncryptionUtil.generateChecksum(secret + password);
    }
}
