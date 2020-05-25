package tableFrames;

import allComands.Requests;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.*;

public class AirportDetailsFrame extends JFrame {
    private LinkedHashMap<Integer,Integer> addressesWithId;
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel nameL;
    private JLabel fillNameL;
    private JLabel addressL;
    private JLabel fillAddressL;
    private JPanel buttonBar;
    private JButton okButton;
    private JPanel panel1;
    private JTextField fillnameL;
    private JLabel addressL3;
    private JTextField fillCodeL;
    private JComboBox comboBox;
    private JCheckBox checkBox;
    private JPanel panel3;
    private JPanel panel2;
    private JLabel countryL;
    private JTextField fillCountry;
    private JLabel cityL;
    private JTextField fillCity;
    private JLabel postalL;
    private JTextField fillPostal;
    private JLabel streetL;
    private JTextField fillStreet;
    private JLabel numberL;
    private JTextField fillNumber;
    private int id;
    private boolean update;

    public AirportDetailsFrame() throws SQLException {
        this.update = false;
        initAddUpdateComponents();
        setVisible(true);
    }

    public AirportDetailsFrame(Boolean update, int id, String name, String code, String address) throws SQLException {
        if(update) {
            this.id = id;
            initAddUpdateComponents();
            checkBox.setVisible(false);
            fillnameL.setText(name);
            fillCodeL.setText(code);
            ResultSet rs = Requests.readById(id,"airport");
            rs.next();
            int addressId = rs.getInt(4);
            System.out.println(addressId);
            for (Map.Entry<Integer, Integer> entry : addressesWithId.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                if (value == addressId) {
                    comboBox.setSelectedIndex(key);
                }
            }
        } else {
            initDetailComponents();
            String[] all = address.split(",", 2);
            fillNameL.setText(name +" ("+code+")");
            fillAddressL.setText("<html>"+all[0]+"<br/>"+all[1]+"</html>");
        }
        this.update = update;
        setVisible(true);
    }

    private void initDetailComponents() throws SQLException {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        nameL = new JLabel();
        fillNameL = new JLabel();
        addressL = new JLabel();
        fillAddressL = new JLabel();
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

                nameL.setText("Airport name:");
                nameL.setForeground(new Color(66, 122, 161));
                nameL.setFont(nameL.getFont().deriveFont(nameL.getFont().getStyle() | Font.BOLD, nameL.getFont().getSize() + 17f));
                nameL.setBorder(null);
                contentPanel.add(nameL);
                nameL.setBounds(35, 40, 455, 40);

                fillNameL.setForeground(new Color(66, 122, 161));
                fillNameL.setFont(fillNameL.getFont().deriveFont(fillNameL.getFont().getStyle() | Font.BOLD, fillNameL.getFont().getSize() + 12f));
                contentPanel.add(fillNameL);
                fillNameL.setBounds(35, 90, 455, 40);

                addressL.setText("Address:");
                addressL.setForeground(Color.black);
                addressL.setFont(addressL.getFont().deriveFont(addressL.getFont().getStyle() | Font.BOLD, addressL.getFont().getSize() + 17f));
                contentPanel.add(addressL);
                addressL.setBounds(35, 140, 455, 40);

                fillAddressL.setForeground(Color.black);
                fillAddressL.setFont(fillAddressL.getFont().deriveFont(fillAddressL.getFont().getStyle() | Font.BOLD, fillAddressL.getFont().getSize() + 12f));
                contentPanel.add(fillAddressL);
                fillAddressL.setBounds(35, 190, 455, 60);

                {
                    Dimension preferredSize = new Dimension();
                    preferredSize.width = 490;
                    preferredSize.height += 250;
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
                okButton.addActionListener(e -> dispose());
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

    private void initAddUpdateComponents() throws SQLException {
        addressesWithId = Requests.getAddressesWihtId();
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        nameL = new JLabel();
        addressL = new JLabel();
        fillnameL = new JTextField();
        addressL3 = new JLabel();
        fillCodeL = new JTextField();
        comboBox = getComboBox();
        checkBox = new JCheckBox();
        panel3 = new JPanel();
        buttonBar = new JPanel();
        okButton = new JButton();
        panel1 = new JPanel();
        panel2 = new JPanel();
        countryL = new JLabel();
        fillCountry = new JTextField();
        cityL = new JLabel();
        fillCity = new JTextField();
        postalL = new JLabel();
        fillPostal = new JTextField();
        streetL = new JLabel();
        fillStreet = new JTextField();
        numberL = new JLabel();
        fillNumber = new JTextField();

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

                nameL.setText("Airport name:");
                nameL.setForeground(new Color(66, 122, 161));
                nameL.setFont(nameL.getFont().deriveFont(nameL.getFont().getStyle() | Font.BOLD, nameL.getFont().getSize() + 17f));
                nameL.setBorder(null);
                contentPanel.add(nameL);
                nameL.setBounds(35, 40, 205, 40);

                addressL.setText("Code:");
                addressL.setForeground(Color.black);
                addressL.setFont(addressL.getFont().deriveFont(addressL.getFont().getStyle() | Font.BOLD, addressL.getFont().getSize() + 17f));
                contentPanel.add(addressL);
                addressL.setBounds(35, 140, 90, 40);

                fillnameL.setBackground(Color.white);
                contentPanel.add(fillnameL);
                fillnameL.setBounds(35, 90, 250, 40);

                addressL3.setText("Address:");
                addressL3.setForeground(Color.black);
                addressL3.setFont(addressL3.getFont().deriveFont(addressL3.getFont().getStyle() | Font.BOLD, addressL3.getFont().getSize() + 17f));
                contentPanel.add(addressL3);
                addressL3.setBounds(35, 240, 130, 40);

                fillCodeL.setBackground(Color.white);
                contentPanel.add(fillCodeL);
                fillCodeL.setBounds(35, 190, 250, 40);

                comboBox.setBackground(Color.white);
                contentPanel.add(comboBox);
                comboBox.setBounds(35, 290, 250, 40);
                setVisibility(false);

                if(checkBox.isVisible()) {
                    checkBox.setText("create your own");
                    checkBox.setBackground(new Color(235, 242, 250));
                    checkBox.setForeground(Color.black);
                    contentPanel.add(checkBox);
                    checkBox.setBounds(35, 335, 120, 30);
                    checkBox.addItemListener(e -> {
                        if(e.getStateChange() == ItemEvent.DESELECTED) {
                            setVisibility(false);
                        } else {
                            setVisibility(true);
                        }
                    });
                } else {
                    setVisibility(false);
                }
                {
                    panel3.setBackground(new Color(5, 102, 141));
                    panel3.setPreferredSize(new Dimension(5, 200));
                    panel3.setMinimumSize(new Dimension(5, 200));
                    panel3.setMaximumSize(new Dimension(5, 200));
                    panel3.setLayout(null);
                }
                contentPanel.add(panel3);
                panel3.setBounds(360, 35, 5, 365);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setBackground(new Color(235, 242, 250));
                buttonBar.setMaximumSize(new Dimension(105, 42));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

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

                countryL.setText("Country:");
                countryL.setForeground(Color.black);
                countryL.setFont(countryL.getFont().deriveFont(countryL.getFont().getStyle() | Font.BOLD, countryL.getFont().getSize() + 12f));
                countryL.setBorder(null);
                panel2.add(countryL);
                countryL.setBounds(20, 0, 190, 40);

                fillCountry.setBackground(Color.white);
                panel2.add(fillCountry);
                fillCountry.setBounds(20, 40, 195, 40);

                cityL.setText("City");
                cityL.setForeground(Color.black);
                cityL.setFont(cityL.getFont().deriveFont(cityL.getFont().getStyle() | Font.BOLD, cityL.getFont().getSize() + 12f));
                cityL.setBorder(null);
                panel2.add(cityL);
                cityL.setBounds(20, 80, 190, 40);

                fillCity.setBackground(Color.white);
                panel2.add(fillCity);
                fillCity.setBounds(20, 120, 195, 40);

                postalL.setText("Postal code");
                postalL.setForeground(Color.black);
                postalL.setFont(postalL.getFont().deriveFont(postalL.getFont().getStyle() | Font.BOLD, postalL.getFont().getSize() + 12f));
                postalL.setBorder(null);
                panel2.add(postalL);
                postalL.setBounds(20, 165, 190, 40);

                fillPostal.setBackground(Color.white);
                panel2.add(fillPostal);
                fillPostal.setBounds(20, 205, 195, 40);

                streetL.setText("Street");
                streetL.setForeground(Color.black);
                streetL.setFont(streetL.getFont().deriveFont(streetL.getFont().getStyle() | Font.BOLD, streetL.getFont().getSize() + 12f));
                streetL.setBorder(null);
                panel2.add(streetL);
                streetL.setBounds(20, 245, 190, 40);

                fillStreet.setBackground(Color.white);
                panel2.add(fillStreet);
                fillStreet.setBounds(20, 285, 195, 40);

                numberL.setText("Number");
                numberL.setForeground(Color.black);
                numberL.setFont(numberL.getFont().deriveFont(numberL.getFont().getStyle() | Font.BOLD, numberL.getFont().getSize() + 12f));
                numberL.setBorder(null);
                panel2.add(numberL);
                numberL.setBounds(20, 330, 190, 40);

                fillNumber.setBackground(Color.white);
                panel2.add(fillNumber);
                fillNumber.setBounds(20, 370, 195, 40);
            }
            dialogPane.add(panel2, BorderLayout.EAST);
            okButton.addActionListener(e -> {
                if(update) {
                    try {
                        System.out.println("INDEKS ADRESU (COMBOBOX): "+ (Integer) addressesWithId.get(comboBox.getSelectedIndex()));
                        Requests.updateAirport(id,fillnameL.getText(),fillCodeL.getText(),(Integer) addressesWithId.get(comboBox.getSelectedIndex()));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    if(!checkBox.isSelected()) {
                        try {
                            Requests.createAirport(fillnameL.getText(),fillCodeL.getText(), (Integer) addressesWithId.get(comboBox.getSelectedIndex()));
                            updateContent();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        try {
                            Requests.createAddress(fillCountry.getText(), fillCity.getText(),fillPostal.getText(),fillStreet.getText(), Integer.parseInt(fillNumber.getText()));
                            ResultSet rs = Requests.readTableByRequest("SELECT TOP 1 addressID FROM address ORDER BY addressID DESC");
                            while (rs.next()) {
                                int id = rs.getInt(1);
                                Requests.createAirport(fillnameL.getText(),fillCodeL.getText(),id);
                                updateContent();
                            }

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                dispose();
            });
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void setVisibility(Boolean x) {
        addressL3.setVisible(!x);
        comboBox.setVisible(!x);
        cityL.setVisible(x);
        streetL.setVisible(x);
        numberL.setVisible(x);
        postalL.setVisible(x);
        countryL.setVisible(x);
        fillCity.setVisible(x);
        fillCountry.setVisible(x);
        fillNumber.setVisible(x);
        fillPostal.setVisible(x);
        fillStreet.setVisible(x);
    }

    private void updateContent() throws SQLException {
        addressesWithId = Requests.getAddressesWihtId();
        comboBox = getComboBox();
    }

    private JComboBox getComboBox() throws SQLException {
        JComboBox comboBox = new JComboBox();
        ResultSet rs = Requests.readTableByRequest("select addressID from address");
        while (rs.next()) {
            int id = rs.getInt("addressID");
            comboBox.addItem(Requests.getStringAddress(id));
        }

        return comboBox;
    }
}
