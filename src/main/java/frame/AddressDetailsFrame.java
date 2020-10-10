package main.java.frame;

import main.java.commands.Requests;
import main.java.utils.StringsFormatter;

import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.border.*;

public class AddressDetailsFrame extends JFrame {
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel countryL;
    private JLabel cityL;
    private JLabel postalL;
    private JLabel streetL;
    private JLabel numL;
    private JPanel buttonBar;
    private JButton okButton;
    private JPanel panel1;
    private JTextField fillCountry;
    private JTextField fillCity;
    private JTextField fillPostal;
    private JTextField fillStreet;
    private JTextField fillNumber;
    private boolean update;
    private int id;

    public AddressDetailsFrame() throws ParseException {
        update = false;
        initAddUpdateComponents();
        setVisible(true);
    }

    public AddressDetailsFrame(Boolean update, int id, String country, String city, String postal, String street, String number) throws ParseException {
        if (update) {
            initAddUpdateComponents();
            this.id = id;
            fillCountry.setText(country);
            fillCity.setText(city);
            fillStreet.setText(street);
            fillPostal.setText(postal);
            fillNumber.setText(number);
        } else {
            initDetailComponents();
            countryL.setText(countryL.getText() + " " + country);
            cityL.setText(cityL.getText() + " " + city);
            postalL.setText(postalL.getText() + " " + postal);
            streetL.setText(streetL.getText() + " " + street);
            numL.setText(numL.getText() + " " + number);
        }
        this.update = update;
        setVisible(true);
    }

    private void initDetailComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        countryL = new JLabel();
        cityL = new JLabel();
        postalL = new JLabel();
        streetL = new JLabel();
        numL = new JLabel();
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

                countryL.setText("Country:");
                countryL.setForeground(new Color(66, 122, 161));
                countryL.setFont(countryL.getFont().deriveFont(countryL.getFont().getStyle() | Font.BOLD, countryL.getFont().getSize() + 17f));
                countryL.setBorder(null);
                contentPanel.add(countryL);
                countryL.setBounds(35, 40, 455, 40);

                cityL.setText("City:");
                cityL.setForeground(Color.black);
                cityL.setFont(cityL.getFont().deriveFont(cityL.getFont().getStyle() | Font.BOLD, cityL.getFont().getSize() + 17f));
                contentPanel.add(cityL);
                cityL.setBounds(35, 90, 455, 40);

                postalL.setText("Postal code:");
                postalL.setForeground(Color.black);
                postalL.setFont(postalL.getFont().deriveFont(postalL.getFont().getStyle() | Font.BOLD, postalL.getFont().getSize() + 17f));
                contentPanel.add(postalL);
                postalL.setBounds(35, 140, 460, 40);

                streetL.setText("Street:");
                streetL.setForeground(Color.black);
                streetL.setFont(streetL.getFont().deriveFont(streetL.getFont().getStyle() | Font.BOLD, streetL.getFont().getSize() + 17f));
                contentPanel.add(streetL);
                streetL.setBounds(35, 190, 455, 40);

                numL.setText("Number:");
                numL.setForeground(Color.black);
                numL.setFont(numL.getFont().deriveFont(numL.getFont().getStyle() | Font.BOLD, numL.getFont().getSize() + 17f));
                contentPanel.add(numL);
                numL.setBounds(35, 240, 455, 40);

                {
                    Dimension preferredSize = new Dimension();
                    preferredSize.width = 490;
                    preferredSize.height = 230;
                    contentPanel.setMinimumSize(preferredSize);
                    contentPanel.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(contentPanel, BorderLayout.EAST);

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

    private void initAddUpdateComponents() throws ParseException {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        countryL = new JLabel();
        cityL = new JLabel();
        fillCountry = new JTextField();
        postalL = new JLabel();
        fillCity = new JTextField();
        JPanel panel3 = new JPanel();
        fillPostal = StringsFormatter.getPostalCodeTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        streetL = new JLabel();
        fillStreet = new JTextField();
        JLabel numberL = new JLabel();
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

                countryL.setText("Country");
                countryL.setForeground(new Color(66, 122, 161));
                countryL.setFont(countryL.getFont().deriveFont(countryL.getFont().getStyle() | Font.BOLD, countryL.getFont().getSize() + 17f));
                countryL.setBorder(null);
                contentPanel.add(countryL);
                countryL.setBounds(35, 40, 205, 40);

                cityL.setText("City");
                cityL.setForeground(Color.black);
                cityL.setFont(cityL.getFont().deriveFont(cityL.getFont().getStyle() | Font.BOLD, cityL.getFont().getSize() + 17f));
                contentPanel.add(cityL);
                cityL.setBounds(35, 140, 185, 40);

                fillCountry.setBackground(Color.white);
                StringsFormatter.setTextFieldLength(30, fillCountry);
                StringsFormatter.setLettersWithSpaces(fillCountry);

                contentPanel.add(fillCountry);
                fillCountry.setBounds(35, 90, 195, 40);

                postalL.setText("Postal code:");
                postalL.setForeground(Color.black);
                postalL.setFont(postalL.getFont().deriveFont(postalL.getFont().getStyle() | Font.BOLD, postalL.getFont().getSize() + 17f));
                contentPanel.add(postalL);
                postalL.setBounds(35, 240, 180, 40);

                fillCity.setBackground(Color.white);
                StringsFormatter.setTextFieldLength(30, fillCity);
                StringsFormatter.setLettersWithSpaces(fillCity);
                contentPanel.add(fillCity);
                fillCity.setBounds(35, 190, 195, 40);

                {
                    panel3.setBackground(new Color(5, 102, 141));
                    panel3.setPreferredSize(new Dimension(5, 200));
                    panel3.setMinimumSize(new Dimension(5, 200));
                    panel3.setMaximumSize(new Dimension(5, 200));
                    panel3.setLayout(null);
                }
                contentPanel.add(panel3);
                panel3.setBounds(345, 40, 5, 300);

                fillPostal.setBackground(Color.white);
                contentPanel.add(fillPostal);
                fillPostal.setBounds(35, 290, 195, 40);
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

                okButton.addActionListener(e -> {
                    if (isValidate()) {
                        if (update) {
                            try {
                                Requests.updateAddress(id, Integer.parseInt(fillNumber.getText()), fillCountry.getText(), fillCity.getText(), fillPostal.getText(), fillStreet.getText());
                                dispose();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            try {
                                Requests.createAddress(fillCountry.getText(), fillCity.getText(), fillPostal.getText(), fillStreet.getText(), Integer.parseInt(fillNumber.getText()));
                                dispose();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
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

            {
                panel2.setMinimumSize(new Dimension(300, 200));
                panel2.setPreferredSize(new Dimension(300, 230));
                panel2.setBackground(new Color(235, 242, 250));
                panel2.setLayout(null);

                streetL.setText("Street:");
                streetL.setForeground(new Color(66, 122, 161));
                streetL.setFont(streetL.getFont().deriveFont(streetL.getFont().getStyle() | Font.BOLD, streetL.getFont().getSize() + 17f));
                streetL.setBorder(null);
                panel2.add(streetL);
                streetL.setBounds(5, 40, 205, 40);

                fillStreet.setBackground(Color.white);
                StringsFormatter.setTextFieldLength(30, fillStreet);
                StringsFormatter.setLettersWithSpaces(fillStreet);
                panel2.add(fillStreet);
                fillStreet.setBounds(5, 90, 195, 40);

                numberL.setText("Number:");
                numberL.setForeground(Color.black);
                numberL.setFont(numberL.getFont().deriveFont(numberL.getFont().getStyle() | Font.BOLD, numberL.getFont().getSize() + 17f));
                panel2.add(numberL);
                numberL.setBounds(5, 140, 185, 40);

                fillNumber.setBackground(Color.white);
                StringsFormatter.setOnlyDigits(fillNumber);
                StringsFormatter.setTextFieldLength(7, fillNumber);
                panel2.add(fillNumber);
                fillNumber.setBounds(5, 190, 195, 40);
            }
            dialogPane.add(panel2, BorderLayout.EAST);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    public boolean isValidate() {
        if (fillCountry.getText().equals("") || fillCity.getText().equals("")
                || fillNumber.getText().equals("") || !StringsFormatter.checkPostalCode(fillPostal.getText()) || fillStreet.getText().equals("")) {
            JOptionPane.showMessageDialog(new Frame(), "All fields must be filled!");
            return false;
        } else if (fillPostal.getText().length() != 6) {
            JOptionPane.showMessageDialog(new Frame(), "Postal code must have 5 digits. (__-___)");
            return false;
        } else {
            return true;
        }
    }

    public JPanel getDialogPane() {
        return dialogPane;
    }

    public void setDialogPane(JPanel dialogPane) {
        this.dialogPane = dialogPane;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setContentPanel(JPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    public JLabel getCountryL() {
        return countryL;
    }

    public void setCountryL(JLabel countryL) {
        this.countryL = countryL;
    }

    public JLabel getCityL() {
        return cityL;
    }

    public void setCityL(JLabel cityL) {
        this.cityL = cityL;
    }

    public JLabel getPostalL() {
        return postalL;
    }

    public void setPostalL(JLabel postalL) {
        this.postalL = postalL;
    }

    public JLabel getStreetL() {
        return streetL;
    }

    public void setStreetL(JLabel streetL) {
        this.streetL = streetL;
    }

    public JLabel getNumL() {
        return numL;
    }

    public void setNumL(JLabel numL) {
        this.numL = numL;
    }

    public JPanel getButtonBar() {
        return buttonBar;
    }

    public void setButtonBar(JPanel buttonBar) {
        this.buttonBar = buttonBar;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public void setOkButton(JButton okButton) {
        this.okButton = okButton;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JTextField getFillCountry() {
        return fillCountry;
    }

    public void setFillCountry(JTextField fillCountry) {
        this.fillCountry = fillCountry;
    }

    public JTextField getFillCity() {
        return fillCity;
    }

    public void setFillCity(JTextField fillCity) {
        this.fillCity = fillCity;
    }

    public JTextField getFillPostal() {
        return fillPostal;
    }

    public void setFillPostal(JTextField fillPostal) {
        this.fillPostal = fillPostal;
    }

    public JTextField getFillStreet() {
        return fillStreet;
    }

    public void setFillStreet(JTextField fillStreet) {
        this.fillStreet = fillStreet;
    }

    public JTextField getFillNumber() {
        return fillNumber;
    }

    public void setFillNumber(JTextField fillNumber) {
        this.fillNumber = fillNumber;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
