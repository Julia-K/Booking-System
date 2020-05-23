package tableFrames;

import allComands.Requests;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LuggageDetailsFrame extends JFrame {
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel nameL;
    private JLabel fillName;
    private JLabel priceL;
    private JLabel heightL;
    private JLabel weightL;
    private JPanel buttonBar;
    private JButton okButton;
    private JPanel panel1;

    public LuggageDetailsFrame() {
        initComponents();
    }

    public LuggageDetailsFrame(String name, String price, String height, String weight) {
        initComponents();
        fillName.setText(name);
        priceL.setText(priceL.getText() + " " + price);
        heightL.setText(heightL.getText() + " " + height);
        weightL.setText(weightL.getText() + " "+ weight);
        setVisible(true);
    }

    private void initComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        nameL = new JLabel();
        fillName = new JLabel();
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

                fillName.setForeground(new Color(66, 122, 161));
                fillName.setFont(fillName.getFont().deriveFont(fillName.getFont().getStyle() | Font.BOLD, fillName.getFont().getSize() + 13f));
                contentPanel.add(fillName);
                fillName.setBounds(35, 90, 455, 40);

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
                okButton.addActionListener(e->{
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

    private void initFields() throws SQLException {
        ResultSet rs = Requests.readByTableName("luggage");

    }

}

