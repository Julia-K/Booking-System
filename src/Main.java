import allComands.DBConnection;
import allComands.PasswordUtils;
import allComands.Requests;
import tableFrames.LoginFrame;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {
        new LoginFrame();
    }
}
