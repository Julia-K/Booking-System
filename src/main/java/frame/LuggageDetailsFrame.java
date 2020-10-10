package main.java.frame;

import main.java.commands.Requests;
import main.java.utils.StringsFormatter;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class LuggageDetailsFrame extends JFrame {
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JTextField fillName;
    private JLabel nameL;
    private JLabel priceL;
    private JLabel heightL;
    private JLabel weightL;
    private JPanel buttonBar;
    private JButton okButton;
    private JPanel panel1;
    private JLabel nameLuggageL;
    private JTextField fillPrice;
    private JTextField fillWeight;
    private JTextField fillHeight;
    private boolean update;
    private int id;

    public LuggageDetailsFrame() {
        this.update = false;
        initAddUpdateComponents();
        setVisible(true);
    }

    public LuggageDetailsFrame(Boolean update, int id, String name, float price, String height, String weight) {
        this.update = update;
        if (update) {
            initAddUpdateComponents();
            this.id = id;
            fillName.setText(name);
            fillPrice.setText(String.valueOf(price));
            fillHeight.setText(String.valueOf(height));
            fillWeight.setText(String.valueOf(weight));
        } else {
            initDetailsComponents();
            nameLuggageL.setText(name);
            priceL.setText(priceL.getText() + " " + price);
            heightL.setText(heightL.getText() + " " + height);
            weightL.setText(weightL.getText() + " " + weight);
        }
        setVisible(true);
    }

    private void initDetailsComponents() {
        nameLuggageL = new JLabel();
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        nameL = new JLabel();
        fillName = new JTextField();
        priceL = new JLabel();
        heightL = new JLabel();
        weightL = new JLabel();
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

                nameL.setText("Luggage name:");
                nameL.setForeground(new Color(66, 122, 161));
                nameL.setFont(nameL.getFont().deriveFont(nameL.getFont().getStyle() | Font.BOLD, nameL.getFont().getSize() + 17f));
                nameL.setBorder(null);
                contentPanel.add(nameL);
                nameL.setBounds(35, 40, 455, 40);

                nameLuggageL.setForeground(new Color(66, 122, 161));
                nameLuggageL.setFont(nameLuggageL.getFont().deriveFont(nameLuggageL.getFont().getStyle() | Font.BOLD, nameLuggageL.getFont().getSize() + 13f));
                contentPanel.add(nameLuggageL);
                nameLuggageL.setBounds(35, 90, 455, 40);

                priceL.setText("Price:");
                priceL.setForeground(Color.black);
                priceL.setFont(priceL.getFont().deriveFont(priceL.getFont().getStyle() | Font.BOLD, priceL.getFont().getSize() + 17f));
                contentPanel.add(priceL);
                priceL.setBounds(35, 140, 455, 40);

                heightL.setText("Height:");
                heightL.setForeground(Color.black);
                heightL.setFont(heightL.getFont().deriveFont(heightL.getFont().getStyle() | Font.BOLD, heightL.getFont().getSize() + 17f));
                contentPanel.add(heightL);
                heightL.setBounds(35, 190, 455, 40);

                weightL.setText("Weight:");
                weightL.setForeground(Color.black);
                weightL.setFont(weightL.getFont().deriveFont(weightL.getFont().getStyle() | Font.BOLD, weightL.getFont().getSize() + 17f));
                contentPanel.add(weightL);
                weightL.setBounds(35, 240, 455, 40);

                {
                    Dimension preferredSize = new Dimension();
                    preferredSize.width = 490;
                    preferredSize.height = 280;
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
                okButton.addActionListener(e -> {
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
        JLabel luggageL = new JLabel();
        nameL = new JLabel();
        priceL = new JLabel();
        fillName = new JTextField();
        fillPrice = new JTextField();
        weightL = new JLabel();
        fillWeight = new JTextField();
        heightL = new JLabel();
        fillHeight = new JTextField();
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

                luggageL.setText("Luggage");
                luggageL.setForeground(new Color(66, 122, 161));
                luggageL.setFont(luggageL.getFont().deriveFont(luggageL.getFont().getStyle() | Font.BOLD, luggageL.getFont().getSize() + 20f));
                luggageL.setBorder(null);
                contentPanel.add(luggageL);
                luggageL.setBounds(50, 20, 170, 45);

                nameL.setText("Name");
                nameL.setForeground(Color.black);
                nameL.setFont(nameL.getFont().deriveFont(nameL.getFont().getStyle() | Font.BOLD, nameL.getFont().getSize() + 15f));
                contentPanel.add(nameL);
                nameL.setBounds(50, 90, 85, 45);

                priceL.setText("Price");
                priceL.setForeground(Color.black);
                priceL.setFont(priceL.getFont().deriveFont(priceL.getFont().getStyle() | Font.BOLD, priceL.getFont().getSize() + 15f));
                contentPanel.add(priceL);
                priceL.setBounds(50, 150, 80, 45);

                fillName.setBackground(Color.white);
                StringsFormatter.setLettersWithSpaces(fillName);
                StringsFormatter.setTextFieldLength(20, fillName);
                contentPanel.add(fillName);
                fillName.setBounds(160, 90, 170, 45);

                fillPrice.setBackground(Color.white);
                StringsFormatter.setFloatPattern(3, fillPrice);
                contentPanel.add(fillPrice);
                fillPrice.setBounds(160, 150, 170, 45);

                weightL.setText("Weight");
                weightL.setForeground(Color.black);
                weightL.setFont(weightL.getFont().deriveFont(weightL.getFont().getStyle() | Font.BOLD, weightL.getFont().getSize() + 15f));
                contentPanel.add(weightL);
                weightL.setBounds(50, 270, 95, 45);

                fillWeight.setBackground(Color.white);
                StringsFormatter.setOnlyDigits(fillWeight);
                StringsFormatter.setTextFieldLength(7, fillWeight);
                contentPanel.add(fillWeight);
                fillWeight.setBounds(160, 270, 170, 45);

                heightL.setText("Height");
                heightL.setForeground(Color.black);
                heightL.setFont(heightL.getFont().deriveFont(heightL.getFont().getStyle() | Font.BOLD, heightL.getFont().getSize() + 15f));
                contentPanel.add(heightL);
                heightL.setBounds(50, 210, 90, 45);

                fillHeight.setBackground(Color.white);
                StringsFormatter.setOnlyDigits(fillHeight);
                StringsFormatter.setTextFieldLength(7, fillHeight);
                contentPanel.add(fillHeight);
                fillHeight.setBounds(160, 210, 170, 45);
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

                okButton.addActionListener(e -> {
                    if (isValidate()) {
                        try {
                            if (update) {
                                Requests.updateLuggage(id, fillName.getText(), fillPrice.getText(), fillHeight.getText(), fillWeight.getText());
                            } else {
                                Requests.createLuggage(fillName.getText(), fillPrice.getText(), fillHeight.getText(), fillWeight.getText());
                            }
                            dispose();
                        } catch (SQLServerException exception) {
                            JOptionPane.showMessageDialog(new Frame(), "Luggage name must be unique!");
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

    private boolean isValidate() {
        if (fillWeight.getText().equals("") || fillHeight.getText().equals("") || fillPrice.getText().equals("") || fillName.getText().equals("")) {
            JOptionPane.showMessageDialog(new Frame(), "All fields must be filled");
            return false;
        }
        return true;
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

    public JTextField getFillName() {
        return fillName;
    }

    public void setFillName(JTextField fillName) {
        this.fillName = fillName;
    }

    public JLabel getNameL() {
        return nameL;
    }

    public void setNameL(JLabel nameL) {
        this.nameL = nameL;
    }

    public JLabel getPriceL() {
        return priceL;
    }

    public void setPriceL(JLabel priceL) {
        this.priceL = priceL;
    }

    public JLabel getHeightL() {
        return heightL;
    }

    public void setHeightL(JLabel heightL) {
        this.heightL = heightL;
    }

    public JLabel getWeightL() {
        return weightL;
    }

    public void setWeightL(JLabel weightL) {
        this.weightL = weightL;
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

    public JLabel getNameLuggageL() {
        return nameLuggageL;
    }

    public void setNameLuggageL(JLabel nameLuggageL) {
        this.nameLuggageL = nameLuggageL;
    }

    public JTextField getFillPrice() {
        return fillPrice;
    }

    public void setFillPrice(JTextField fillPrice) {
        this.fillPrice = fillPrice;
    }

    public JTextField getFillWeight() {
        return fillWeight;
    }

    public void setFillWeight(JTextField fillWeight) {
        this.fillWeight = fillWeight;
    }

    public JTextField getFillHeight() {
        return fillHeight;
    }

    public void setFillHeight(JTextField fillHeight) {
        this.fillHeight = fillHeight;
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

