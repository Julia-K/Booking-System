package tableFrames;

import allComands.Requests;
import allComands.StringsFormatter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

public class SelectFlight extends JFrame {
    private LinkedHashMap airportsWithId;
    private JComboBox fromCombo;
    private JComboBox toCombo;
    private MyOwnDatePicker dateFrom;
    private MyOwnDatePicker dateTo;
    private JTextField fillPriceFrom;
    private JTextField fillPriceTo;
    private JPanel panelForTable;
    private JScrollPane scrollPane;
    private JTable table;
    private BookingDetailsFrame bdf;
    private int flight;
    private boolean update;

    public SelectFlight(boolean update, BookingDetailsFrame bdf) throws SQLException {
        this.update = update;
        this.bdf = bdf;
        this.flight = bdf.getFlightId();
        Date data = java.util.Calendar.getInstance().getTime();
        String s = new SimpleDateFormat("yyyy-MM-dd").format(data);
        dateFrom = new MyOwnDatePicker(s);
        dateTo = new MyOwnDatePicker(s);
        initComponents();
        setTableDesign();
    }

    private void initComponents() throws SQLException {
        JPanel dialogPane = new JPanel();
        JPanel contentPanel = new JPanel();
        airportsWithId = Requests.getAirportsWithId();
        fromCombo = getFilledComboBox();
        toCombo = getFilledComboBox();
        JLabel searchL = new JLabel();
        JLabel fromL = new JLabel();
        fillPriceFrom = new JTextField();
        fillPriceTo = new JTextField();
        JLabel toL = new JLabel();
        JLabel dateFromL = new JLabel();
        JLabel dateToL = new JLabel();
        JLabel priceFromL = new JLabel();
        JLabel priceToL = new JLabel();
        JButton searchButton = new JButton();
        JButton searchAllButton = new JButton();
        JLabel infoLabel = new JLabel();
        panelForTable = new JPanel();
        scrollPane = new JScrollPane();
        table = new JTable();
        JPanel buttonBar = new JPanel();
        JButton okButton = new JButton();
        JPanel panel1 = new JPanel();

        setResizable(false);
        setMinimumSize(new Dimension(1100, 700));
        setBackground(new Color(235, 242, 250));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBackground(new Color(235, 242, 250));
            dialogPane.setMaximumSize(new Dimension(850, 510));
            dialogPane.setMinimumSize(new Dimension(850, 510));
            dialogPane.setPreferredSize(new Dimension(850, 510));
            dialogPane.setLayout(new BorderLayout());

            {
                contentPanel.setBackground(new Color(235, 242, 250));
                contentPanel.setMaximumSize(new Dimension(200, 230));
                contentPanel.setMinimumSize(new Dimension(200, 230));
                contentPanel.setPreferredSize(new Dimension(200, 230));
                contentPanel.setLayout(null);

                fromCombo.setBackground(new Color(235, 242, 250));
                fromCombo.setForeground(Color.black);
                contentPanel.add(fromCombo);
                fromCombo.setBounds(20, 95, 210, 26);

                toCombo.setBackground(new Color(235, 242, 250));
                toCombo.setForeground(Color.black);
                contentPanel.add(toCombo);
                toCombo.setBounds(235, 95, 210, 26);

                searchL.setText("Search your flight here:");
                searchL.setFont(searchL.getFont().deriveFont(searchL.getFont().getStyle() | Font.BOLD, searchL.getFont().getSize() + 9f));
                searchL.setBackground(new Color(235, 242, 250));
                searchL.setForeground(Color.black);
                contentPanel.add(searchL);
                searchL.setBounds(410, 15, 245, 40);

                fromL.setText("From");
                fromL.setBackground(new Color(235, 242, 250));
                fromL.setForeground(Color.black);
                fromL.setFont(fromL.getFont().deriveFont(fromL.getFont().getStyle() | Font.BOLD, fromL.getFont().getSize() + 3f));
                contentPanel.add(fromL);
                fromL.setBounds(40, 70, 55, 25);

                dateFrom.addTo(contentPanel);
                dateFrom.setBackground(new Color(235, 242, 250));
                dateFrom.setBounds(450, 95, 210, 50);

                dateTo.addTo(contentPanel);
                dateTo.setBounds(665, 95, 210, 26);

                fillPriceFrom.setForeground(Color.black);
                fillPriceFrom.setBackground(new Color(235, 242, 250));
                StringsFormatter.setFloatPattern(5,fillPriceFrom);
                contentPanel.add(fillPriceFrom);
                fillPriceFrom.setBounds(885, 95, 70, 26);

                fillPriceTo.setForeground(Color.black);
                fillPriceTo.setBackground(new Color(235, 242, 250));
                StringsFormatter.setFloatPattern(5,fillPriceTo);
                contentPanel.add(fillPriceTo);
                fillPriceTo.setBounds(960, 95, 70, 26);

                toL.setText("To");
                toL.setBackground(new Color(235, 242, 250));
                toL.setForeground(Color.black);
                toL.setFont(toL.getFont().deriveFont(toL.getFont().getStyle() | Font.BOLD, toL.getFont().getSize() + 3f));
                contentPanel.add(toL);
                toL.setBounds(250, 70, 55, 25);

                dateFromL.setText("Date from");
                dateFromL.setBackground(new Color(235, 242, 250));
                dateFromL.setForeground(Color.black);
                dateFromL.setFont(dateFromL.getFont().deriveFont(dateFromL.getFont().getStyle() | Font.BOLD, dateFromL.getFont().getSize() + 3f));
                contentPanel.add(dateFromL);
                dateFromL.setBounds(460, 70, 105, 25);

                dateToL.setText("Date to");
                dateToL.setBackground(new Color(235, 242, 250));
                dateToL.setForeground(Color.black);
                dateToL.setFont(dateToL.getFont().deriveFont(dateToL.getFont().getStyle() | Font.BOLD, dateToL.getFont().getSize() + 3f));
                contentPanel.add(dateToL);
                dateToL.setBounds(670, 70, 100, 25);

                priceFromL.setText("Price from");
                priceFromL.setBackground(new Color(235, 242, 250));
                priceFromL.setForeground(Color.black);
                priceFromL.setFont(priceFromL.getFont().deriveFont(priceFromL.getFont().getStyle() | Font.BOLD, priceFromL.getFont().getSize() + 2f));
                contentPanel.add(priceFromL);
                priceFromL.setBounds(880, 70, 80, 25);

                priceToL.setBackground(new Color(235, 242, 250));
                priceToL.setForeground(Color.black);
                priceToL.setFont(priceToL.getFont().deriveFont(priceToL.getFont().getStyle() | Font.BOLD, priceToL.getFont().getSize() + 2f));
                priceToL.setText("Price to");
                contentPanel.add(priceToL);
                priceToL.setBounds(965, 70, 65, 25);

                searchButton.setText("Search");
                searchButton.setBackground(new Color(5, 102, 141));
                searchButton.setForeground(Color.white);
                contentPanel.add(searchButton);
                searchButton.setBounds(895, 165, 130, 40);

                searchButton.addActionListener(e -> {
                    String dateFromString = dateFrom.getDate();
                    String dateToString = dateTo.getDate();
                    float pricefrom;
                    float priceTo;
                    int depId = (Integer) airportsWithId.get(fromCombo.getSelectedIndex());
                    int arrId = (Integer) airportsWithId.get(toCombo.getSelectedIndex());
                    try {
                        if (fillPriceFrom.getText().equals("")) {
                            if (fillPriceTo.getText().equals("")) {
                                table = Requests.readTableAfterSearchingWithoutPrice(dateFromString, dateToString, depId, arrId);
                            } else {
                                priceTo = Float.parseFloat(fillPriceTo.getText());
                                table = Requests.readTableAfterSearchingWithOnePrice(false, dateFromString, dateToString, priceTo, depId, arrId);
                            }
                            setTableDesign();
                        } else {
                            if (fillPriceTo.getText().equals("")) {
                                pricefrom = Float.parseFloat(fillPriceFrom.getText());
                                table = Requests.readTableAfterSearchingWithOnePrice(true, dateFromString, dateToString, pricefrom, depId, arrId);
                            } else {
                                pricefrom = Float.parseFloat(fillPriceFrom.getText());
                                priceTo = Float.parseFloat(fillPriceTo.getText());
                                table = Requests.readTableAfterFullSearching(dateFromString, dateToString, pricefrom, priceTo, depId, arrId);
                            }
                            setTableDesign();
                        }
                        setDoubleClick();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    scrollPane.setViewportView(table);
                    panelForTable.removeAll();
                    panelForTable.add(scrollPane);
                    panelForTable.revalidate();
                    panelForTable.repaint();
                });

                searchAllButton.setText("Search All");
                searchAllButton.setBackground(new Color(5, 102, 141));
                searchAllButton.setForeground(Color.white);
                contentPanel.add(searchAllButton);
                searchAllButton.setBounds(755, 165, 130, 40);

                searchAllButton.addActionListener(e -> {
                    try {
                        table = Requests.readFlights();
                        setDoubleClick();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    scrollPane.setViewportView(table);
                    panelForTable.removeAll();
                    panelForTable.add(scrollPane);
                    panelForTable.revalidate();
                    panelForTable.repaint();
                });

                infoLabel.setText(" Double click on the row for the details");
                infoLabel.setBackground(new Color(235, 242, 250));
                infoLabel.setForeground(new Color(5, 102, 141));
                infoLabel.setFont(infoLabel.getFont().deriveFont(infoLabel.getFont().getStyle() | Font.BOLD));
                contentPanel.add(infoLabel);
                infoLabel.setBounds(15, 205, 240, 20);

                {
                    panelForTable.setBackground(new Color(235, 242, 250));
                    panelForTable.setLayout(null);

                    {
                        table.setPreferredScrollableViewportSize(new Dimension(450, 180));
                        if(update) {
                            table = (JTable) Requests.readFligthsById(flight);
                        } else {
                            table = Requests.readFlights();
                        }
                        setDoubleClick();
                        scrollPane.setViewportView(table);
                    }
                    panelForTable.add(scrollPane);
                    scrollPane.setBounds(15, 5, 1035, 290);
                }
                contentPanel.add(panelForTable);
                panelForTable.setBounds(0, 230, 1075, 305);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setBackground(new Color(235, 242, 250));
                buttonBar.setMaximumSize(new Dimension(105, 42));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout) buttonBar.getLayout()).columnWidths = new int[]{0, 80};
                ((GridBagLayout) buttonBar.getLayout()).columnWeights = new double[]{1.0, 0.0};

                okButton.setText("OK");
                okButton.setBackground(new Color(66, 122, 161));

                okButton.addActionListener(e-> {
                    if (table.getSelectedRow() == -1) return;
                    int row = table.getSelectedRow();
                    int id = Integer.parseInt(table.getModel().getValueAt(table.convertRowIndexToModel(row), 0).toString());
                    try {
                        bdf.setFlightId(id);
                        bdf.setSeatComboBox();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    dispose();
                });

                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);

            {
                panel1.setPreferredSize(new Dimension(160, 70));
                panel1.setBackground(new Color(5, 102, 141));
                panel1.setForeground(new Color(5, 102, 141));
                panel1.setMaximumSize(new Dimension(160, 70));
                panel1.setMinimumSize(null);
                panel1.setLayout(null);
            }
            dialogPane.add(panel1, BorderLayout.NORTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    private JComboBox<String> getFilledComboBox() throws SQLException {
        JComboBox<String> comboBox = new JComboBox<String>();
        ResultSet rs = Requests.readByTableName("airport");
        while (rs.next()) {
            int id = rs.getInt(1);
            comboBox.addItem(getFromInformations(id));
        }
        return comboBox;
    }

    private String getFromInformations(int id) throws SQLException {
        ResultSet rs = Requests.readTableByRequest("select airportID, address.country, airport.code from airport\n" +
                "inner join address on address.addressID = address_id\n" +
                "where airportID=" + id);
        rs.next();
        String name = rs.getString(2);
        String code = rs.getString(3);
        return (name + " - " + code);
    }

    private void setDoubleClick() {
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    int id = Integer.parseInt(table.getModel().getValueAt(table.convertRowIndexToModel(row), 0).toString());
                    ResultSet rs = null;
                    try {
                        rs = Requests.readById(id, "flight");
                        rs.next();
                        int depid = rs.getInt(2);
                        int arrid = rs.getInt(3);
                        int pilotId = rs.getInt(4);
                        int planeId = rs.getInt(5);
                        String depTime = rs.getString(6).split("\\.")[0];
                        String depDate = rs.getString(7);
                        String arrTime = rs.getString(8).split("\\.")[0];
                        String arrDate = rs.getString(9);
                        float price = rs.getInt(10);
                        new FlightDetailsFrame(false,id,depid,arrid,pilotId,planeId,depTime,depDate,arrTime,arrDate,price);
                    } catch (SQLException | ParseException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }

    private void setTableDesign() {
        table.setFont(new Font("Roboto", Font.PLAIN, 15));
        table.setRowHeight(30);
        table.setBackground(new Color(235, 242, 250));
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(5, 102, 141));
        tableHeader.setForeground(Color.white);
    }

}
