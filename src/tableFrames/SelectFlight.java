package tableFrames;

import allComands.Requests;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

public class SelectFlight extends JFrame {
    private LinkedHashMap airportsWithId;
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JComboBox fromCombo;
    private JComboBox toCombo;
    private JLabel searchL;
    private JLabel fromL;
    private MyOwnDatePicker dateFrom;
    private MyOwnDatePicker dateTo;
    private JTextField fillPriceFrom;
    private JTextField fillPriceTo;
    private JLabel toL;
    private JLabel dateFromL;
    private JLabel dateToL;
    private JLabel priceFromL;
    private JLabel priceToL;
    private JButton searchButton;
    private JLabel infoLabel;
    private JPanel panelForTable;
    private JScrollPane scrollPane;
    private JTable table;
    private JPanel buttonBar;
    private JButton okButton;
    private JPanel panel1;
    BookingDetailsFrame bdf;

    public SelectFlight(BookingDetailsFrame bdf) throws SQLException {
        this.bdf = bdf;
        Date data = java.util.Calendar.getInstance().getTime();
        String s = new SimpleDateFormat("yyyy-MM-dd").format(data);
        dateFrom = new MyOwnDatePicker(s);
        dateTo = new MyOwnDatePicker(s);
        initComponents();
    }

    private void initComponents() throws SQLException {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        airportsWithId = Requests.getAirportsWithId();
        fromCombo = getFilledComboBox();
        toCombo = getFilledComboBox();
        searchL = new JLabel();
        fromL = new JLabel();
        fillPriceFrom = new JTextField();
        fillPriceTo = new JTextField();
        toL = new JLabel();
        dateFromL = new JLabel();
        dateToL = new JLabel();
        priceFromL = new JLabel();
        priceToL = new JLabel();
        searchButton = new JButton();
        infoLabel = new JLabel();
        panelForTable = new JPanel();
        scrollPane = new JScrollPane();
        table = new JTable();
        buttonBar = new JPanel();
        okButton = new JButton();
        panel1 = new JPanel();

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
                dateTo.setBounds(665, 95, 210, 50);

                fillPriceFrom.setForeground(Color.black);
                fillPriceFrom.setBackground(new Color(235, 242, 250));
                contentPanel.add(fillPriceFrom);
                fillPriceFrom.setBounds(885, 95, 70, 26);

                fillPriceTo.setForeground(Color.black);
                fillPriceTo.setBackground(new Color(235, 242, 250));
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
                    table = getTableAfterSearching();
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
                        table = Requests.readFlights();
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
                    System.out.println("ID FLIGHT: "+ id);
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
        ResultSet rs = Requests.readTableByRequest("select airportID from airport");
        while (rs.next()) {
            int id = rs.getInt("airportID");
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

    private void updateContent(int airlineid) throws SQLException {
        airportsWithId = Requests.getAirportsWithId();
        fromCombo = getFilledComboBox();
        toCombo = getFilledComboBox();
    }

    private JTable getTableAfterSearching() {
        String dateFromString = dateFrom.getDate();
        String dateToString = dateTo.getDate();
        JTable jTable = new JTable();
        int pricefrom = Integer.parseInt(fillPriceFrom.getText());
        int priceTo = Integer.parseInt(fillPriceTo.getText());
        int depId = (Integer) airportsWithId.get(fromCombo.getSelectedIndex());
        int arrId = (Integer) airportsWithId.get(toCombo.getSelectedIndex());
        DefaultTableModel model = new DefaultTableModel();
        try {
            ResultSet rs = Requests.readTableByRequest("select flightID, dep.name as dname, arr.name as aname, departure_date, arrival_date from flight\n" +
                    "inner join airport as dep on dep.airportID = flight.departureAirport_id\n" +
                    "inner join airport as arr on arr.airportID = flight.arrivalAirport_id\n" +
                    "where price between " + pricefrom + " and " + priceTo + "\n" +
                    "and dep.airportID = " + depId + " and arr.airportID = " + arrId + "\n" +
                    "and departure_date >= '" + dateFromString + "' \n" +
                    "and arrival_date <= '" + dateToString + "'");
            model.addColumn("flightID");
            model.addColumn("Departure Airport");
            model.addColumn("Arrival Airport");
            model.addColumn("Departure date");
            model.addColumn("Arrival date");
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            jTable.setRowSorter(sorter);
            System.out.println("DZIAla?");
            jTable.setModel(Requests.addRows(rs, model));
            jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return jTable;
    }
}
