package tableFrames;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FlightDetailsFrame extends JFrame {
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel fromL;
    private JLabel toL;
    private JLabel departureL;
    private JLabel arrivalL;
    private JLabel priceL;
    private JPanel buttonBar;
    private JButton okButton;
    private JPanel panel1;

    public FlightDetailsFrame() {
        initComponents();
    }

    public FlightDetailsFrame(String from, String to, String dep, String arr, String price) {
        initComponents();
        fromL.setText(fromL.getText() + " " + from);
        toL.setText(toL.getText() + " " + to);
        departureL.setText(departureL.getText() + " " + dep);
        arrivalL.setText(arrivalL.getText() + " " + arr);
        priceL.setText(priceL.getText() + " " + price);
        setVisible(true);
    }

    private void initComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        fromL = new JLabel();
        toL = new JLabel();
        departureL = new JLabel();
        arrivalL = new JLabel();
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

                fromL.setText("From:");
                fromL.setForeground(new Color(66, 122, 161));
                fromL.setFont(fromL.getFont().deriveFont(fromL.getFont().getStyle() | Font.BOLD, fromL.getFont().getSize() + 17f));
                fromL.setBorder(null);
                contentPanel.add(fromL);
                fromL.setBounds(35, 40, 455, 40);

                toL.setText("To:");
                toL.setForeground(Color.black);
                toL.setFont(toL.getFont().deriveFont(toL.getFont().getStyle() | Font.BOLD, toL.getFont().getSize() + 17f));
                contentPanel.add(toL);
                toL.setBounds(35, 90, 455, 40);

                departureL.setText("Departure:");
                departureL.setForeground(Color.black);
                departureL.setFont(departureL.getFont().deriveFont(departureL.getFont().getStyle() | Font.BOLD, departureL.getFont().getSize() + 12f));
                contentPanel.add(departureL);
                departureL.setBounds(35, 140, 455, 40);

                arrivalL.setText("Arrival:");
                arrivalL.setForeground(Color.black);
                arrivalL.setFont(arrivalL.getFont().deriveFont(arrivalL.getFont().getStyle() | Font.BOLD, arrivalL.getFont().getSize() + 12f));
                contentPanel.add(arrivalL);
                arrivalL.setBounds(35, 190, 455, 40);

                priceL.setText("Price:");
                priceL.setForeground(Color.black);
                priceL.setFont(priceL.getFont().deriveFont(priceL.getFont().getStyle() | Font.BOLD, priceL.getFont().getSize() + 17f));
                contentPanel.add(priceL);
                priceL.setBounds(35, 240, 455, 40);

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
}
