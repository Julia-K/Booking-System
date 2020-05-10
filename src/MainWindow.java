import javax.swing.*;
import java.awt.*;

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

    public MainWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(new Dimension(1100,700));
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
        JLabel name = new JLabel("Welcome to Airline Booking System");
        JLabel memberLogin = new JLabel("MEMBER LOGIN");

        JTextField email = new JTextField();
        email.setColumns(10);
        email.setBounds(450,160,280,35);
        contentPane.add(email);


        JPasswordField passwordField = new JPasswordField();
        passwordField.setColumns(10);
        passwordField.setBounds(450, 220,280,35);
        contentPane.add(passwordField);
        JPasswordField reapeatField = new JPasswordField();
        reapeatField.setColumns(10);
        reapeatField.setBounds(450, 280,280,35);
        contentPane.add(reapeatField);

        JButton singUpButton = new JButton("SingUp");

        singUpButton.setForeground(Color.WHITE);
        //singUpButton.setBackground(new Color());

    }

    public JFrame getMainWindow() {
        return mainWindow;
    }
}
