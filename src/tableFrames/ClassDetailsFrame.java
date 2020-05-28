package tableFrames;

import allComands.Requests;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class ClassDetailsFrame extends JFrame {
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel classL;
    private JLabel priceL;
    private JPanel buttonBar;
    private JButton okButton;
    private JPanel panel1;
    private JTextField fillName;
    private JTextField fillPrice;
    private int id;
    private boolean update;

    public ClassDetailsFrame() {
        this.update = false;
        initAddUpdateComponents();
        setVisible(true);
    }

    public ClassDetailsFrame(Boolean update, int id, String name, String price) {
        this.update = update;
        if(update) {
            initAddUpdateComponents();
            this.id = id;
            fillName.setText(name);
            fillPrice.setText(price);
        } else {
            initDetailComponents();
            classL.setText(classL.getText() + " " + name);
            priceL.setText(priceL.getText() + " " +price);
        }
        setVisible(true);
    }

    private void initDetailComponents() {

        dialogPane = new JPanel();
        contentPanel = new JPanel();
        classL = new JLabel();
        priceL = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();
        panel1 = new JPanel();

        setResizable(false);
        setMinimumSize(new Dimension(670, 430));
        setBackground(new Color(235, 242, 250));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBackground(new Color(235, 242, 250));
            dialogPane.setLayout(new BorderLayout());

            {
                contentPanel.setBackground(new Color(235, 242, 250));
                contentPanel.setLayout(null);

                classL.setText("Class:");
                classL.setForeground(new Color(66, 122, 161));
                classL.setFont(classL.getFont().deriveFont(classL.getFont().getStyle() | Font.BOLD, classL.getFont().getSize() + 20f));
                classL.setBorder(null);
                contentPanel.add(classL);
                classL.setBounds(35, 85, 455, 40);

                priceL.setText("Price:");
                priceL.setForeground(Color.black);
                priceL.setFont(priceL.getFont().deriveFont(priceL.getFont().getStyle() | Font.BOLD, priceL.getFont().getSize() + 17f));
                contentPanel.add(priceL);
                priceL.setBounds(35, 135, 455, 40);

                {
                    Dimension preferredSize = new Dimension();
                    preferredSize.width = 490;
                    preferredSize.height = 175;
                    contentPanel.setMinimumSize(preferredSize);
                    contentPanel.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(contentPanel, BorderLayout.EAST);

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
                okButton.addActionListener(e-> {
                    dispose();
                });
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
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void initAddUpdateComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        classL = new JLabel();
        JLabel nameL = new JLabel();
        priceL = new JLabel();
        fillName = new JTextField();
        fillPrice = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        panel1 = new JPanel();

        setResizable(false);
        setMinimumSize(new Dimension(670, 430));
        setBackground(new Color(235, 242, 250));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBackground(new Color(235, 242, 250));
            dialogPane.setLayout(new BorderLayout());

            {
                contentPanel.setBackground(new Color(235, 242, 250));
                contentPanel.setLayout(null);

                classL.setText("Class");
                classL.setForeground(new Color(66, 122, 161));
                classL.setFont(classL.getFont().deriveFont(classL.getFont().getStyle() | Font.BOLD, classL.getFont().getSize() + 20f));
                classL.setBorder(null);
                contentPanel.add(classL);
                classL.setBounds(50, 65, 90, 40);

                nameL.setText("Name");
                nameL.setForeground(Color.black);
                nameL.setFont(nameL.getFont().deriveFont(nameL.getFont().getStyle() | Font.BOLD, nameL.getFont().getSize() + 15f));
                contentPanel.add(nameL);
                nameL.setBounds(50, 130, 85, 45);

                priceL.setText("Price");
                priceL.setForeground(Color.black);
                priceL.setFont(priceL.getFont().deriveFont(priceL.getFont().getStyle() | Font.BOLD, priceL.getFont().getSize() + 15f));
                contentPanel.add(priceL);
                priceL.setBounds(50, 190, 80, 45);

                fillName.setBackground(Color.white);
                contentPanel.add(fillName);
                fillName.setBounds(160, 130, 170, 45);

                fillPrice.setBackground(Color.white);
                contentPanel.add(fillPrice);
                fillPrice.setBounds(160, 190, 170, 45);

                {
                    Dimension preferredSize = new Dimension();
                    preferredSize.width = 330;
                    preferredSize.height = 235;
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

                okButton.addActionListener(e-> {
                    if(update) {
                        try {
                            Requests.updateClass(id,fillName.getText(), Float.parseFloat(fillPrice.getText()));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    } else {
                        try {
                            Requests.createClass(fillName.getText(), Float.parseFloat(fillPrice.getText()));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    dispose();
                });

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
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }
}
