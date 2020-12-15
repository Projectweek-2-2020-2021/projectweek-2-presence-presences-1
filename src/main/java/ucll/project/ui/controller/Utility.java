package ucll.project.ui.controller;

import ucll.project.domain.model.Rol;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utility {
    public static void checkRoles(HttpServletRequest request, Rol[] roles) {
        Rol actualRole = null;
        String rol = (String) request.getSession().getAttribute("rol");
        for (Rol role : roles) {
            if (rol.equals(role.getStringValue())) {
                actualRole = role;
                break;
            }
        }
        if (actualRole == null) throw new NotAuthorizedException();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(wachtwoordEncryptie("a"));
    }

    public static String wachtwoordEncryptie(String wachtwoord) throws NoSuchAlgorithmException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-512");
        crypt.reset();

        byte[] passwordBytes = wachtwoord.getBytes(StandardCharsets.UTF_8);
        crypt.update(passwordBytes);

        byte[] digest = crypt.digest();
        return new BigInteger(1, digest).toString(16);
    }
}
