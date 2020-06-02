package tableFrames;

import allComands.Requests;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class BookingDetailsFrame extends JFrame {
    private float[] prices;
    private LinkedHashMap<Integer,Integer> clietsWithId;
    private LinkedHashMap<Integer,Integer> luggageWithId;
    private LinkedHashMap<Integer,Integer> classWithId;
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel classL;
    private JPanel panel3;
    private JLabel displayEmail;
    private JLabel luggageL;
    private JLabel displayLuggage;
    private JLabel displayClass;
    private JPanel buttonBar;
    private JButton okButton;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel seatL;
    private JLabel displaySeat;
    private JLabel displayPrice;
    private int flightId;
    private JComboBox<String> classCombo;
    private JComboBox<String> luggageCombo;
    private JComboBox<String> clientCombo;
    private JComboBox<Integer> seatCombo;
    private JButton selectFlightButton;
    private JLabel totalPrice;
    private JTextPane informationPane;
    private JPanel panel4;
    private boolean update;
    private int id;
    private int seat;

    public int getFlightId() {
        return flightId;
    }

    public BookingDetailsFrame(int id, int flightId, String email, String luggage, String classP, String seat, String price) {
        this.update = false;
        this.flightId = flightId;
        initDetailsComponents();
        displayClass.setText(classP);
        displayEmail.setText(email);
        displayLuggage.setText(luggage);
        displaySeat.setText(seat);
        displayPrice.setText(price);
        setVisible(true);
    }

    public BookingDetailsFrame(int id, int flightId, int client, int luggage, int classId, int seat) throws SQLException {
        this.seat = seat;
        this.update = true;
        this.id = id;
        initAddUpdateComponents();
        for (Map.Entry<Integer, Integer> entry : clietsWithId.entrySet()) { //set comboBox
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (value == client) {
                clientCombo.setSelectedIndex(key);
            }
        }

        for (Map.Entry<Integer, Integer> entry : luggageWithId.entrySet()) { //set comboBox
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (value == luggage) {
                luggageCombo.setSelectedIndex(key);
            }
        }

        for (Map.Entry<Integer, Integer> entry : classWithId.entrySet()) { //set comboBox
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (value == classId) {
                classCombo.setSelectedIndex(key);
            }
        }

        setFlightId(flightId);
        setSeatComboBox();
        seatCombo.setSelectedItem(seat);
        selectFlightButton.addActionListener(e-> {
            try {
                new SelectFlight(true,this).setVisible(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        setVisible(true);
    }

    public BookingDetailsFrame() throws SQLException {
        this.update = false;
        initAddUpdateComponents();
        selectFlightButton.addActionListener(e-> {
            try {
                new SelectFlight(false,this).setVisible(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        setVisible(true);
        Arrays.fill(prices,0);
        luggageCombo.setSelectedIndex(0);
        classCombo.setSelectedIndex(0);
    }

    private void initDetailsComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        JLabel email = new JLabel();
        classL = new JLabel();
        panel3 = new JPanel();
        displayEmail = new JLabel();
        luggageL = new JLabel();
        displayLuggage = new JLabel();
        displayClass = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();
        panel1 = new JPanel();
        panel2 = new JPanel();
        seatL = new JLabel();
        JButton buttonFlight = new JButton();
        displaySeat = new JLabel();
        JLabel priceL = new JLabel();
        displayPrice = new JLabel();

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

                email.setText("Customer e-mail:");
                email.setForeground(new Color(66, 122, 161));
                email.setFont(email.getFont().deriveFont(email.getFont().getStyle() | Font.BOLD, email.getFont().getSize() + 17f));
                contentPanel.add(email);
                email.setBounds(35, 40, 245, 40);

                classL.setText("Class:");
                classL.setForeground(Color.black);
                classL.setFont(classL.getFont().deriveFont(classL.getFont().getStyle() | Font.BOLD, classL.getFont().getSize() + 17f));
                contentPanel.add(classL);
                classL.setBounds(35, 280, 135, 40);
                {
                    panel3.setBackground(new Color(5, 102, 141));
                    panel3.setLayout(null);
                }
                contentPanel.add(panel3);
                panel3.setBounds(310, 40, 5, 320);

                displayEmail.setFont(displayEmail.getFont().deriveFont(displayEmail.getFont().getStyle() | Font.BOLD, displayEmail.getFont().getSize() + 9f));
                displayEmail.setBorder(null);
                displayEmail.setForeground(new Color(66, 122, 161));
                contentPanel.add(displayEmail);
                displayEmail.setBounds(35, 90, 260, 40);

                luggageL.setText("Luggage:");
                luggageL.setForeground(Color.black);
                luggageL.setFont(luggageL.getFont().deriveFont(luggageL.getFont().getStyle() | Font.BOLD, luggageL.getFont().getSize() + 17f));
                luggageL.setBorder(null);
                contentPanel.add(luggageL);
                luggageL.setBounds(35, 160, 165, 40);

                displayLuggage.setForeground(Color.black);
                displayLuggage.setFont(displayLuggage.getFont().deriveFont(displayLuggage.getFont().getStyle() | Font.BOLD, displayLuggage.getFont().getSize() + 13f));
                displayLuggage.setBorder(null);
                contentPanel.add(displayLuggage);
                displayLuggage.setBounds(35, 210, 260, 40);

                displayClass.setForeground(Color.black);
                displayClass.setFont(displayClass.getFont().deriveFont(displayClass.getFont().getStyle() | Font.BOLD, displayClass.getFont().getSize() + 13f));
                displayClass.setBorder(null);
                contentPanel.add(displayClass);
                displayClass.setBounds(35, 330, 235, 40);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setBackground(new Color(235, 242, 250));
                buttonBar.setMaximumSize(new Dimension(105, 42));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                okButton.setText("OK");
                okButton.setBackground(new Color(66, 122, 161));
                okButton.addActionListener(e-> {
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

            {
                panel2.setMinimumSize(new Dimension(300, 200));
                panel2.setPreferredSize(new Dimension(300, 230));
                panel2.setBackground(new Color(235, 242, 250));
                panel2.setLayout(null);

                seatL.setText("Seat number");
                seatL.setForeground(Color.black);
                seatL.setFont(seatL.getFont().deriveFont(seatL.getFont().getStyle() | Font.BOLD, seatL.getFont().getSize() + 17f));
                seatL.setBorder(null);
                panel2.add(seatL);
                seatL.setBounds(5, 40, 205, 40);

                buttonFlight.setText("Display flight");
                buttonFlight.setBackground(new Color(66, 122, 161));
                buttonFlight.setForeground(Color.white);
                panel2.add(buttonFlight);
                buttonFlight.setBounds(5, 280, 190, 40);
                buttonFlight.addActionListener(e-> {
                    ResultSet rs = null;
                    try {
                        rs = Requests.readById(flightId, "flight");
                        rs.next();
                        int depid = rs.getInt(2);
                        int arrid = rs.getInt(3);
                        int pilotId = rs.getInt(4);
                        int planeId = rs.getInt(5);
                        String depTime = rs.getString(6).split("\\.")[0];
                        String depDate = rs.getString(7);
                        String arrTime = rs.getString(8).split("\\.")[0];
                        String arrDate = rs.getString(9);
                        int total = rs.getInt(10);
                        new FlightDetailsFrame(false, flightId, depid, arrid, pilotId, planeId, depTime, depDate, arrTime, arrDate, total);
                    } catch (ParseException | SQLException x) {
                        x.printStackTrace();
                    }
                });

                displaySeat.setForeground(Color.black);
                displaySeat.setFont(displaySeat.getFont().deriveFont(displaySeat.getFont().getStyle() | Font.BOLD, displaySeat.getFont().getSize() + 13f));
                displaySeat.setBorder(null);
                panel2.add(displaySeat);
                displaySeat.setBounds(5, 90, 200, 40);

                priceL.setText("Price:");
                priceL.setForeground(Color.black);
                priceL.setFont(priceL.getFont().deriveFont(priceL.getFont().getStyle() | Font.BOLD, priceL.getFont().getSize() + 17f));
                priceL.setBorder(null);
                panel2.add(priceL);
                priceL.setBounds(5, 160, 205, 40);

                displayPrice.setForeground(Color.black);
                displayPrice.setFont(displayPrice.getFont().deriveFont(displayPrice.getFont().getStyle() | Font.BOLD, displayPrice.getFont().getSize() + 13f));
                displayPrice.setBorder(null);
                panel2.add(displayPrice);
                displayPrice.setBounds(5, 210, 200, 40);
            }
            dialogPane.add(panel2, BorderLayout.EAST);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
    }

    private void initAddUpdateComponents() throws SQLException {
        prices = new float[4];
        panel4 = new JPanel();
        clietsWithId= Requests.getClientsWithId();
        luggageWithId = Requests.getLuggageWithId();
        classWithId = Requests.getClassesWithId();
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        JLabel nameL = new JLabel();
        luggageL = new JLabel();
        classL = new JLabel();
        classCombo = getClassComboBox();
        panel3 = new JPanel();
        luggageCombo = getLuggageComboBox();
        clientCombo = getClientComboBox();
        seatL = new JLabel();
        seatCombo = new JComboBox<Integer>();
        buttonBar = new JPanel();
        okButton = new JButton();
        panel1 = new JPanel();
        panel2 = new JPanel();
        selectFlightButton = new JButton();
        JPanel panelForInformation = new JPanel();
        totalPrice = new JLabel();
        informationPane = new JTextPane();

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

                nameL.setText("Client:");
                nameL.setForeground(new Color(66, 122, 161));
                nameL.setFont(nameL.getFont().deriveFont(nameL.getFont().getStyle() | Font.BOLD, nameL.getFont().getSize() + 17f));
                nameL.setBorder(null);
                contentPanel.add(nameL);
                nameL.setBounds(35, 15, 205, 40);

                luggageL.setText("Luggage:");
                luggageL.setForeground(Color.black);
                luggageL.setFont(luggageL.getFont().deriveFont(luggageL.getFont().getStyle() | Font.BOLD, luggageL.getFont().getSize() + 17f));
                contentPanel.add(luggageL);
                luggageL.setBounds(35, 115, 175, 40);

                classL.setText("Class:");
                classL.setForeground(Color.black);
                classL.setFont(classL.getFont().deriveFont(classL.getFont().getStyle() | Font.BOLD, classL.getFont().getSize() + 17f));
                contentPanel.add(classL);
                classL.setBounds(35, 215, 130, 40);

                classCombo.setBackground(Color.white);
                contentPanel.add(classCombo);
                classCombo.setBounds(35, 265, 195, 40);
                classCombo.addActionListener(e-> {
                    int classID = classWithId.get(classCombo.getSelectedIndex());
                    ResultSet rs = null;
                    try {
                        rs = Requests.readById(classID, "class");
                        rs.next();
                        float price = rs.getFloat(3);
                        if(prices[1] != 0) {
                            prices[3] -= prices[1];
                            prices[1] = price;
                            prices[3] += prices[1];
                        } else {
                            prices[1] = price;
                            prices[3] += prices[1];
                        }
                        totalPrice.setText("Price: " + prices[3]);

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                });

                {
                    panel3.setBackground(new Color(5, 102, 141));
                    panel3.setPreferredSize(new Dimension(5, 200));
                    panel3.setMinimumSize(new Dimension(5, 200));
                    panel3.setMaximumSize(new Dimension(5, 200));
                    panel3.setLayout(null);
                }
                contentPanel.add(panel3);
                panel3.setBounds(345, 35, 5, 365);

                luggageCombo.setBackground(Color.white);
                contentPanel.add(luggageCombo);
                luggageCombo.setBounds(35, 165, 195, 40);

                luggageCombo.addActionListener(e-> {
                    int luggageID = luggageWithId.get(luggageCombo.getSelectedIndex());
                    ResultSet rs = null;
                    try {
                        rs = Requests.readById(luggageID, "luggage");
                        rs.next();
                        float price = rs.getFloat(3);
                        if(prices[0] != 0) {
                            prices[3] -= prices[0];
                            prices[0] = price;
                            prices[3] += prices[0];
                        } else {
                            prices[0] = price;
                            prices[3] += prices[0];
                        }
                        totalPrice.setText("Price: " + prices[3]);

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                });

                clientCombo.setBackground(Color.white);
                contentPanel.add(clientCombo);
                clientCombo.setBounds(35, 65, 195, 40);

                selectFlightButton.setText("Select flight");
                selectFlightButton.setBackground(new Color(5, 102, 141));
                selectFlightButton.setForeground(Color.white);
                contentPanel.add(selectFlightButton);
                selectFlightButton.setBounds(35, 340, 195, 45);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setBackground(new Color(235, 242, 250));
                buttonBar.setMaximumSize(new Dimension(105, 42));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                okButton.setText("OK");
                okButton.setBackground(new Color(66, 122, 161));
                okButton.addActionListener(e-> {
                    int clientId = clietsWithId.get(clientCombo.getSelectedIndex());
                    int luggId = luggageWithId.get(luggageCombo.getSelectedIndex());
                    int classId = classWithId.get(classCombo.getSelectedIndex());
                    int seatNum;
                    try {
                        seatNum = (int) seatCombo.getItemAt(seatCombo.getSelectedIndex());
                        if(update) {
                            try {
                                Requests.updateBooking(id,flightId,clientId,luggId,classId,seatNum);
                                dispose();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        } else {
                            try {
                                Requests.createBooking(flightId,clientId,luggId,classId,seatNum);
                                dispose();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    } catch (NullPointerException x) {
                        JOptionPane.showMessageDialog(new Frame(), "You must select flight.");
                    }

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

            {
                panel2.setMinimumSize(new Dimension(300, 200));
                panel2.setPreferredSize(new Dimension(300, 230));
                panel2.setBackground(new Color(235, 242, 250));
                panel2.setLayout(null);

                {
                    panelForInformation.setBackground(new Color(235, 242, 250));
                    panelForInformation.setLayout(null);

                    informationPane.setBackground(new Color(235, 242, 250));
                    informationPane.setForeground(new Color(5, 102, 141));
                    informationPane.setFont(new Font("Serif", Font.BOLD, 15));
                    informationPane.setText("Select flight for more details.");
                    panelForInformation.add(informationPane);
                    informationPane.setBounds(10, 10, 240, 185);
                }
                panel2.add(panelForInformation);
                panelForInformation.setBounds(5, 20, 265, 205);

                seatL.setText("Seat number:");
                seatL.setForeground(Color.black);
                seatL.setFont(seatL.getFont().deriveFont(seatL.getFont().getStyle() | Font.BOLD, seatL.getFont().getSize() + 17f));
                panel2.add(seatL);
                seatL.setBounds(15, 215, 190, 40);

                {
                    panel4.setBackground(new Color(235, 242, 250));
                    panel4.setLayout(null);

                    seatCombo.setBackground(Color.white);
                    panel4.add(seatCombo);
                    seatCombo.setBounds(10, 5, 195, 40);
                }
                panel2.add(panel4);
                panel4.setBounds(5, 260, 265, 50);

                totalPrice.setText("Price:");
                totalPrice.setForeground(Color.black);
                totalPrice.setFont(totalPrice.getFont().deriveFont(totalPrice.getFont().getStyle() | Font.BOLD, totalPrice.getFont().getSize() + 20f));
                panel2.add(totalPrice);
                totalPrice.setBounds(15, 340, 250, 40);
            }
            dialogPane.add(panel2, BorderLayout.EAST);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    public void setFlightId(int id) throws SQLException {
        flightId = id;
        ResultSet rs = Requests.readTableByRequest("select dep.name as dname, arr.name as aname, departure_date, arrival_date, departure_time, arrival_time, price from flight\n" +
                "inner join airport as dep on dep.airportID = flight.departureAirport_id\n" +
                "inner join airport as arr on arr.airportID = flight.arrivalAirport_id\n" +
                "where flightID="+id);
        rs.next();
        String from = rs.getString(1);
        String to = rs.getString(2);
        String depDate = rs.getString(3);
        String arrDate = rs.getString(4);
        String depTime = rs.getString(5).substring(0,5);
        String arrTime = rs.getString(6).substring(0,5);
        float price = rs.getFloat(7);

        informationPane.setText("Details:" +"\n"+"Departure Airport:" + "\n" +from + "\n" + "Arrival Airport:" +"\n"+ to + "\n" + "Departure date: " + depDate +
                "\n" + "Arrival date: " + arrDate + "\n" + "Departure time: " + depTime + "\n" + "Arrival time: " + arrTime);
        if(prices[2] != 0) {
            prices[3] -= prices[2];
            prices[2] = price;
            prices[3] += prices[2];
        } else {
            prices[2] = price;
            prices[3] += prices[2];
        }
        totalPrice.setText("Price: " + prices[3]);
    }

    private JComboBox<String> getClientComboBox() throws SQLException {
        JComboBox<String> comboBox = new JComboBox<>();
        ResultSet rs = Requests.readTableByRequest("select clientID from client");
        while (rs.next()) {
            int id = rs.getInt("clientID");
            comboBox.addItem(getEmail(id));
        }
        return comboBox;
    }

    private JComboBox<String> getLuggageComboBox() throws SQLException {
        JComboBox<String> comboBox = new JComboBox<>();
        ResultSet rs = Requests.readTableByRequest("select luggageID from luggage");
        while (rs.next()) {
            int id = rs.getInt("luggageID");
            comboBox.addItem(getLuggage(id));
        }
        return comboBox;
    }

    private JComboBox<String> getClassComboBox() throws SQLException {
        JComboBox<String> comboBox = new JComboBox<>();
        ResultSet rs = Requests.readTableByRequest("select classID from class");
        while (rs.next()) {
            int id = rs.getInt("classID");
            comboBox.addItem(getClass(id));
        }
        return comboBox;
    }

    private String getLuggage(int id) throws SQLException {
        ResultSet rs = Requests.readById(id, "luggage");
        rs.next();
        return rs.getString(2);
    }

    private String getClass(int id) throws SQLException {
        ResultSet rs = Requests.readById(id, "class");
        rs.next();
        return rs.getString(2);
    }
    private String getEmail(int id) throws SQLException {
        ResultSet rs = Requests.readById(id, "client");
        rs.next();
       return rs.getString(4);
    }

    public void setSeatComboBox() throws SQLException {
        LinkedHashMap linkedHashMap;
        if(update) {
            linkedHashMap = Requests.loadListOfSeats(flightId, seat);
        } else {
            linkedHashMap = Requests.loadListOfSeats();
        }
        LinkedList<Integer> linkedList = (LinkedList<Integer>) linkedHashMap.get(flightId);
        panel4.removeAll();
        seatCombo.removeAllItems();

        for (int i : linkedList) {
            seatCombo.addItem(i);
        }

        panel4.add(seatCombo);
        panel4.revalidate();
        panel4.repaint();
    }
}

