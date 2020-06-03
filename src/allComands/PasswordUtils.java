package allComands;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {
    public static String hashing(String password) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(password.getBytes());
        return bytesToHex(messageDigest.digest());
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuffer codedPassword = new StringBuffer();
        for (byte byt : bytes) codedPassword.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return codedPassword.toString();
    }
}
