package tableFrames;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class ClientDetailsFrame extends JFrame {

    public ClientDetailsFrame(String name, String last, String email, String pass, String date) {
        initComponents();
        nameL.setText(nameL.getText() + " " + name + " " + last);
        emailL.setText(emailL.getText()+ " " + email);
        passL.setText(passL.getText()+ " " + pass);
        birthL.setText(birthL.getText()+ " " + date);
        setVisible(true);
    }

    public ClientDetailsFrame() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        nameL = new JLabel();
        emailL = new JLabel();
        passL = new JLabel();
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
                nameL.setBounds(35, 40, 455, 40);

                emailL.setText("Email:");
                emailL.setForeground(Color.black);
                emailL.setFont(emailL.getFont().deriveFont(emailL.getFont().getStyle() | Font.BOLD, emailL.getFont().getSize() + 17f));
                contentPanel.add(emailL);
                emailL.setBounds(35, 90, 455, 40);

                passL.setText("Password:");
                passL.setForeground(Color.black);
                passL.setFont(passL.getFont().deriveFont(passL.getFont().getStyle() | Font.BOLD, passL.getFont().getSize() + 17f));
                contentPanel.add(passL);
                passL.setBounds(35, 140, 455, 40);

                birthL.setText("Birth date: ");
                birthL.setForeground(Color.black);
                birthL.setFont(birthL.getFont().deriveFont(birthL.getFont().getStyle() | Font.BOLD, birthL.getFont().getSize() + 17f));
                contentPanel.add(birthL);
                birthL.setBounds(35, 190, 455, 40);

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
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                okButton.setText("OK");
                okButton.setBackground(new Color(66, 122, 161));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                okButton.addActionListener(e -> {
                    this.dispose();
                });
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

    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel nameL;
    private JLabel emailL;
    private JLabel passL;
    private JLabel birthL;
    private JPanel buttonBar;
    private JButton okButton;
    private JPanel panel1;
}
