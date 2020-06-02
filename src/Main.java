import allComands.PasswordUtils;

import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        char[] password = "lubieplackihaha".toCharArray();
        String codedPass = PasswordUtils.hash256(password);
        if (codedPass.equals(PasswordUtils.hash256(password))) {
            System.out.println("BEKA");
        }

    }
}
