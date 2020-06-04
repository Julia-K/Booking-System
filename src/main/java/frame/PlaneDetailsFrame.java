package main.java.frame;

import main.java.commands.Requests;
import main.java.utils.StringsFormatter;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import javax.swing.*;
import javax.swing.border.*;

public class PlaneDetailsFrame extends JFrame {
    private LinkedHashMap<Integer,Integer> airlinesWithId;
    private JTextField fillQuantity;
    private JComboBox comboBox;
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel brandL;
    private JLabel modelL;
    private JPanel buttonBar;
    private JButton okButton;
    private JPanel panel1;
    private JTextField fillBrand;
    private JTextField fillModel;
    private boolean update;
    private int id;


    public PlaneDetailsFrame() throws SQLException {
        this.update = false;
        initAddUpdateComponents();
        setVisible(true);
    }

    public PlaneDetailsFrame(boolean update, int id, String brand, String model) throws SQLException {
        this.update = update;
        if (update) {
            this.id = id;
            initAddUpdateComponents();
            fillBrand.setText(brand);
            fillModel.setText(model);
        } else {
            initDetailComponents();
            brandL.setText(brandL.getText() + " " + brand);
            modelL.setText(modelL.getText() + " " + model);
        }
        setVisible(true);
    }

    private void initDetailComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        JLabel planeL = new JLabel();
        brandL = new JLabel();
        modelL = new JLabel();
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

                planeL.setText("Plane");
                planeL.setForeground(new Color(66, 122, 161));
                planeL.setFont(planeL.getFont().deriveFont(planeL.getFont().getStyle() | Font.BOLD, planeL.getFont().getSize() + 20f));
                planeL.setBorder(null);
                contentPanel.add(planeL);
                planeL.setBounds(35, 75, 455, 40);

                brandL.setText("Brand:");
                brandL.setForeground(Color.black);
                brandL.setFont(brandL.getFont().deriveFont(brandL.getFont().getStyle() | Font.BOLD, brandL.getFont().getSize() + 12f));
                contentPanel.add(brandL);
                brandL.setBounds(35, 130, 455, 40);

                modelL.setText("Model:");
                modelL.setForeground(Color.black);
                modelL.setFont(modelL.getFont().deriveFont(modelL.getFont().getStyle() | Font.BOLD, modelL.getFont().getSize() + 12f));
                contentPanel.add(modelL);
                modelL.setBounds(35, 175, 455, 40);

                {
                    Dimension preferredSize = new Dimension();
                    preferredSize.width = 490;
                    preferredSize.height = 215;
                    contentPanel.setMinimumSize(preferredSize);
                    contentPanel.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(contentPanel, BorderLayout. EAST);

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

    private void initAddUpdateComponents() throws SQLException {
        airlinesWithId = new LinkedHashMap<>();
        JLabel quantity = new JLabel();
        fillQuantity = new JTextField();
        JLabel airlineL = new JLabel();
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        JPanel panel5 = new JPanel();
        brandL = new JLabel();
        modelL = new JLabel();
        fillBrand = new JTextField();
        fillModel = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        panel1 = new JPanel();
        comboBox = new JComboBox();
        if(update) {
            updateContent();
            comboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    try {
                        int airlineid = (Integer)airlinesWithId.get(comboBox.getSelectedIndex());
                        ResultSet rs = Requests.readTableByRequest("select planes_quantity from plane_airline\n" +
                                "where plane_id = " + id + " and airline_id = " + airlineid);
                        rs.next();
                        fillQuantity.setText(rs.getString(1));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            });
            comboBox.setSelectedItem(0);
        } else {
            updateContent();
        }

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

                brandL.setText("Brand:");
                brandL.setForeground(new Color(66, 122, 161));
                brandL.setFont(brandL.getFont().deriveFont(brandL.getFont().getStyle() | Font.BOLD, brandL.getFont().getSize() + 17f));
                brandL.setBorder(null);
                contentPanel.add(brandL);
                brandL.setBounds(35, 60, 455, 40);

                modelL.setText("Model:");
                modelL.setForeground(Color.black);
                modelL.setFont(modelL.getFont().deriveFont(modelL.getFont().getStyle() | Font.BOLD, modelL.getFont().getSize() + 17f));
                contentPanel.add(modelL);
                modelL.setBounds(35, 160, 225, 40);

                fillBrand.setBackground(Color.white);
                StringsFormatter.setTextFieldLength(30, fillBrand);
                contentPanel.add(fillBrand);
                fillBrand.setBounds(35, 110, 195, 40);

                fillModel.setBackground(Color.white);
                StringsFormatter.setTextFieldLength(10, fillModel);
                contentPanel.add(fillModel);
                fillModel.setBounds(35, 210, 195, 40);

                panel5.setBackground(new Color(5, 102, 141));
                panel5.setPreferredSize(new Dimension(5, 200));
                panel5.setMinimumSize(new Dimension(5, 200));
                panel5.setMaximumSize(new Dimension(5, 200));
                panel5.setLayout(null);
                contentPanel.add(panel5);
                panel5.setBounds(250, 55, 5, 220);

                contentPanel.setMinimumSize(new Dimension(490,250));
                contentPanel.setPreferredSize(new Dimension(490,250));

                comboBox.setBackground(Color.white);
                contentPanel.add(comboBox);
                comboBox.setBounds(275, 210, 195, 40);

                airlineL.setText("Airline:");
                airlineL.setForeground(Color.black);
                airlineL.setFont(airlineL.getFont().deriveFont(airlineL.getFont().getStyle() | Font.BOLD, airlineL.getFont().getSize() + 17f));
                contentPanel.add(airlineL);
                airlineL.setBounds(280, 165, 110, 40);

                quantity.setText("Quantity");
                quantity.setForeground(Color.black);
                quantity.setFont(quantity.getFont().deriveFont(quantity.getFont().getStyle() | Font.BOLD, quantity.getFont().getSize() + 17f));
                contentPanel.add(quantity);
                quantity.setBounds(275, 60, 150, 40);

                fillQuantity.setBackground(Color.white);
                StringsFormatter.setOnlyDigits(fillQuantity);
                StringsFormatter.setTextFieldLength(2, fillQuantity);
                contentPanel.add(fillQuantity);
                fillQuantity.setBounds(275, 110, 195, 40);

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

            okButton.addActionListener(e-> {
                if (isValidate()) {
                    if (update) {
                        try {
                            Requests.updatePlane(id, fillBrand.getText(), fillModel.getText());
                            Requests.updatePlaneAirline(id,(Integer)airlinesWithId.get(comboBox.getSelectedIndex()), Integer.parseInt(fillQuantity.getText()));
                            updateContent();
                            dispose();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    } else {
                        try {
                            Requests.createPlane(fillBrand.getText(),fillModel.getText());
                            ResultSet rs = Requests.readTableByRequest("SELECT TOP 1 * FROM plane ORDER BY planeID DESC");
                            rs.next();
                            int planeid = rs.getInt(1);
                            Requests.createPlaneAirplane(planeid,(Integer)airlinesWithId.get(comboBox.getSelectedIndex()),
                                    Integer.parseInt(fillQuantity.getText()));

                            updateContent();
                            dispose();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
            });
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    public boolean isValidate() {
        if(fillBrand.getText().equals("") || fillModel.getText().equals("")) {
            JOptionPane.showMessageDialog(new Frame(),"All fields must be filled!");
            return false;
        } else {
            return true;
        }
    }
    private void updateContent() throws SQLException {
        if(update) {
            airlinesWithId = Requests.getAirlinesWithId(id);
            comboBox = getSelectedComboBox();
        } else {
            airlinesWithId = Requests.getAirlinesWithId();
            comboBox = getComboBox();
        }
    }

    private JComboBox getComboBox() throws SQLException {
        JComboBox x = new JComboBox();
        ResultSet rs = Requests.readTableByRequest("select airlineID from airline");
        while (rs.next()) {
            int id = rs.getInt("airlineID");
            x.addItem(Requests.getStringAirline(id));
        }
        return x;
    }

    private JComboBox getSelectedComboBox() throws SQLException {
        boolean first = true;
        JComboBox x = new JComboBox();
        ResultSet rs = Requests.readTableByRequest("select * from plane_airline\n" +
                "where plane_id = '"+id+"'");
        while (rs.next()) {
            if(first) {
                fillQuantity.setText(rs.getString(3));
                first = false;
            }
            int airlineid = rs.getInt(2);
            comboBox.addItem(Requests.getStringAirline(airlineid));
        }
        return comboBox;
    }

    public LinkedHashMap<Integer, Integer> getAirlinesWithId() {
        return airlinesWithId;
    }

    public void setAirlinesWithId(LinkedHashMap<Integer, Integer> airlinesWithId) {
        this.airlinesWithId = airlinesWithId;
    }

    public JTextField getFillQuantity() {
        return fillQuantity;
    }

    public void setFillQuantity(JTextField fillQuantity) {
        this.fillQuantity = fillQuantity;
    }

    public void setComboBox(JComboBox comboBox) {
        this.comboBox = comboBox;
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

    public JLabel getBrandL() {
        return brandL;
    }

    public void setBrandL(JLabel brandL) {
        this.brandL = brandL;
    }

    public JLabel getModelL() {
        return modelL;
    }

    public void setModelL(JLabel modelL) {
        this.modelL = modelL;
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

    public JTextField getFillBrand() {
        return fillBrand;
    }

    public void setFillBrand(JTextField fillBrand) {
        this.fillBrand = fillBrand;
    }

    public JTextField getFillModel() {
        return fillModel;
    }

    public void setFillModel(JTextField fillModel) {
        this.fillModel = fillModel;
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
