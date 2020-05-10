import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        Connection cn = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("databaseData.txt"));
            String url = br.readLine();
            String dbUser = br.readLine();
            String dbPassword = br.readLine();
            cn = DriverManager.getConnection(url, dbUser, dbPassword);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            if(cn != null) System.out.println("Welcome to Airline Booking System");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection Error");
            //JOptionPane.showMessageDialog(null, "Connection error");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cn;
    }
}