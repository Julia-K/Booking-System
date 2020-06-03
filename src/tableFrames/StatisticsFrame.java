package tableFrames;

import allComands.Requests;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
                leftPanel.setBackground(new Color(5, 102, 141));
                leftPanel.setForeground(new Color(5, 102, 141));
                leftPanel.setMaximumSize(new Dimension(160, 150));
                leftPanel.setMinimumSize(new Dimension(160, 200));
                leftPanel.setLayout(null);

                classButton.setText("Classes");
                leftPanel.add(classButton);
                classButton.setBounds(45, 50, 120, 50);
                classButton.addActionListener(e-> {
                    try {
                        table = Requests.showClassStatistic();
                        yearsSpinner.setVisible(false);
                        chooseYearL.setVisible(false);
                        setTableDesign();
                        reload();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                });

                pilotButton.setText("Pilots");
                leftPanel.add(pilotButton);
                pilotButton.setBounds(45, 125, 120, 55);
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
                clientButton.setBounds(45, 205, 120, 50);
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
                yearsButton.setBounds(45, 280, 120, 55);
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
                monthsButton.setBounds(45, 360, 120, 55);
            }
            dialogPane.add(leftPanel, BorderLayout.WEST);

            {
                rightPanel.setBackground(new Color(235, 242, 250));
                rightPanel.setPreferredSize(new Dimension(428, 600));
                rightPanel.setLayout(new BorderLayout());
                rightPanel.setBorder(new EmptyBorder(0,12,12,0));

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
                information.setFont(information.getFont().deriveFont(information.getFont().getStyle() | Font.BOLD, information.getFont().getSize() + 10f));
                information.setHorizontalAlignment(SwingConstants.CENTER);
                information.setForeground(Color.black);
                information.setMaximumSize(new Dimension(634, 100));
                information.setMinimumSize(new Dimension(634, 100));
                information.setPreferredSize(new Dimension(634, 100));
                rightPanel.add(information, BorderLayout.NORTH);
            }
            dialogPane.add(rightPanel, BorderLayout.CENTER);

            {
                tiopPanel.setMaximumSize(new Dimension(32767, 80));
                tiopPanel.setMinimumSize(new Dimension(0, 70));
                tiopPanel.setPreferredSize(new Dimension(0, 80));
                tiopPanel.setLayout(null);

                statistic.setText("Statistics");
                statistic.setMaximumSize(new Dimension(1200, 50));
                statistic.setMinimumSize(new Dimension(33, 50));
                statistic.setPreferredSize(new Dimension(150, 50));
                statistic.setDisplayedMnemonicIndex(0);
                statistic.setAlignmentX(0.5F);
                statistic.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
                statistic.setHorizontalAlignment(SwingConstants.CENTER);
                statistic.setBorder(new EmptyBorder(5, 10, 5, 5));
                statistic.setForeground(Color.white);
                tiopPanel.add(statistic);
                statistic.setBounds(35, 10, 150, 50);
                tiopPanel.add(yearsSpinner);
                yearsSpinner.setBounds(340, 25, 80, 30);
                yearsSpinner.setVisible(false);

                chooseYearL.setText("Choose year: ");
                chooseYearL.setForeground(Color.white);
                chooseYearL.setFont(chooseYearL.getFont().deriveFont(chooseYearL.getFont().getSize() + 5f));
                tiopPanel.add(chooseYearL);
                chooseYearL.setBounds(215, 25, 120, 25);
                chooseYearL.setVisible(false);

                goButton.setText("Go to manage");
                tiopPanel.add(goButton);
                goButton.setBounds(720,25,120,25);
                goButton.addActionListener(e-> {
                    //ManageWindow.main(new String[]);
                    dispose();
                });

                Dimension preferredSize = new Dimension();
                preferredSize.width =870;
                preferredSize.height = 80;
                tiopPanel.setMinimumSize(preferredSize);
                tiopPanel.setPreferredSize(preferredSize);
                tiopPanel.setBackground(Color.black);
            }
            dialogPane.add(tiopPanel, BorderLayout.NORTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
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
        rightPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        rightPanel.add(information, BorderLayout.NORTH);
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void setTableDesign() {
        table.setFont(new Font("Lucida Sans", Font.PLAIN, 25));
        table.setRowHeight(30);
        table.setBackground(new Color(235, 242, 250));
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(66, 122, 161));
        tableHeader.setForeground(Color.white);
    }
}

