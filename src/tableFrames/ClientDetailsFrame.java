package tableFrames;

import allComands.PasswordUtils;
import allComands.Requests;
import allComands.StringsFormatter;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;

public class ClientDetailsFrame extends JFrame {
    private boolean update;
    private int id;
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel nameL;
    private MyOwnDatePicker birthDate;
    private JLabel emailL;
    private JLabel passL;
    private JLabel birthL;
    private JPanel buttonBar;
    private JButton okButton;
    private JPanel panel1;
    private JLabel firstL;
    private JLabel lastL;
    private JTextField fillFirst;
    private JTextField fillLast;
    private JComboBox<String> dayBox;
    private JComboBox<String> monthBox;
    private JComboBox<String> yearBox;
    private JPanel panel3;
    private JPanel panel2;
    private JTextField fillEmail;
    private JPasswordField fillPassword;

    public ClientDetailsFrame(Boolean update, int id, String name, String last, String email, String pass, String date) {
        if(update) {
            this.id = id;
            birthDate = new MyOwnDatePicker(date);
            initAddUpdateComponents();
            fillFirst.setText(name);
            fillLast.setText(last);
            fillEmail.setText(email);
            fillPassword.setText(pass);
        } else {
            initDetailComponents();
            nameL.setText(nameL.getText() + " " + name + " " + last);
            emailL.setText(emailL.getText() + " " + email);
            LocalDate birth = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int age = Period.between(birth,LocalDate.now()).getYears();
            birthL.setText(birthL.getText() + " " + date + " (Age: " + age +")");
        }
        this.update = update;
        setVisible(true);
    }

    public ClientDetailsFrame() {
        Date data = java.util.Calendar.getInstance().getTime();
        String s = new SimpleDateFormat("yyyy-MM-dd").format(data);
        birthDate = new MyOwnDatePicker(s);
        update = false;
        initAddUpdateComponents();
        setVisible(true);
    }

    private void initAddUpdateComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        firstL = new JLabel();
        lastL = new JLabel();
        fillFirst = new JTextField();
        birthL = new JLabel();
        fillLast = new JTextField();
        dayBox = new JComboBox<>();
        monthBox = new JComboBox<>();
        yearBox = new JComboBox<>();
        panel3 = new JPanel();
        buttonBar = new JPanel();
        okButton = new JButton();
        panel1 = new JPanel();
        panel2 = new JPanel();
        emailL = new JLabel();
        fillEmail = new JTextField();
        passL = new JLabel();
        fillPassword = new JPasswordField();

        setResizable(false);
        setMinimumSize(new Dimension(670, 430));
        setBackground(new Color(235, 242, 250));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBackground(new Color(235, 242, 250));
            dialogPane.setMaximumSize(new Dimension(850, 510));
            dialogPane.setMinimumSize(new Dimension(850, 510));
            dialogPane.setPreferredSize(new Dimension(850, 510));
            dialogPane.setLayout(new BorderLayout());

            {
                contentPanel.setBackground(new Color(235, 242, 250));
                contentPanel.setMaximumSize(new Dimension(200, 230));
                contentPanel.setMinimumSize(new Dimension(200, 230));
                contentPanel.setPreferredSize(new Dimension(200, 230));
                contentPanel.setLayout(null);

                firstL.setText("First name:");
                firstL.setForeground(new Color(66, 122, 161));
                firstL.setFont(firstL.getFont().deriveFont(firstL.getFont().getStyle() | Font.BOLD, firstL.getFont().getSize() + 17f));
                firstL.setBorder(null);
                contentPanel.add(firstL);
                firstL.setBounds(35, 40, 205, 40);

                lastL.setText("Last name:");
                lastL.setForeground(Color.black);
                lastL.setFont(lastL.getFont().deriveFont(lastL.getFont().getStyle() | Font.BOLD, lastL.getFont().getSize() + 17f));
                contentPanel.add(lastL);
                lastL.setBounds(35, 140, 185, 40);

                fillFirst.setBackground(Color.white);
                StringsFormatter.setTextFieldLength(30,fillFirst);
                StringsFormatter.setOnlyLetters(fillFirst);
                contentPanel.add(fillFirst);
                fillFirst.setBounds(35, 90, 195, 40);

                birthL.setText("Birth date:");
                birthL.setForeground(Color.black);
                birthL.setFont(birthL.getFont().deriveFont(birthL.getFont().getStyle() | Font.BOLD, birthL.getFont().getSize() + 17f));
                contentPanel.add(birthL);
                birthL.setBounds(35, 240, 165, 40);

                fillLast.setBackground(Color.white);
                StringsFormatter.setTextFieldLength(30,fillLast);
                StringsFormatter.setOnlyLetters(fillLast);

                contentPanel.add(fillLast);
                fillLast.setBounds(35, 190, 195, 40);
                birthDate.addTo(contentPanel);
                birthDate.setBounds(35, 290, 195, 26);

                {
                    panel3.setBackground(new Color(5, 102, 141));
                    panel3.setPreferredSize(new Dimension(5, 200));
                    panel3.setMinimumSize(new Dimension(5, 200));
                    panel3.setMaximumSize(new Dimension(5, 200));
                    panel3.setLayout(null);

                    {
                        Dimension preferredSize = new Dimension();
                        for (int i = 0; i < panel3.getComponentCount(); i++) {
                            Rectangle bounds = panel3.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel3.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        System.out.println(preferredSize.width + " "+preferredSize.height);
                        panel3.setMinimumSize(preferredSize);
                        panel3.setPreferredSize(preferredSize);
                    }
                }
                contentPanel.add(panel3);
                panel3.setBounds(345, 40, 5, 300);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setBackground(new Color(235, 242, 250));
                buttonBar.setMaximumSize(new Dimension(105, 42));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout) buttonBar.getLayout()).columnWidths = new int[]{0, 80};
                ((GridBagLayout) buttonBar.getLayout()).columnWeights = new double[]{1.0, 0.0};

                okButton.setText("OK");
                okButton.setBackground(new Color(66, 122, 161));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);

            {
                panel1.setPreferredSize(new Dimension(160, 200));
                panel1.setBackground(new Color(5, 102, 141));
                panel1.setForeground(new Color(5, 102, 141));
                panel1.setMaximumSize(new Dimension(160, 200));
                panel1.setMinimumSize(new Dimension(160, 200));
                panel1.setLayout(null);
            }
            dialogPane.add(panel1, BorderLayout.WEST);

            {
                panel2.setMinimumSize(new Dimension(300, 200));
                panel2.setPreferredSize(new Dimension(300, 230));
                panel2.setBackground(new Color(235, 242, 250));
                panel2.setLayout(null);

                emailL.setText("E-mail:");
                emailL.setForeground(new Color(66, 122, 161));
                emailL.setFont(emailL.getFont().deriveFont(emailL.getFont().getStyle() | Font.BOLD, emailL.getFont().getSize() + 17f));
                emailL.setBorder(null);
                panel2.add(emailL);
                emailL.setBounds(5, 40, 205, 40);

                fillEmail.setBackground(Color.white);
                StringsFormatter.setTextFieldLength(45,fillEmail);

                panel2.add(fillEmail);
                fillEmail.setBounds(5, 90, 195, 40);

                passL.setText("Password:");
                passL.setForeground(Color.black);
                passL.setFont(passL.getFont().deriveFont(passL.getFont().getStyle() | Font.BOLD, passL.getFont().getSize() + 17f));
                panel2.add(passL);
                passL.setBounds(5, 140, 185, 40);

                fillPassword.setBackground(Color.white);
                StringsFormatter.setTextFieldLength(100,fillPassword);
                panel2.add(fillPassword);
                fillPassword.setBounds(5, 190, 195, 40);
            }

            okButton.addActionListener(e -> {
                String date = birthDate.getDate();
                String codedPassword = PasswordUtils.hashing(new String(fillPassword.getPassword()));
                if(isValidate()) {
                    if(update) {
                        try {
                            Requests.updateClient(id,fillFirst.getText(),fillLast.getText(),fillEmail.getText(), codedPassword,date);
                            dispose();
                        } catch (SQLServerException exception) {
                            JOptionPane.showMessageDialog(new Frame(), "E-mail must be unique!");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                    } else {
                        try {
                            Requests.createClient(fillFirst.getText(),fillLast.getText(),fillEmail.getText(),codedPassword,date);
                            dispose();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
            dialogPane.add(panel2, BorderLayout.EAST);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void initDetailComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        nameL = new JLabel();
        emailL = new JLabel();
        birthL = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();
        panel1 = new JPanel();

        setResizable(false);
        setMinimumSize(new Dimension(670, 430));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBackground(new Color(235, 242, 250));
            dialogPane.setLayout(new BorderLayout());

            {
                contentPanel.setBackground(new Color(235, 242, 250));
                contentPanel.setLayout(null);

                nameL.setText("Name: ");
                nameL.setForeground(new Color(66, 122, 161));
                nameL.setFont(nameL.getFont().deriveFont(nameL.getFont().getStyle() | Font.BOLD, nameL.getFont().getSize() + 17f));
                nameL.setBorder(null);
                contentPanel.add(nameL);
                nameL.setBounds(35, 70, 455, 40);

                emailL.setText("Email:");
                emailL.setForeground(Color.black);
                emailL.setFont(emailL.getFont().deriveFont(emailL.getFont().getStyle() | Font.BOLD, emailL.getFont().getSize() + 17f));
                contentPanel.add(emailL);
                emailL.setBounds(35, 120, 455, 40);

                birthL.setText("Birth date: ");
                birthL.setForeground(Color.black);
                birthL.setFont(birthL.getFont().deriveFont(birthL.getFont().getStyle() | Font.BOLD, birthL.getFont().getSize() + 17f));
                contentPanel.add(birthL);
                birthL.setBounds(35, 170, 455, 40);

                {
                    Dimension preferredSize = new Dimension();
                    preferredSize.width = 490;
                    preferredSize.height = 230;
                    contentPanel.setMinimumSize(preferredSize);
                    contentPanel.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setBackground(new Color(235, 242, 250));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout) buttonBar.getLayout()).columnWidths = new int[]{0, 80};
                ((GridBagLayout) buttonBar.getLayout()).columnWeights = new double[]{1.0, 0.0};

                okButton.setText("OK");
                okButton.setBackground(new Color(66, 122, 161));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                okButton.addActionListener(e -> this.dispose());
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);

            {
                panel1.setPreferredSize(new Dimension(160, 200));
                panel1.setBackground(new Color(5, 102, 141));
                panel1.setMaximumSize(new Dimension(160, 200));
                panel1.setMinimumSize(new Dimension(160, 200));
                panel1.setLayout(null);
            }
            dialogPane.add(panel1, BorderLayout.WEST);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    public boolean isValidate() {
        if(!StringsFormatter.checkEmail(fillEmail.getText())) {
            JOptionPane.showMessageDialog(new Frame(), "Wrong e-mail format.");
            return false;
        } else if(!StringsFormatter.checkBirthDate( birthDate.getDate())) {
            JOptionPane.showMessageDialog(new Frame(), "Client must be at least 18 years old");
            return false;
        } else if (fillEmail.getText().equals("") || fillFirst.getText().equals("")
                || fillLast.getText().equals("") || birthDate.getDate().equals("") || fillPassword.getPassword().length == 0) {
            JOptionPane.showMessageDialog(new Frame(), "All fields must be filled!");
            return false;
        } else {
            return true;
        }
    }
}
