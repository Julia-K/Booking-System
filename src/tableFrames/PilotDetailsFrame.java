package tableFrames;

import allComands.Requests;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class PilotDetailsFrame extends JFrame {
    private LinkedHashMap<Integer,Integer> airlinesWithId;
    private boolean update;
    private int id;
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
    private JPanel contentPane;
    private JLabel firstL;
    private JLabel lastL;
    private JTextField fillFirst;
    private JTextField fillLast;
    private JComboBox comboBox;
    private JPanel panel5;
    private JComboBox dayBox;
    private JComboBox monthBox;
    private JComboBox yearBox;
    private JPanel panel;

    public PilotDetailsFrame() throws SQLException {
        update = false;
        initAddUpdateComponents();
        setVisible(true);
    }

    public PilotDetailsFrame(Boolean update, int id, String first, String last, String date, String airline, int airlineid) throws SQLException {
        if (update) {
            String[] dateArray = date.split("-");
            this.id = id;
            initAddUpdateComponents();
            fillFirst.setText(first);
            fillLast.setText(last);
            yearBox.setSelectedItem(dateArray[0]);
            monthBox.setSelectedItem(dateArray[1]);
            dayBox.setSelectedItem(dateArray[2]);
            for (Map.Entry<Integer, Integer> entry : airlinesWithId.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                if (value == airlineid) {
                    comboBox.setSelectedIndex(key);
                }
            }
        } else {
            initDetailComponents();
            nameL.setText(nameL.getText() + " "+first+ " "+last);
            fillDate.setText(date);
            fillAirline.setText(airline);
        }
        this.update = update;
        setVisible(true);
    }

    private void initDetailComponents() {
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

    private void initAddUpdateComponents() throws SQLException {
        String[] years = {"2020", "2019", "2018", "2000"};
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] days = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17"};
        dialogPane = new JPanel();
        contentPane = new JPanel();
        firstL = new JLabel();
        lastL = new JLabel();
        fillFirst = new JTextField();
        fillLast = new JTextField();
        comboBox = getComboBox();
        panel5 = new JPanel();
        airlineL = new JLabel();
        dateL = new JLabel();
        dayBox = new JComboBox();
        monthBox = new JComboBox();
        yearBox = new JComboBox();
        buttonBar = new JPanel();
        okButton = new JButton();
        panel = new JPanel();
        airlinesWithId = Requests.getAirlinesWithId();

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

                firstL.setText("First name:");
                firstL.setForeground(new Color(66, 122, 161));
                firstL.setFont(firstL.getFont().deriveFont(firstL.getFont().getStyle() | Font.BOLD, firstL.getFont().getSize() + 17f));
                firstL.setBorder(null);
                contentPane.add(firstL);
                firstL.setBounds(35, 30, 180, 40);

                lastL.setText("Last name:");
                lastL.setForeground(Color.black);
                lastL.setFont(lastL.getFont().deriveFont(lastL.getFont().getStyle() | Font.BOLD, lastL.getFont().getSize() + 17f));
                contentPane.add(lastL);
                lastL.setBounds(35, 125, 190, 40);

                fillFirst.setBackground(Color.white);
                contentPane.add(fillFirst);
                fillFirst.setBounds(35, 75, 195, 40);

                fillLast.setBackground(Color.white);
                contentPane.add(fillLast);
                fillLast.setBounds(35, 175, 195, 40);

                comboBox.setBackground(Color.white);
                contentPane.add(comboBox);
                comboBox.setBounds(315, 75, 195, 40);

                {
                    panel5.setBackground(new Color(5, 102, 141));
                    panel5.setPreferredSize(new Dimension(5, 200));
                    panel5.setMinimumSize(new Dimension(5, 200));
                    panel5.setMaximumSize(new Dimension(5, 200));
                    panel5.setLayout(null);
                }
                contentPane.add(panel5);
                panel5.setBounds(290, 35, 5, 220);

                airlineL.setText("Airline:");
                airlineL.setForeground(new Color(66, 122, 161));
                airlineL.setFont(airlineL.getFont().deriveFont(airlineL.getFont().getStyle() | Font.BOLD, airlineL.getFont().getSize() + 17f));
                airlineL.setBorder(null);
                contentPane.add(airlineL);
                airlineL.setBounds(320, 30, 110, 40);

                dateL.setText("Employment date:");
                dateL.setForeground(Color.black);
                dateL.setFont(dateL.getFont().deriveFont(dateL.getFont().getStyle() | Font.BOLD, dateL.getFont().getSize() + 10f));
                contentPane.add(dateL);
                dateL.setBounds(35, 225, 190, 40);

                dayBox.setBackground(Color.white);
                dayBox.setForeground(Color.black);
                contentPane.add(dayBox);
                dayBox.setBounds(new Rectangle(new Point(35, 275), dayBox.getPreferredSize()));

                monthBox.setBackground(Color.white);
                monthBox.setForeground(Color.black);
                contentPane.add(monthBox);
                monthBox.setBounds(115, 275, 80, 30);

                yearBox.setBackground(Color.white);
                yearBox.setForeground(Color.black);
                contentPane.add(yearBox);
                yearBox.setBounds(195, 275, 80, 30);

                yearBox.setModel(new DefaultComboBoxModel<>(years));
                monthBox.setModel(new DefaultComboBoxModel<>(months));
                dayBox.setModel(new DefaultComboBoxModel<>(days));

                {
                    Dimension preferredSize = new Dimension();
                    preferredSize.width =510;
                    preferredSize.height =305;
                    contentPane.setMinimumSize(preferredSize);
                    contentPane.setPreferredSize(preferredSize);
                }
            }
            dialogPane.add(contentPane, BorderLayout.CENTER);

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
                panel.setPreferredSize(new Dimension(160, 200));
                panel.setBackground(new Color(5, 102, 141));
                panel.setForeground(new Color(5, 102, 141));
                panel.setMaximumSize(new Dimension(160, 200));
                panel.setMinimumSize(new Dimension(160, 200));
                panel.setLayout(null);
            }

            okButton.addActionListener(e -> {
                String date =yearBox.getSelectedItem() + "-" + monthBox.getSelectedItem()+ "-" + dayBox.getSelectedItem();
                if (update) {
                    try {
                        System.out.println("ID PILOTAL " + id);
                        Requests.updatePilot(id,fillFirst.getText(),fillLast.getText(),date,(Integer)airlinesWithId.get(comboBox.getSelectedIndex()));
                        updateContent();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    try {
                        Requests.createPilot(fillFirst.getText(),fillLast.getText(),date,(Integer)airlinesWithId.get(comboBox.getSelectedIndex()));
                        updateContent();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                dispose();
            });

            dialogPane.add(panel, BorderLayout.WEST);
        }
        contentPane2.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void updateContent() throws SQLException {
        airlinesWithId = Requests.getAirlinesWithId();
        comboBox = getComboBox();
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
}