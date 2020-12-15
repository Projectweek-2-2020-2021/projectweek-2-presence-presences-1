package ucll.project.ui.controller;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Dit is voor een latere story
public class PasswordEncryptie {
    public static String encryption(String password) throws NoSuchAlgorithmException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-512");
        crypt.reset();

        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        crypt.update(passwordBytes);

        byte[] digest = crypt.digest();
        return new BigInteger(1, digest).toString(16);
    }
}
