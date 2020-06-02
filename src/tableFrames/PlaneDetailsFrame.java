package tableFrames;

import allComands.Requests;
import allComands.StringsFormatter;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;

public class PlaneDetailsFrame extends JFrame {
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel planeL;
    private JLabel brandL;
    private JLabel modelL;
    private JPanel buttonBar;
    private JButton okButton;
    private JPanel panel1;
    private JTextField fillBrand;
    private JTextField fillModel;
    private boolean update;
    private int id;


    public PlaneDetailsFrame() {
        this.update = false;
        initAddUpdateComponents();
        setVisible(true);
    }

    public PlaneDetailsFrame(boolean update, int id, String brand, String model) {
        if (update) {
            initAddUpdateComponents();
            this.id = id;
            fillBrand.setText(brand);
            fillModel.setText(model);
        } else {
            initDetailComponents();
            brandL.setText(brandL.getText() + " " + brand);
            modelL.setText(modelL.getText() + " " + model);
        }
        this.update = update;
        setVisible(true);
    }

    private void initDetailComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        planeL = new JLabel();
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

    private void initAddUpdateComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        brandL = new JLabel();
        modelL = new JLabel();
        fillBrand = new JTextField();
        fillModel = new JTextField();
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

                {
                    Dimension preferredSize = new Dimension();
                    preferredSize.width =490;
                    preferredSize.height = 250;
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
                            dispose();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    } else {
                        try {
                            Requests.createPlane(fillBrand.getText(),fillModel.getText());
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
}
