package main.java.frame;

import main.java.commands.Requests;
import main.java.utils.StringsFormatter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    private JPasswordField fillPassword;
    private JTextField fiillLoginRight;
    private JPasswordField fillPasswordRight;
    private JPasswordField fillPasswordRight2;
    private JTextField fillLogin;

    public LoginFrame() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel dialogPane = new JPanel();
        JPanel panel1 = new JPanel();
        JLabel loginL = new JLabel();
        fillPassword = new JPasswordField();
        fillLogin = new JTextField();
        JLabel loginLeft = new JLabel();
        JLabel passLeft = new JLabel();
        JButton loginButton = new JButton();
        JPanel panel2 = new JPanel();
        JLabel registerL = new JLabel();
        fiillLoginRight = new JTextField();
        fillPasswordRight = new JPasswordField();
        fillPasswordRight2 = new JPasswordField();
        JButton registerButton = new JButton();
        JLabel loginRight = new JLabel();
        JLabel passwordRight = new JLabel();
        JLabel confirmPassL = new JLabel();

        setResizable(false);
        setMinimumSize(new Dimension(820, 540));
        setBackground(new Color(235, 242, 250));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBackground(new Color(235, 242, 250));
            dialogPane.setMaximumSize(new Dimension(100, 100));
            dialogPane.setMinimumSize(new Dimension(100, 100));
            dialogPane.setPreferredSize(new Dimension(100, 100));
            dialogPane.setLayout(new BorderLayout());

            {
                panel1.setPreferredSize(new Dimension(330, 200));
                panel1.setBackground(new Color(5, 102, 141));
                panel1.setForeground(new Color(5, 102, 141));
                panel1.setMaximumSize(new Dimension(160, 200));
                panel1.setMinimumSize(new Dimension(160, 200));
                panel1.setLayout(null);

                loginL.setText("Login");
                loginL.setForeground(new Color(235, 242, 250));
                loginL.setFont(loginL.getFont().deriveFont(loginL.getFont().getStyle() | Font.BOLD, loginL.getFont().getSize() + 20f));
                panel1.add(loginL);
                loginL.setBounds(60, 35, 160, 70);

                fillPassword.setForeground(Color.black);
                fillPassword.setBackground(Color.white);
                panel1.add(fillPassword);
                StringsFormatter.setTextFieldLength(100, fillPassword);
                fillPassword.setBounds(55, 230, 210, 35);

                fillLogin.setForeground(Color.black);
                fillLogin.setBackground(Color.white);
                StringsFormatter.setTextFieldLength(30, fillLogin);
                StringsFormatter.setNoSpaces(fillLogin);
                panel1.add(fillLogin);
                fillLogin.setBounds(55, 150, 210, 35);

                loginLeft.setText("Login");
                loginLeft.setForeground(new Color(235, 242, 250));
                panel1.add(loginLeft);
                loginLeft.setBounds(60, 125, 65, 25);

                passLeft.setText("Password");
                passLeft.setForeground(new Color(235, 242, 250));
                panel1.add(passLeft);
                passLeft.setBounds(60, 205, 65, 25);

                loginButton.setText("Login");
                loginButton.setBackground(new Color(235, 242, 250));
                loginButton.setForeground(new Color(5, 102, 141));
                panel1.add(loginButton);
                loginButton.setBounds(190, 405, 98, 40);

                loginButton.addActionListener(e -> {
                    String haslo = new String(fillPassword.getPassword());
                    if (haslo.equals("") || fillLogin.getText().equals("")) {
                        JOptionPane.showMessageDialog(new Frame(), "Fields must be filled.");
                    } else {
                        try {
                            if (Requests.isAdmin(fillLogin.getText(), haslo)) {
                                dispose();
                                ManageWindow.main(new String[]{});
                            } else {
                                JOptionPane.showMessageDialog(new Frame(), "Incorrect login or password.");
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                });
            }

            dialogPane.add(panel1, BorderLayout.WEST);

            {
                panel2.setBackground(new Color(235, 242, 250));
                panel2.setLayout(null);

                registerL.setText("Register");
                registerL.setForeground(new Color(5, 102, 141));
                registerL.setFont(registerL.getFont().deriveFont(registerL.getFont().getStyle() | Font.BOLD, registerL.getFont().getSize() + 20f));
                panel2.add(registerL);
                registerL.setBounds(60, 35, 265, 70);

                fiillLoginRight.setBackground(Color.white);
                panel2.add(fiillLoginRight);
                StringsFormatter.setTextFieldLength(30, fiillLoginRight);
                StringsFormatter.setNoSpaces(fiillLoginRight);
                fiillLoginRight.setBounds(60, 150, 345, 35);

                fillPasswordRight.setBackground(Color.white);
                panel2.add(fillPasswordRight);
                StringsFormatter.setTextFieldLength(100, fillPasswordRight);
                fillPasswordRight.setBounds(60, 230, 345, 35);

                fillPasswordRight2.setBackground(Color.white);
                panel2.add(fillPasswordRight2);
                StringsFormatter.setTextFieldLength(100, fillPasswordRight2);
                fillPasswordRight2.setBounds(60, 310, 345, 35);

                registerButton.setText("Register");
                registerButton.setForeground(new Color(235, 242, 250));
                registerButton.setBackground(new Color(5, 102, 141));
                panel2.add(registerButton);
                registerButton.setBounds(330, 405, 98, 40);
                registerButton.addActionListener(e -> {
                    String password = new String(fillPasswordRight.getPassword());
                    String passwordcheck = new String(fillPasswordRight2.getPassword());
                    if (fiillLoginRight.getText().equals("") || password.equals("") || passwordcheck.equals("")) {
                        JOptionPane.showMessageDialog(new Frame(), "Fields must be filled");
                    } else {
                        if (!password.equals(passwordcheck)) {
                            JOptionPane.showMessageDialog(new Frame(), "Passwords do not match.");
                        } else {
                            try {
                                Requests.createAdmin(fiillLoginRight.getText(), password);
                                JOptionPane.showMessageDialog(new Frame(), "You created an account! Now Sign In");
                                dispose();
                                new LoginFrame();
                            } catch (SQLException throwables) {
                                JOptionPane.showMessageDialog(new Frame(), "Login must be unique, choose another one.");
                            }
                        }
                    }
                });

                loginRight.setText("Login");
                loginRight.setForeground(new Color(5, 102, 141));
                panel2.add(loginRight);
                loginRight.setBounds(65, 125, 65, 25);

                passwordRight.setText("Password");
                passwordRight.setForeground(new Color(5, 102, 141));
                panel2.add(passwordRight);
                passwordRight.setBounds(60, 205, 65, 25);

                confirmPassL.setText("Confirm password");
                confirmPassL.setForeground(new Color(5, 102, 141));
                panel2.add(confirmPassL);
                confirmPassL.setBounds(60, 285, 125, 25);

                Dimension preferredSize = new Dimension();
                preferredSize.width = 428;
                preferredSize.height = 445;
                panel2.setMinimumSize(preferredSize);
                panel2.setPreferredSize(preferredSize);
            }
            dialogPane.add(panel2, BorderLayout.CENTER);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    public JPasswordField getFillPassword() {
        return fillPassword;
    }

    public void setFillPassword(JPasswordField fillPassword) {
        this.fillPassword = fillPassword;
    }

    public JTextField getFiillLoginRight() {
        return fiillLoginRight;
    }

    public void setFiillLoginRight(JTextField fiillLoginRight) {
        this.fiillLoginRight = fiillLoginRight;
    }

    public JPasswordField getFillPasswordRight() {
        return fillPasswordRight;
    }

    public void setFillPasswordRight(JPasswordField fillPasswordRight) {
        this.fillPasswordRight = fillPasswordRight;
    }

    public JPasswordField getFillPasswordRight2() {
        return fillPasswordRight2;
    }

    public void setFillPasswordRight2(JPasswordField fillPasswordRight2) {
        this.fillPasswordRight2 = fillPasswordRight2;
    }

    public JTextField getFillLogin() {
        return fillLogin;
    }

    public void setFillLogin(JTextField fillLogin) {
        this.fillLogin = fillLogin;
    }
}
