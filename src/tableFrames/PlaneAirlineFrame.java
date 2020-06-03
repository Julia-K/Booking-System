package tableFrames;

import allComands.Requests;
import allComands.StringsFormatter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class PlaneAirlineFrame extends JFrame {
    private LinkedHashMap<Integer, Integer> planesWithId;
    private JComboBox comboBox;
    private JTextField fillQuantity;
    private final int option;
    private final int airlineId;

    public PlaneAirlineFrame(int option, int airlineId) throws SQLException {
        this.option = option;
        this.airlineId = airlineId;
        initComponents();
        setVisible(true);
    }
    private void initComponents() throws SQLException {
        planesWithId = Requests.getPlanesWithId();
        JPanel dialogPane = new JPanel();
        JPanel contentPane = new JPanel();
        comboBox = new JComboBox();
        fillQuantity = new JTextField();
        JLabel quantityL = new JLabel();
        JLabel planeL = new JLabel();
        JPanel buttonBar = new JPanel();
        JButton okButton = new JButton();
        JPanel panel = new JPanel();

        if(option==0) {      // add
            comboBox = getAllPlanesComboBox();
        } else if (option ==1 ) {
            planesWithId = Requests.getPlanesByAirlineID(airlineId);
            comboBox = getSelectedPlanesComboBox();
            comboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    try {
                        int planeId = (Integer)planesWithId.get(comboBox.getSelectedIndex());
                        ResultSet rs = Requests.readTableByRequest("select planes_quantity from plane_airline\n" +
                                "where plane_id = " + planeId + " and airline_id = " + airlineId);
                        rs.next();
                        fillQuantity.setText(rs.getString(1));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
            comboBox.setSelectedItem(0);
        } else { //delete
            fillQuantity.setVisible(false);
            quantityL.setVisible(false);
            planeL.setBounds(60,60,110,40);
            comboBox.setBounds(55, 105, 195, 40);
            planesWithId = Requests.getPlanesByAirlineID(airlineId);
            comboBox = getSelectedPlanesComboBox();
        }

        setResizable(false);
        setMinimumSize(new Dimension(670, 430));
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

                comboBox.setBackground(Color.white);
                contentPane.add(comboBox);
                comboBox.setBounds(55, 90, 195, 40);

                fillQuantity.setBackground(Color.white);
                contentPane.add(fillQuantity);
                StringsFormatter.setTextFieldLength(2, fillQuantity);
                StringsFormatter.setOnlyDigits(fillQuantity);
                fillQuantity.setBounds(55, 210, 195, 40);

                quantityL.setText("Quantity:");
                quantityL.setForeground(Color.black);
                quantityL.setFont(quantityL.getFont().deriveFont(quantityL.getFont().getStyle() | Font.BOLD, quantityL.getFont().getSize() + 17f));
                contentPane.add(quantityL);
                quantityL.setBounds(60, 160, 135, 40);

                planeL.setText("Plane:");
                planeL.setForeground(new Color(66, 122, 161));
                planeL.setFont(planeL.getFont().deriveFont(planeL.getFont().getStyle() | Font.BOLD, planeL.getFont().getSize() + 17f));
                planeL.setBorder(null);
                contentPane.add(planeL);
                planeL.setBounds(60, 45, 110, 40);

                contentPane.setMinimumSize(new Dimension(250,250));
                contentPane.setPreferredSize(new Dimension(250,250));
            }
            dialogPane.add(contentPane, BorderLayout.CENTER);

            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setBackground(new Color(235, 242, 250));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout) buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout) buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                okButton.setText("OK");
                okButton.setBackground(new Color(66, 122, 161));
                okButton.addActionListener(e-> {
                    if(isValidate()) {
                        try {
                            if(option==0) {
                                int planeId = (Integer) planesWithId.get(comboBox.getSelectedIndex());
                                Requests.createPlaneAirplane(planeId, airlineId, Integer.parseInt(fillQuantity.getText()));
                                dispose();
                            } else if (option == 1) {
                                int planeId = (Integer) planesWithId.get(comboBox.getSelectedIndex());
                                Requests.updatePlaneAirline(planeId,airlineId, Integer.parseInt(fillQuantity.getText()));
                                dispose();
                            } else {
                                int planeId = (Integer) planesWithId.get(comboBox.getSelectedIndex());
                                Requests.deleteFromPlaneAirline(airlineId, planeId);
                                dispose();
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                    }
                });

                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
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

    private JComboBox getSelectedPlanesComboBox() throws SQLException {
        boolean first = true;
        JComboBox x = new JComboBox();
        ResultSet rs = Requests.readTableByRequest("select * from plane_airline\n" +
                "where airline_id = '"+airlineId+"'");
        while (rs.next()) {
            if(first) {
                fillQuantity.setText(rs.getString(3));
                first = false;
            }
            int planeId = rs.getInt(1);
            comboBox.addItem(Requests.getStringPlane(planeId));
        }
        return comboBox;
    }

    private JComboBox getAllPlanesComboBox() throws SQLException {
        JComboBox x = new JComboBox();
        ResultSet rs = Requests.readTableByRequest("select planeID from plane");
        while (rs.next()) {
            int id = rs.getInt("planeID");
            x.addItem(Requests.getStringPlane(id));
        }
        return x;
    }

    private boolean isValidate() {
        if(fillQuantity.getText().equals("")) {
            JOptionPane.showMessageDialog(new Frame(), "All fields must be filled");
            return false;
        } else if ( fillQuantity.getText().equals("0")) {
            JOptionPane.showMessageDialog(new Frame(), "The number of planes must be greater than 0");
            return false;
        }
        return true;
    }
}

