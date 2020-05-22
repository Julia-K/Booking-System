package tableFrames;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PilotDetailsFrame extends JFrame {
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel nameL;
    private JLabel dateL;
    private JLabel airlineL;
    private JLabel fillDate;
    private JLabel fillAirline;
    private JPanel buttonBar;
    private JButton okButton;
    private JPanel panel1;

    public PilotDetailsFrame() {
        initComponents();
    }

    public PilotDetailsFrame(String name, String date, String airline) {
        initComponents();
        nameL.setText(nameL.getText() + " "+name);
        fillDate.setText(date);
        fillAirline.setText(airline);
        setVisible(true);
    }

    private void initComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        nameL = new JLabel();
        dateL = new JLabel();
        airlineL = new JLabel();
        fillDate = new JLabel();
        fillAirline = new JLabel();
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

                nameL.setText("Name:");
                nameL.setForeground(new Color(66, 122, 161));
                nameL.setFont(nameL.getFont().deriveFont(nameL.getFont().getStyle() | Font.BOLD, nameL.getFont().getSize() + 17f));
                nameL.setBorder(null);
                contentPanel.add(nameL);
                nameL.setBounds(35, 25, 455, 40);

                dateL.setText("Employment date:");
                dateL.setForeground(Color.black);
                dateL.setFont(dateL.getFont().deriveFont(dateL.getFont().getStyle() | Font.BOLD, dateL.getFont().getSize() + 17f));
                contentPanel.add(dateL);
                dateL.setBounds(35, 75, 455, 40);

                airlineL.setText("Airline:");
                airlineL.setForeground(Color.black);
                airlineL.setFont(airlineL.getFont().deriveFont(airlineL.getFont().getStyle() | Font.BOLD, airlineL.getFont().getSize() + 17f));
                contentPanel.add(airlineL);
                airlineL.setBounds(35, 175, 455, 40);

                fillDate.setForeground(Color.black);
                fillDate.setFont(fillDate.getFont().deriveFont(fillDate.getFont().getStyle() | Font.BOLD, fillDate.getFont().getSize() + 13f));
                contentPanel.add(fillDate);
                fillDate.setBounds(35, 125, 455, 40);

                fillAirline.setForeground(Color.black);
                fillAirline.setFont(fillAirline.getFont().deriveFont(fillAirline.getFont().getStyle() | Font.BOLD, fillAirline.getFont().getSize() + 13f));
                contentPanel.add(fillAirline);
                fillAirline.setBounds(35, 225, 455, 40);

                {
                    Dimension preferredSize = new Dimension();
                    preferredSize.width = 490;
                    preferredSize.height = 315;
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