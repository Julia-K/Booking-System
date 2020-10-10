package main.java.frame;

import main.java.commands.Requests;
import main.java.utils.StringsFormatter;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import main.java.utils.MyOwnDatePicker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FlightDetailsFrame extends JFrame {
    private LinkedHashMap<Integer, Integer> airportsWithId;
    private LinkedHashMap<Integer, Integer> pilotsWithId;
    private LinkedHashMap<Integer, Integer> planesWithId;
    private JComboBox<String> pilotComboBox;
    private JComboBox<String> planeComboBox;
    private JComboBox<String> depComboBox;
    private JComboBox<String> arrComboBox;
    private MyOwnDatePicker depPicker;
    private MyOwnDatePicker arrPicker;
    private JSpinner depTimeSpinner;
    private JSpinner arrTimeSpinner;
    private JTextField fillPrice;
    private JPanel contentPanel;
    private JPanel dialogPane;
    private JPanel planePanel;
    private JPanel buttonBar;
    private JButton okButton;
    private boolean update;
    private JLabel departureL;
    private JLabel arrivalL;
    private JLabel priceL;
    private JPanel panel1;
    private JLabel pilotL;
    private JLabel planeL;
    private JLabel fromL;
    private JLabel toL;
    private int id;

    public FlightDetailsFrame() throws SQLException {
        this.update = false;
        Date data = java.util.Calendar.getInstance().getTime();
        String s = new SimpleDateFormat("yyyy-MM-dd").format(data);
        depPicker = new MyOwnDatePicker(s);
        arrPicker = new MyOwnDatePicker(s);
        initAddUpdateComponents();
        setVisible(true);
    }

    public FlightDetailsFrame(Boolean update, int id, int depId, int arrId, int pilotId, int planeId, String depTime,
                              String depDate, String arrTime, String arrDate, float price) throws SQLException, ParseException {
        this.update = update;
        if (update) {
            this.id = id;
            depPicker = new MyOwnDatePicker(depDate);
            arrPicker = new MyOwnDatePicker(arrDate);
            initAddUpdateComponents();

            for (Map.Entry<Integer, Integer> entry : airportsWithId.entrySet()) { //set comboBox
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                if (value == depId) {
                    depComboBox.setSelectedIndex(key);
                }
                if (value == arrId) {
                    arrComboBox.setSelectedIndex(key);
                }
            }
            for (Map.Entry<Integer, Integer> entry : pilotsWithId.entrySet()) { //set comboBox
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                if (value == pilotId) {
                    pilotComboBox.setSelectedIndex(key);
                }
            }
            for (Map.Entry<Integer, Integer> entry : planesWithId.entrySet()) { //set comboBox
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                if (value == planeId) {
                    planeComboBox.setSelectedIndex(key);
                }
            }
            fillPrice.setText(String.valueOf(price));
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            depTimeSpinner.setValue(format.parseObject(depTime));
            arrTimeSpinner.setValue(format.parseObject(arrTime));
            update = true;
        } else {
            initDetailsComponents();
            fromL.setText(fromL.getText() + " " + getAirport(depId));
            toL.setText(toL.getText() + " " + getAirport(arrId));
            pilotL.setText(pilotL.getText() + " " + getPilot(pilotId));
            planeL.setText(planeL.getText() + " " + getPlane(planeId));
            departureL.setText(departureL.getText() + " " + depDate + "  " + depTime);
            arrivalL.setText(arrivalL.getText() + " " + arrDate + "  " + arrTime);
            priceL.setText(priceL.getText() + " " + price);
        }
        this.update = update;
        setVisible(true);
    }

    private void initDetailsComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        fromL = new JLabel();
        toL = new JLabel();
        departureL = new JLabel();
        arrivalL = new JLabel();
        pilotL = new JLabel();
        planeL = new JLabel();
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
                fromL.setFont(fromL.getFont().deriveFont(fromL.getFont().getStyle() | Font.BOLD, fromL.getFont().getSize() + 13f));
                fromL.setBorder(null);
                contentPanel.add(fromL);
                fromL.setBounds(35, 10, 455, 40);

                toL.setText("To:");
                toL.setForeground(new Color(66, 122, 161));
                toL.setFont(toL.getFont().deriveFont(toL.getFont().getStyle() | Font.BOLD, toL.getFont().getSize() + 13f));
                contentPanel.add(toL);
                toL.setBounds(35, 60, 455, 40);

                departureL.setText("Departure:");
                departureL.setForeground(Color.black);
                departureL.setFont(departureL.getFont().deriveFont(departureL.getFont().getStyle() | Font.BOLD, departureL.getFont().getSize() + 12f));
                contentPanel.add(departureL);
                departureL.setBounds(35, 120, 455, 40);

                arrivalL.setText("Arrival:");
                arrivalL.setForeground(Color.black);
                arrivalL.setFont(arrivalL.getFont().deriveFont(arrivalL.getFont().getStyle() | Font.BOLD, arrivalL.getFont().getSize() + 12f));
                contentPanel.add(arrivalL);
                arrivalL.setBounds(35, 160, 455, 40);

                pilotL.setText("Pilot:");
                pilotL.setForeground(Color.black);
                pilotL.setFont(pilotL.getFont().deriveFont(pilotL.getFont().getStyle() | Font.BOLD, pilotL.getFont().getSize() + 12f));
                contentPanel.add(pilotL);
                pilotL.setBounds(35, 200, 455, 40);

                planeL.setText("Plane:");
                planeL.setForeground(Color.black);
                planeL.setFont(planeL.getFont().deriveFont(planeL.getFont().getStyle() | Font.BOLD, planeL.getFont().getSize() + 12f));
                contentPanel.add(planeL);
                planeL.setBounds(35, 240, 455, 40);

                priceL.setText("Price:");
                priceL.setForeground(Color.black);
                priceL.setFont(priceL.getFont().deriveFont(priceL.getFont().getStyle() | Font.BOLD, priceL.getFont().getSize() + 12f));
                contentPanel.add(priceL);
                priceL.setBounds(35, 280, 455, 40);

                {
                    Dimension preferredSize = new Dimension();
                    preferredSize.width = 490;
                    preferredSize.height = 320;
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
                okButton.addActionListener(e -> {
                    dispose();
                });
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
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void initAddUpdateComponents() throws SQLException {
        planePanel = new JPanel();
        airportsWithId = Requests.getAirportsWithId();
        pilotsWithId = Requests.getPilotsWithId();
        planesWithId = new LinkedHashMap<>();
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        fromL = new JLabel();
        toL = new JLabel();
        JPanel panel3 = new JPanel();
        depComboBox = getAirportComboBox();
        arrComboBox = getAirportComboBox();
        pilotL = new JLabel();
        planeL = new JLabel();
        priceL = new JLabel();
        fillPrice = new JTextField();
        pilotComboBox = getPilotComboBox();
        planeComboBox = new JComboBox<>();
        buttonBar = new JPanel();
        okButton = new JButton();
        panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JLabel depDateL = new JLabel();
        JLabel depTimeL = new JLabel();
        depTimeSpinner = new JSpinner();
        JLabel arrDateL = new JLabel();
        JLabel arrTimeL = new JLabel();
        arrTimeSpinner = new JSpinner();

        setResizable(false);
        setMinimumSize(new Dimension(670, 430));
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

                fromL.setText("Departure airport");
                fromL.setForeground(new Color(66, 122, 161));
                fromL.setFont(fromL.getFont().deriveFont(fromL.getFont().getStyle() | Font.BOLD, fromL.getFont().getSize() + 12f));
                fromL.setBorder(null);
                contentPanel.add(fromL);
                fromL.setBounds(35, 15, 290, 40);

                toL.setText("Arrival airport");
                toL.setForeground(new Color(66, 122, 161));
                toL.setFont(toL.getFont().deriveFont(toL.getFont().getStyle() | Font.BOLD, toL.getFont().getSize() + 12f));
                contentPanel.add(toL);
                toL.setBounds(35, 100, 270, 40);

                {
                    panel3.setBackground(new Color(5, 102, 141));
                    panel3.setPreferredSize(new Dimension(5, 200));
                    panel3.setMinimumSize(new Dimension(5, 200));
                    panel3.setMaximumSize(new Dimension(5, 200));
                    panel3.setLayout(null);
                }
                contentPanel.add(panel3);
                panel3.setBounds(345, 35, 5, 365);

                depComboBox.setBackground(Color.white);
                depComboBox.setForeground(Color.black);
                contentPanel.add(depComboBox);
                depComboBox.setBounds(35, 55, 195, 40);

                arrComboBox.setBackground(Color.white);
                arrComboBox.setForeground(Color.black);
                contentPanel.add(arrComboBox);
                arrComboBox.setBounds(35, 140, 195, 40);

                pilotL.setText("Pilot");
                pilotL.setForeground(Color.black);
                pilotL.setFont(pilotL.getFont().deriveFont(pilotL.getFont().getStyle() | Font.BOLD, pilotL.getFont().getSize() + 12f));
                contentPanel.add(pilotL);
                pilotL.setBounds(35, 185, 195, 40);

                planeL.setText("Plane");
                planeL.setForeground(Color.black);
                planeL.setFont(planeL.getFont().deriveFont(planeL.getFont().getStyle() | Font.BOLD, planeL.getFont().getSize() + 12f));
                contentPanel.add(planeL);
                planeL.setBounds(35, 270, 205, 40);

                priceL.setText("Price");
                priceL.setForeground(Color.black);
                priceL.setFont(priceL.getFont().deriveFont(priceL.getFont().getStyle() | Font.BOLD, priceL.getFont().getSize() + 12f));
                contentPanel.add(priceL);
                priceL.setBounds(35, 360, 130, 40);

                fillPrice.setBackground(Color.white);
                fillPrice.setForeground(Color.black);
                StringsFormatter.setFloatPattern(5, fillPrice);
                contentPanel.add(fillPrice);
                fillPrice.setBounds(155, 360, 75, 40);

                pilotComboBox.setBackground(Color.white);
                pilotComboBox.setForeground(Color.black);
                contentPanel.add(pilotComboBox);
                pilotComboBox.setBounds(35, 225, 195, 40);
                pilotComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        planeComboBox.removeAllItems();
                        try {
                            int airlineID = getAirlineId((Integer) pilotsWithId.get(pilotComboBox.getSelectedIndex()));
                            ResultSet rs = Requests.readTableByRequest("select * from plane_airline");
                            while (rs.next()) {
                                int id = rs.getInt("airline_id");
                                if (airlineID == id) {
                                    int planeId = rs.getInt("plane_id");
                                    planeComboBox.addItem(getPlane(planeId));
                                }
                            }
                            planesWithId = Requests.getPlanesByAirlineID(airlineID);
                            planePanel.removeAll();
                            planePanel.add(planeComboBox);
                            planePanel.revalidate();
                            planePanel.repaint();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                planePanel.setBackground(new Color(235, 242, 250));
                planePanel.setLayout(null);

                planeComboBox.setBackground(Color.white);
                planeComboBox.setForeground(Color.black);
                planePanel.add(planeComboBox);
                planeComboBox.setBounds(15, 0, 195, 40);
                contentPanel.add(planePanel);
                planePanel.setBounds(20, 305, 240, 55);
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

                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
            }

            okButton.addActionListener(e -> {
                if (isValidate()) {
                    try {
                        int depId = (Integer) airportsWithId.get(depComboBox.getSelectedIndex());
                        int arrId = (Integer) airportsWithId.get(arrComboBox.getSelectedIndex());
                        int pilotId = (Integer) pilotsWithId.get(pilotComboBox.getSelectedIndex());
                        int planeId = (Integer) planesWithId.get(planeComboBox.getSelectedIndex());
                        SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
                        String arrSpinner = formater.format(arrTimeSpinner.getValue());
                        String depSpinner = formater.format(depTimeSpinner.getValue());
                        if (update) {
                            Requests.updateFlight(id, depId, arrId, pilotId, planeId, depSpinner, depPicker.getDate(), arrSpinner,
                                    arrPicker.getDate(), fillPrice.getText());
                            dispose();
                        } else {
                            Requests.createFlight(depId, arrId, pilotId, planeId, depSpinner, depPicker.getDate(), arrSpinner,
                                    arrPicker.getDate(), fillPrice.getText());
                            dispose();
                        }
                    } catch (NullPointerException x) {
                        JOptionPane.showMessageDialog(new Frame(), "You must select a pilot who is assigned to some plain");
                    } catch (SQLServerException x) {
                        JOptionPane.showMessageDialog(new Frame(), "Price must be of the float type in the given formula (max: _ _ _ _ _._ _)");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });
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

            {
                panel2.setMinimumSize(new Dimension(300, 200));
                panel2.setPreferredSize(new Dimension(300, 230));
                panel2.setBackground(new Color(235, 242, 250));
                panel2.setLayout(null);

                depDateL.setText("Departure date");
                depDateL.setForeground(Color.black);
                depDateL.setFont(depDateL.getFont().deriveFont(depDateL.getFont().getStyle() | Font.BOLD, depDateL.getFont().getSize() + 12f));
                depDateL.setBorder(null);
                panel2.add(depDateL);
                depDateL.setBounds(15, 15, 190, 40);

                depTimeL.setText("Departure time");
                depTimeL.setForeground(Color.black);
                depTimeL.setFont(depTimeL.getFont().deriveFont(depTimeL.getFont().getStyle() | Font.BOLD, depTimeL.getFont().getSize() + 12f));
                depTimeL.setBorder(null);
                panel2.add(depTimeL);
                depTimeL.setBounds(15, 95, 190, 40);

                depTimeSpinner.setBackground(Color.white);
                panel2.add(depTimeSpinner);
                depTimeSpinner.setBounds(15, 140, 195, 40);

                arrDateL.setText("Arrival date");
                arrDateL.setForeground(Color.black);
                arrDateL.setFont(arrDateL.getFont().deriveFont(arrDateL.getFont().getStyle() | Font.BOLD, arrDateL.getFont().getSize() + 12f));
                arrDateL.setBorder(null);
                panel2.add(arrDateL);
                arrDateL.setBounds(15, 185, 190, 40);

                arrTimeL.setText("Arrival time");
                arrTimeL.setForeground(Color.black);
                arrTimeL.setFont(arrTimeL.getFont().deriveFont(arrTimeL.getFont().getStyle() | Font.BOLD, arrTimeL.getFont().getSize() + 12f));
                arrTimeL.setBorder(null);
                panel2.add(arrTimeL);
                arrTimeL.setBounds(15, 265, 190, 40);

                arrTimeSpinner.setBackground(Color.white);
                panel2.add(arrTimeSpinner);
                arrTimeSpinner.setBounds(15, 310, 195, 40);

                depPicker.addTo(panel2);
                depPicker.setBackground(new Color(235, 242, 250));
                depPicker.setBounds(15, 55, 195, 26);

                setSpinner(arrTimeSpinner);
                setSpinner(depTimeSpinner);

                arrPicker.addTo(panel2);
                arrPicker.setBackground(new Color(235, 242, 250));
                arrPicker.setBounds(15, 225, 195, 26);
            }
            dialogPane.add(panel2, BorderLayout.EAST);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    private JComboBox<String> getAirportComboBox() throws SQLException {
        JComboBox<String> comboBox = new JComboBox<String>();
        ResultSet rs = Requests.readTableByRequest("select airportID from airport");
        while (rs.next()) {
            int id = rs.getInt("airportID");
            comboBox.addItem(getAirport(id));
        }
        return comboBox;
    }

    private JComboBox<String> getPilotComboBox() throws SQLException {
        JComboBox<String> comboBox = new JComboBox<>();
        ResultSet rs = Requests.readTableByRequest("select pilotID from pilot");
        while (rs.next()) {
            int id = rs.getInt("pilotID");
            comboBox.addItem(getPilot(id));
        }
        return comboBox;
    }

    private void setSpinner(JSpinner jSpinner) {
        Date date = new Date();
        SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        jSpinner.setModel(sm);
        JSpinner.DateEditor de = new JSpinner.DateEditor(jSpinner, "HH:mm");
        jSpinner.setEditor(de);
    }

    public boolean isValidate() {
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
        String arrSpinner = formater.format(arrTimeSpinner.getValue());
        String depSpinner = formater.format(depTimeSpinner.getValue());
        if (fillPrice.getText().equals("")) {
            JOptionPane.showMessageDialog(new Frame(), "All fields must be filled!");
            return false;
        } else if (!StringsFormatter.isFlightDateValid(depPicker.getDate(), arrPicker.getDate())) {
            JOptionPane.showMessageDialog(new Frame(), "Dates must be from the future and departure date cannot be later than the date of arrival");
            return false;
        } else if (StringsFormatter.isDatesAreEqual(depPicker.getDate(), arrPicker.getDate())) {
            if (!StringsFormatter.isDepTimeIsBefore(depSpinner, arrSpinner)) {
                JOptionPane.showMessageDialog(new Frame(), "If dates are the same, the arrival time must be later than the departure time");
                return false;
            }
            return true;
        } else {
            return true;
        }
    }

    private String getAirport(int id) throws SQLException {
        ResultSet rs = Requests.readById(id, "airport");
        rs.next();
        String name = rs.getString(2);
        String code = rs.getString(3);
        return (name + " (" + code + ")");
    }

    private String getPilot(int id) throws SQLException {
        ResultSet rs = Requests.readById(id, "pilot");
        rs.next();
        String name = rs.getString(2);
        String last = rs.getString(3);
        return (name + " " + last);
    }

    private int getAirlineId(int pilotId) throws SQLException {
        ResultSet rs = Requests.readById(pilotId, "pilot");
        rs.next();
        int airlineId = rs.getInt(5);
        return airlineId;
    }

    private String getPlane(int id) throws SQLException {
        ResultSet rs = Requests.readById(id, "plane");
        rs.next();
        String brand = rs.getString(2);
        String model = rs.getString(3);
        return (brand + " - " + model);
    }

    public LinkedHashMap<Integer, Integer> getAirportsWithId() {
        return airportsWithId;
    }

    public void setAirportsWithId(LinkedHashMap<Integer, Integer> airportsWithId) {
        this.airportsWithId = airportsWithId;
    }

    public LinkedHashMap<Integer, Integer> getPilotsWithId() {
        return pilotsWithId;
    }

    public void setPilotsWithId(LinkedHashMap<Integer, Integer> pilotsWithId) {
        this.pilotsWithId = pilotsWithId;
    }

    public LinkedHashMap<Integer, Integer> getPlanesWithId() {
        return planesWithId;
    }

    public void setPlanesWithId(LinkedHashMap<Integer, Integer> planesWithId) {
        this.planesWithId = planesWithId;
    }

    public void setPilotComboBox(JComboBox<String> pilotComboBox) {
        this.pilotComboBox = pilotComboBox;
    }

    public JComboBox<String> getPlaneComboBox() {
        return planeComboBox;
    }

    public void setPlaneComboBox(JComboBox<String> planeComboBox) {
        this.planeComboBox = planeComboBox;
    }

    public JComboBox<String> getDepComboBox() {
        return depComboBox;
    }

    public void setDepComboBox(JComboBox<String> depComboBox) {
        this.depComboBox = depComboBox;
    }

    public JComboBox<String> getArrComboBox() {
        return arrComboBox;
    }

    public void setArrComboBox(JComboBox<String> arrComboBox) {
        this.arrComboBox = arrComboBox;
    }

    public MyOwnDatePicker getDepPicker() {
        return depPicker;
    }

    public void setDepPicker(MyOwnDatePicker depPicker) {
        this.depPicker = depPicker;
    }

    public MyOwnDatePicker getArrPicker() {
        return arrPicker;
    }

    public void setArrPicker(MyOwnDatePicker arrPicker) {
        this.arrPicker = arrPicker;
    }

    public JSpinner getDepTimeSpinner() {
        return depTimeSpinner;
    }

    public void setDepTimeSpinner(JSpinner depTimeSpinner) {
        this.depTimeSpinner = depTimeSpinner;
    }

    public JSpinner getArrTimeSpinner() {
        return arrTimeSpinner;
    }

    public void setArrTimeSpinner(JSpinner arrTimeSpinner) {
        this.arrTimeSpinner = arrTimeSpinner;
    }

    public JTextField getFillPrice() {
        return fillPrice;
    }

    public void setFillPrice(JTextField fillPrice) {
        this.fillPrice = fillPrice;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void setContentPanel(JPanel contentPanel) {
        this.contentPanel = contentPanel;
    }

    public JPanel getDialogPane() {
        return dialogPane;
    }

    public void setDialogPane(JPanel dialogPane) {
        this.dialogPane = dialogPane;
    }

    public JPanel getPlanePanel() {
        return planePanel;
    }

    public void setPlanePanel(JPanel planePanel) {
        this.planePanel = planePanel;
    }

    public JPanel getButtonBar() {
        return buttonBar;
    }

    public void setButtonBar(JPanel buttonBar) {
        this.buttonBar = buttonBar;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public void setOkButton(JButton okButton) {
        this.okButton = okButton;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public JLabel getDepartureL() {
        return departureL;
    }

    public void setDepartureL(JLabel departureL) {
        this.departureL = departureL;
    }

    public JLabel getArrivalL() {
        return arrivalL;
    }

    public void setArrivalL(JLabel arrivalL) {
        this.arrivalL = arrivalL;
    }

    public JLabel getPriceL() {
        return priceL;
    }

    public void setPriceL(JLabel priceL) {
        this.priceL = priceL;
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    public JLabel getPilotL() {
        return pilotL;
    }

    public void setPilotL(JLabel pilotL) {
        this.pilotL = pilotL;
    }

    public JLabel getPlaneL() {
        return planeL;
    }

    public void setPlaneL(JLabel planeL) {
        this.planeL = planeL;
    }

    public JLabel getFromL() {
        return fromL;
    }

    public void setFromL(JLabel fromL) {
        this.fromL = fromL;
    }

    public JLabel getToL() {
        return toL;
    }

    public void setToL(JLabel toL) {
        this.toL = toL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
