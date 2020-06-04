package main.java.frame;

import main.java.commands.Requests;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.SQLException;
import java.util.Calendar;

public class StatisticsFrame extends JFrame{
    private JPanel rightPanel;
    private JTable table;
    private JScrollPane scrollTable;
    private JLabel information;
    private static StatisticsFrame statisticFrame;

    public StatisticsFrame() throws SQLException {
        initComponents();
    }

    private void initComponents() throws SQLException {
        JPanel dialogPane = new JPanel();
        JPanel leftPanel = new JPanel();
        JButton classButton = new JButton();
        JButton pilotButton = new JButton();
        JButton clientButton = new JButton();
        JButton yearsButton = new JButton();
        JButton monthsButton = new JButton();
        rightPanel = new JPanel();
        scrollTable = new JScrollPane();
        table = Requests.showClassStatistic();
        information = new JLabel();
        JPanel tiopPanel = new JPanel();
        JLabel statistic = new JLabel();
        JSpinner yearsSpinner = new JSpinner();
        JLabel chooseYearL = new JLabel();
        JButton goButton = new JButton();

        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(900, 600));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBackground(new Color(235, 242, 250));
            dialogPane.setMaximumSize(new Dimension(100, 100));
            dialogPane.setMinimumSize(new Dimension(100, 100));
            dialogPane.setPreferredSize(new Dimension(100, 100));
            dialogPane.setLayout(new BorderLayout());

            {
                leftPanel.setPreferredSize(new Dimension(210, 150));
                leftPanel.setBackground(new Color(235, 242, 250));
                leftPanel.setMaximumSize(new Dimension(160, 150));
                leftPanel.setLayout(null);
                leftPanel.setBorder(BorderFactory.createMatteBorder(0,10,10,10,new Color(4, 72, 98)));
                //rightPanel.setBorder(BorderFactory.createMatteBorder(0,0,10,10,new Color(4, 72, 98)));

                classButton.setText("Classes");
                classButton.setForeground(Color.white);
                classButton.setBackground(new Color(5, 102, 141));
                classButton.setBounds(45, 50, 120, 45);
                leftPanel.add(classButton);
                classButton.addActionListener(e-> {
                    try {
                        table = Requests.showClassStatistic();
                        yearsSpinner.setVisible(false);
                        chooseYearL.setVisible(false);
                        information.setText("Percentage distribution of class selections by customers");
                        setTableDesign();
                        reload();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                });

                pilotButton.setText("Pilots");
                leftPanel.add(pilotButton);
                pilotButton.setForeground(Color.white);
                pilotButton.setBackground(new Color(5, 102, 141));
                pilotButton.setBounds(45, 125, 120, 45);
                pilotButton.addActionListener(e-> {
                    try {
                        table = Requests.showPilotStatistics();
                        yearsSpinner.setVisible(false);
                        chooseYearL.setVisible(false);
                        information.setText("The length of pilots' work since the beginning of employment");
                        setTableDesign();
                        reload();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                });

                clientButton.setText("Clients");
                leftPanel.add(clientButton);
                clientButton.setBounds(45, 200, 120, 45);
                clientButton.setForeground(Color.white);
                clientButton.setBackground(new Color(5, 102, 141));
                clientButton.addActionListener(e-> {
                    try {
                        table = Requests.showClientStatistics();
                        yearsSpinner.setVisible(false);
                        chooseYearL.setVisible(false);
                        information.setText("Number of tickets booked by individual customers");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    setTableDesign();
                    reload();
                });

                yearsButton.setText("Tickets-Years");
                leftPanel.add(yearsButton);
                yearsButton.setForeground(Color.white);
                yearsButton.setBackground(new Color(5, 102, 141));
                yearsButton.setBounds(45, 275, 120, 45);
                yearsButton.addActionListener(e-> {
                    try {
                        table = Requests.showYearsStatistics();
                        information.setText("Number of tickets reserved over the years");
                        setTableDesign();
                        yearsSpinner.setVisible(false);
                        chooseYearL.setVisible(false);
                        reload();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                });

                monthsButton.setText("Tickets-Months");
                monthsButton.setForeground(Color.white);
                monthsButton.setBackground(new Color(5, 102, 141));
                monthsButton.addActionListener(e-> {
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    SpinnerModel yearModel = new SpinnerNumberModel(currentYear,currentYear - 100,currentYear + 100,1);
                    JSpinner.NumberEditor editor = new JSpinner.NumberEditor(yearsSpinner, "#");
                    yearsSpinner.setValue(Integer.valueOf("2020"));
                    yearsSpinner.setEditor(editor);
                    yearsSpinner.setModel(yearModel);
                    yearsSpinner.setVisible(true);
                    chooseYearL.setVisible(true);
                    information.setText("The number of reserved tickets in particular months of selected years");
                    information.setFont(new Font("Roboto",Font.PLAIN,19));
                    try {
                        table = Requests.showMonthStatistics("2020");
                        setTableDesign();
                        reload();
                        yearsSpinner.addChangeListener(x-> {
                            try {
                                table = Requests.showMonthStatistics(String.valueOf(yearsSpinner.getValue()));
                                setTableDesign();
                                reload();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        });
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                });
                leftPanel.add(monthsButton);
                monthsButton.setBounds(45, 350, 120, 45);
            }
            dialogPane.add(leftPanel, BorderLayout.WEST);

            {
                rightPanel.setBackground(new Color(235, 242, 250));
                rightPanel.setPreferredSize(new Dimension(428, 600));
                rightPanel.setLayout(new BorderLayout());
                rightPanel.setBorder(new EmptyBorder(0,12,12,12));

                {
                    table.setPreferredScrollableViewportSize(new Dimension(500, 350));
                    table.setBackground(new Color(235, 242, 250));
                    table.setForeground(Color.black);
                    table = Requests.showClassStatistic();
                    scrollTable.setViewportView(table);

                }
                rightPanel.add(scrollTable, BorderLayout.CENTER);
                reload();
                setTableDesign();
                information.setText("Percentage distribution of class selections by customers");
                information.setFont(new Font("Roboto",Font.PLAIN,20));
                //information.setFont(information.getFont().deriveFont(information.getFont().getStyle() | Font.BOLD, information.getFont().getSize() + 10f));
                information.setHorizontalAlignment(SwingConstants.CENTER);
                information.setForeground(Color.black);
                information.setMaximumSize(new Dimension(634, 100));
                information.setMinimumSize(new Dimension(634, 100));
                information.setPreferredSize(new Dimension(634, 100));
                rightPanel.add(information, BorderLayout.NORTH);
            }
            dialogPane.add(rightPanel, BorderLayout.CENTER);

            {
                tiopPanel.setMinimumSize(new Dimension(0, 70));
                tiopPanel.setPreferredSize(new Dimension(0, 80));
                tiopPanel.setLayout(null);

                statistic.setText("Statistics");
                statistic.setMinimumSize(new Dimension(33, 50));
                statistic.setPreferredSize(new Dimension(150, 50));
                statistic.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
                statistic.setHorizontalAlignment(SwingConstants.CENTER);
                statistic.setBorder(new EmptyBorder(5, 10, 5, 5));
                statistic.setForeground(Color.white);
                tiopPanel.add(statistic);
                statistic.setBounds(20, 10, 175, 60);
                tiopPanel.add(yearsSpinner);
                yearsSpinner.setBounds(320, 25, 80, 30);
                yearsSpinner.setVisible(false);

                chooseYearL.setText("Choose year: ");
                chooseYearL.setForeground(Color.white);
                chooseYearL.setFont(new Font("Roboto", Font.BOLD, 13));
                tiopPanel.add(chooseYearL);
                chooseYearL.setBounds(220, 30, 90, 25);
                chooseYearL.setVisible(false);

                goButton.setText("Go to manage \uD83E\uDC46");
                tiopPanel.add(goButton);
                goButton.setBounds(690, 20, 150, 35);
                goButton.setBackground(new Color(235, 242, 250));
                goButton.addActionListener(e-> {
                    try {
                        new ManageWindow().setVisible(true);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    dispose();
                });
                tiopPanel.setMinimumSize(new Dimension(870,80));
                tiopPanel.setPreferredSize(new Dimension(870,80));
                tiopPanel.setBackground(new Color(4, 72, 98));
            }
            dialogPane.add(tiopPanel, BorderLayout.NORTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater((() -> {
            try {
                statisticFrame = new StatisticsFrame();
                statisticFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }

    public void reload() {
        rightPanel.removeAll();
        JScrollPane x = new JScrollPane(table);
        x.getViewport().setBackground(new Color(235, 242, 250));
        rightPanel.add(x, BorderLayout.CENTER);
        rightPanel.add(information, BorderLayout.NORTH);
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void setTableDesign() {
        table.setFont(new Font("Roboto", Font.PLAIN, 15));
        table.setRowHeight(30);
        table.setBackground(new Color(235, 242, 250));
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(5, 102, 141));
        tableHeader.setForeground(Color.white);
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public void setRightPanel(JPanel rightPanel) {
        this.rightPanel = rightPanel;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JScrollPane getScrollTable() {
        return scrollTable;
    }

    public void setScrollTable(JScrollPane scrollTable) {
        this.scrollTable = scrollTable;
    }

    public JLabel getInformation() {
        return information;
    }

    public void setInformation(JLabel information) {
        this.information = information;
    }

    public static StatisticsFrame getStatisticFrame() {
        return statisticFrame;
    }

    public static void setStatisticFrame(StatisticsFrame statisticFrame) {
        StatisticsFrame.statisticFrame = statisticFrame;
    }
}

