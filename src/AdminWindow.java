import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AdminWindow extends JFrame {
    private static AdminWindow adminWindow;
    private JPanel contentPane;

    public AdminWindow() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Airline Ticket Booking System");
        setBackground(Color.WHITE);
        setBounds(100, 100, 1100, 700);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }

    public static void main(String[] args) {
        //setUndecorated(true);
        //mainWindow.setVisible(true);
        EventQueue.invokeLater((() -> {
            try {
                adminWindow = new AdminWindow();
                adminWindow.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }

}
