package tableFrames;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class AirlineDetailsFrame extends JFrame {
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel airlineL;
    private JLabel fillAirline;
    private JLabel listL;
    private JScrollPane scrollPane1;
    private JList<String> fillList;
    private JPanel buttonBar;
    private JButton okButton;
    private JPanel panel1;
    private DefaultListModel<String> model;

    public AirlineDetailsFrame() {
        initComponents();
        setVisible(true);
    }

    public AirlineDetailsFrame(String name, String code, String[] planes) {
        initComponents();
        fillAirline.setText(name + " (" + code+")");
        for (int i = 0; i < planes.length; i++) {
            model.add(i, planes[i]);
        }
        setVisible(true);
    }

    private void initComponents() {
        model = new DefaultListModel<String>();
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        airlineL = new JLabel();
        fillAirline = new JLabel();
        listL = new JLabel();
        scrollPane1 = new JScrollPane();
        fillList = new JList<>(model);
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
}
