import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static void connect() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("databaseData.txt"));
            String url = br.readLine();
            String dbUser = br.readLine();
            String dbPassword = br.readLine();
            Connection cn = DriverManager.getConnection(url, dbUser, dbPassword);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection Error");
            //JOptionPane.showMessageDialog(null, "Connection error");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        connect();
    }

}
