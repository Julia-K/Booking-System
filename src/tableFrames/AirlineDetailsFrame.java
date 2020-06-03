package tableFrames;

import allComands.Requests;
import allComands.StringsFormatter;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedHashMap;
import javax.swing.*;
import javax.swing.border.*;

public class AirlineDetailsFrame extends JFrame {
    private JPanel dialogPane;
    private LinkedHashMap<Integer, Integer> planesWithId;
    private JPanel contentPanel;
    private JLabel airlineL;
    private JTextField fillAirline2;
    private JLabel fillAirline;
    private JPanel buttonBar;
    private JPanel panel5;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton okButton;
    private JPanel panel1;
    private DefaultListModel<String> model;
    private JLabel codeL;
    private JTextField fillCode;
    private JCheckBox checkBox;
    private JComboBox comboBox;
    private JTextField fillQuantity;
    private JLabel quantityL;
    private JLabel planeL;
    private int id;

    public AirlineDetailsFrame() throws SQLException {
        boolean update = false;
        initAddComponents();
        setVisibility(false);
        setVisible(true);
    }

    public AirlineDetailsFrame(String name, String code, String[] planes) {
        initDetailComponents();
        fillAirline.setText(name + " (" + code+")");
        for (int i = 0; i < planes.length; i++) {
            model.add(i, planes[i]);
        }
        setVisible(true);
    }

    public AirlineDetailsFrame(int id, String name, String code) {
        this.id = id;
        initUpdateComponents();
        fillAirline2.setText(name);
        fillCode.setText(code);
        setVisible(true);
    }

    private void initDetailComponents() {
        model = new DefaultListModel<String>();
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        airlineL = new JLabel();
        fillAirline = new JLabel();
        JLabel listL = new JLabel();
        JScrollPane scrollPane1 = new JScrollPane();
        JList<String> fillList = new JList<>(model);
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

                airlineL.setText("Airline:");
                airlineL.setForeground(new Color(66, 122, 161));
                airlineL.setFont(airlineL.getFont().deriveFont(airlineL.getFont().getStyle() | Font.BOLD, airlineL.getFont().getSize() + 17f));
                airlineL.setBorder(null);
                contentPanel.add(airlineL);
                airlineL.setBounds(35, 40, 455, 40);

                fillAirline.setForeground(Color.black);
                fillAirline.setFont(fillAirline.getFont().deriveFont(fillAirline.getFont().getStyle() | Font.BOLD, fillAirline.getFont().getSize() + 12f));
                contentPanel.add(fillAirline);
                fillAirline.setBounds(35, 85, 455, 40);

                listL.setText("List of airplanes:");
                listL.setForeground(Color.black);
                listL.setFont(listL.getFont().deriveFont(listL.getFont().getStyle() | Font.BOLD, listL.getFont().getSize() + 17f));
                contentPanel.add(listL);
                listL.setBounds(35, 140, 455, 40);

                {
                    fillList.setSelectionBackground(new Color(235, 242, 250));
                    fillList.setSelectionForeground(Color.black);
                    fillList.setBackground(new Color(235, 242, 250));
                    fillList.setForeground(Color.black);
                    scrollPane1.setViewportView(fillList);
                }
                contentPanel.add(scrollPane1);
                scrollPane1.setBounds(35, 190, 430, 115);

                {
                    Dimension preferredSize = new Dimension();
                    preferredSize.width= 490;
                    preferredSize.height= 305;
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

    private void initAddComponents() throws SQLException {
        planesWithId = Requests.getPlanesWithId();
        dialogPane = new JPanel();
        JPanel contentPane = new JPanel();
        airlineL = new JLabel();
        codeL = new JLabel();
        fillAirline2 = new JTextField();
        fillCode = new JTextField();
        checkBox = new JCheckBox();
        comboBox = getComboBox();
        fillQuantity = new JTextField();
        JPanel panel5 = new JPanel();
        quantityL = new JLabel();
        planeL = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();
        JPanel panel = new JPanel();

        setResizable(false);
        setMinimumSize(new Dimension(680, 430));
        setBackground(new Color(235, 242, 250));
        var contentPane2 = getContentPane();
        contentPane2.setLayout(new BorderLayout());

        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBackground(new Color(235, 242, 250));
            dialogPane.setLayout(new BorderLayout());

            {
                contentPane.setBackground(new Color(235, 242, 250));
                contentPane.setLayout(null);

                airlineL.setText("Airline");
                airlineL.setForeground(new Color(66, 122, 161));
                airlineL.setFont(airlineL.getFont().deriveFont(airlineL.getFont().getStyle() | Font.BOLD, airlineL.getFont().getSize() + 17f));
                airlineL.setBorder(null);
                contentPane.add(airlineL);
                airlineL.setBounds(35, 30, 110, 40);

                codeL.setText("Code:");
                codeL.setForeground(Color.black);
                codeL.setFont(codeL.getFont().deriveFont(codeL.getFont().getStyle() | Font.BOLD, codeL.getFont().getSize() + 17f));
                contentPane.add(codeL);
                codeL.setBounds(35, 125, 135, 40);

                fillAirline2.setBackground(Color.white);
                StringsFormatter.setTextFieldLength(60,fillAirline2);
                contentPane.add(fillAirline2);
                fillAirline2.setBounds(35, 75, 195, 40);

                fillCode.setBackground(Color.white);
                StringsFormatter.setTextFieldLength(2, fillCode);
                contentPane.add(fillCode);
                fillCode.setBounds(35, 175, 195, 40);

                checkBox.setText("Add plane to the airline");
                checkBox.setBackground(new Color(235, 242, 250));
                checkBox.setForeground(Color.black);
                contentPane.add(checkBox);
                checkBox.setBounds(40, 220, 160, 30);
                checkBox.addItemListener(e -> {
                    if(e.getStateChange() == ItemEvent.DESELECTED) {
                        setVisibility(false);
                    } else {
                        setVisibility(true);
                    }
                });

                comboBox.setBackground(Color.white);
                contentPane.add(comboBox);
                comboBox.setBounds(275, 75, 195, 40);

                fillQuantity.setBackground(Color.white);
                StringsFormatter.setOnlyDigits(fillQuantity);
                contentPane.add(fillQuantity);
                fillQuantity.setBounds(275, 175, 195, 40);

                {
                    panel5.setBackground(new Color(5, 102, 141));
                    panel5.setPreferredSize(new Dimension(5, 200));
                    panel5.setMinimumSize(new Dimension(5, 200));
                    panel5.setMaximumSize(new Dimension(5, 200));
                    panel5.setLayout(null);

                    {
                        Dimension preferredSize = new Dimension();
                        preferredSize.width = 470;
                        preferredSize.height = 255;
                        panel5.setMinimumSize(preferredSize);
                        panel5.setPreferredSize(preferredSize);
                    }
                }
                contentPane.add(panel5);
                panel5.setBounds(265, 35, 5, 220);

                quantityL.setText("Quantity:");
                quantityL.setForeground(Color.black);
                quantityL.setFont(quantityL.getFont().deriveFont(quantityL.getFont().getStyle() | Font.BOLD, quantityL.getFont().getSize() + 17f));
                contentPane.add(quantityL);
                quantityL.setBounds(280, 125, 135, 40);

                planeL.setText("Plane:");
                planeL.setForeground(new Color(66, 122, 161));
                planeL.setFont(planeL.getFont().deriveFont(planeL.getFont().getStyle() | Font.BOLD, planeL.getFont().getSize() + 17f));
                planeL.setBorder(null);
                contentPane.add(planeL);
                planeL.setBounds(280, 30, 110, 40);

                {
                    Dimension preferredSize = new Dimension();
                    preferredSize.width = 470;
                    preferredSize.height = 255;
                    contentPane.setMinimumSize(preferredSize);
                    contentPane.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(contentPane, BorderLayout.CENTER);

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
                    if(isAddingValidate()) {
                        if(!checkBox.isSelected()) {
                            try {
                                Requests.createAirline(fillAirline2.getText(),fillCode.getText());
                                dispose();
                            } catch (SQLServerException ex) {
                                JOptionPane.showMessageDialog(new Frame(), "Code and Airline name must be unique!");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        } else {
                            try {
                                Requests.createAirline(fillAirline2.getText(),fillCode.getText());
                                ResultSet rs = Requests.readTableByRequest("SELECT TOP 1 airlineID FROM airline ORDER BY airlineID DESC");
                                while (rs.next()) {
                                    int id = rs.getInt(1);
                                    Requests.createPlaneAirplane((Integer) planesWithId.get(comboBox.getSelectedIndex()),id, Integer.parseInt(fillQuantity.getText()));
                                    updateContent();
                                    dispose();
                                }
                            } catch (SQLServerException exception) {
                                JOptionPane.showMessageDialog(new Frame(), "Code must be unique!");
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    }
                });
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);

            {
                panel.setPreferredSize(new Dimension(160, 200));
                panel.setBackground(new Color(5, 102, 141));
                panel.setForeground(new Color(5, 102, 141));
                panel.setMaximumSize(new Dimension(160, 200));
                panel.setMinimumSize(new Dimension(160, 200));
                panel.setLayout(null);
            }
            dialogPane.add(panel, BorderLayout.WEST);
        }
        contentPane2.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void initUpdateComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        airlineL = new JLabel();
        panel5 = new JPanel();
        planeL = new JLabel();
        addButton = new JButton();
        updateButton = new JButton();
        deleteButton = new JButton();
        codeL = new JLabel();
        fillAirline2 = new JTextField();
        fillCode = new JTextField();
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

                airlineL.setText("Airline:");
                airlineL.setForeground(new Color(66, 122, 161));
                airlineL.setFont(airlineL.getFont().deriveFont(airlineL.getFont().getStyle() | Font.BOLD, airlineL.getFont().getSize() + 17f));
                airlineL.setBorder(null);
                contentPanel.add(airlineL);
                airlineL.setBounds(35, 40, 455, 40);

                codeL.setText("Code:");
                codeL.setForeground(Color.black);
                codeL.setFont(codeL.getFont().deriveFont(codeL.getFont().getStyle() | Font.BOLD, codeL.getFont().getSize() + 17f));
                contentPanel.add(codeL);
                codeL.setBounds(35, 140, 455, 40);

                fillAirline2.setBackground(Color.white);
                StringsFormatter.setTextFieldLength(60, fillAirline2);
                contentPanel.add(fillAirline2);
                fillAirline2.setBounds(35, 90, 195, 40);

                fillCode.setBackground(Color.white);
                StringsFormatter.setTextFieldLength(2,fillCode);
                contentPanel.add(fillCode);
                fillCode.setBounds(35, 190, 195, 40);


                panel5.setBackground(new Color(5, 102, 141));
                panel5.setPreferredSize(new Dimension(5, 200));
                panel5.setMinimumSize(new Dimension(5, 200));
                panel5.setMaximumSize(new Dimension(5, 200));
                panel5.setLayout(null);

                contentPanel.setMinimumSize(new Dimension(490,230));
                contentPanel.setPreferredSize(new Dimension(490,230));

            }
            contentPanel.add(panel5);
            panel5.setBounds(260, 75, 5, 220);

            addButton.setText("Add");
            addButton.setBackground(new Color(5, 102, 141));
            addButton.setForeground(Color.white);
            addButton.setFont(addButton.getFont().deriveFont(addButton.getFont().getStyle() & ~Font.ITALIC));
            contentPanel.add(addButton);
            addButton.setBounds(310, 95, 110, 40);
            addButton.addActionListener(e-> {
                try {
                    new PlaneAirlineFrame(0, id);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });

            updateButton.setText("Update");
            updateButton.setBackground(new Color(5, 102, 141));
            updateButton.setForeground(Color.white);
            contentPanel.add(updateButton);
            updateButton.setBounds(310, 170, 110, 40);
            updateButton.addActionListener(e-> {
                try {
                    new PlaneAirlineFrame(1, id);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });

            deleteButton.setText("Delete");
            deleteButton.setBackground(new Color(5, 102, 141));
            deleteButton.setForeground(Color.white);
            contentPanel.add(deleteButton);
            deleteButton.setBounds(310, 245, 110, 40);
            deleteButton.addActionListener(e-> {
                try {
                    new PlaneAirlineFrame(2, id);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            });

            planeL.setText("Plane:");
            planeL.setForeground(Color.black);
            planeL.setFont(planeL.getFont().deriveFont(planeL.getFont().getStyle() | Font.BOLD, planeL.getFont().getSize() + 10f));
            contentPanel.add(planeL);
            planeL.setBounds(310, 40, 115, 40);

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

                okButton.addActionListener(e-> {
                    if(isUpdatingValidate()) {
                        try {
                            Requests.updateAirline(id,fillAirline2.getText(),fillCode.getText());
                            dispose();
                        } catch (SQLServerException throwables) {
                            JOptionPane.showMessageDialog(new Frame(), "Code must be unique!");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
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

    private void setVisibility(boolean x) {
        planeL.setVisible(x);
        comboBox.setVisible(x);
        quantityL.setVisible(x);
        fillQuantity.setVisible(x);
    }

    private void updateContent() throws SQLException {
        planesWithId = Requests.getPlanesWithId();
        comboBox = getComboBox();
    }

    private JComboBox getComboBox() throws SQLException {
        JComboBox x = new JComboBox();
        ResultSet rs = Requests.readTableByRequest("select planeID from plane");
        while (rs.next()) {
            int id = rs.getInt("planeID");
            x.addItem(Requests.getStringPlane(id));
        }
        return x;
    }

    public boolean isUpdatingValidate() {
        if (fillAirline2.getText().equals("") || fillCode.getText().equals("")) {
            JOptionPane.showMessageDialog(new Frame(), "All fields must be filled!");
            return false;
        } else if (fillCode.getText().length() != 2) {
            JOptionPane.showMessageDialog(new Frame(), "Code must have 2 letters.");
            return false;
        } else {
            return true;
        }
    }

    public boolean isAddingValidate() {
        if(checkBox.isSelected()) {
            if (fillAirline2.getText().equals("") || fillCode.getText().equals("") || fillQuantity.getText().equals("")) {
                JOptionPane.showMessageDialog(new Frame(), "All fields must be filled!");
                return false;
            } else if (fillCode.getText().length() != 2) {
                JOptionPane.showMessageDialog(new Frame(), "Code must have 2 letters.");
                return false;
            } else {
                return true;
            }
        } else {
            if (fillAirline2.getText().equals("") || fillCode.getText().equals("")) {
                JOptionPane.showMessageDialog(new Frame(), "All fields must be filled!");
                return false;
            } else if (fillCode.getText().length() != 2) {
                JOptionPane.showMessageDialog(new Frame(), "Code must have 2 letters.");
                return false;
            } else {
                return true;
            }
        }
    }

}
