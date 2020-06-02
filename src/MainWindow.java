import allComands.DBConnection;
import allComands.Requests;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MainWindow extends JFrame {
    static MainWindow mainWindow;
    private JPanel contentPane;

    public static void main(String[] args) {
        //setUndecorated(true);
        //mainWindow.setVisible(true);
        EventQueue.invokeLater((() -> {
            try {
                mainWindow = new MainWindow();
                mainWindow.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }

    public MainWindow() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Airline Ticket Booking System");
        setBackground(Color.WHITE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        //this.setLocationRelativeTo(null);

        JPanel jPanel = new JPanel();
        jPanel.setBackground(new Color(69,123,157));
        jPanel.setBounds(0, 0, 370, 600);
        contentPane.add(jPanel);
        contentPane.setLayout(null);
        JLabel memberLogin = new JLabel("MEMBER LOGIN");

        JTextField email = new JTextField();
        email.setColumns(10);
        email.setBounds(450,160,280,35);
        contentPane.add(email);

        //admin
        JPasswordField passwordField = new JPasswordField();
        passwordField.setColumns(10);
        passwordField.setBounds(450, 220,280,35);
        contentPane.add(passwordField);

        JButton logInButton = new JButton("LogIn");
        logInButton.setBounds(395, 363, 283, 36);
        contentPane.add(logInButton);
        logInButton.addActionListener(e -> {
            try {
                if (Requests.isAdmin(DBConnection.getConnection(), email.getText(), passwordField.getPassword())) {
                    System.out.println("LogIn");
                    this.dispose();
                    ManageWindow.main(new String[]{});
                } else {
                    System.out.println(new String(passwordField.getPassword()));
                    System.out.println("Username or Password Error");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        logInButton.setForeground(Color.black);
        //singUpButton.setBackground(new Color());

    }
}
