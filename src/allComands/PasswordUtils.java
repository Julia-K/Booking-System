package allComands;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {
    public static String hash256(char[] password) {
        String passwordS = password.toString();
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(passwordS.getBytes());
        return bytesToHex(messageDigest.digest());
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuffer codedPassword = new StringBuffer();
        for (byte byt : bytes) codedPassword.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return codedPassword.toString();
    }
}
