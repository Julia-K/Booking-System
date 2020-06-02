import allComands.Requests;
import allComands.TableRowFilter;

import java.awt.*;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.JTableHeader;

public class ManageWindow extends JFrame {
    private static ManageWindow manageWindow;
    private LinkedHashMap<Integer, String> tableCombobox;
    private JPanel topPanel;
    private JLabel manage;
    private JComboBox<String> comboBox;
    private JPanel bottomPanel;
    private JPanel leftPanel;
    private JPanel filterPanel;
    private JTextField searchField;
    private JButton detailsButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton addButton;
    private JPanel rightPanel;
    private JTable table;

    public ManageWindow() throws SQLException {
        initComponents();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        reloadClients();
        setTableDesign();
    }

    private void initComponents() throws SQLException {
        topPanel = new JPanel();
        manage = new JLabel();
        comboBox = new JComboBox<String>();
        bottomPanel = new JPanel();
        tableCombobox = new LinkedHashMap<>();
        leftPanel = new JPanel();
        filterPanel = new JPanel();
        searchField = new JTextField();
        detailsButton = new JButton();
        updateButton = new JButton();
        deleteButton = new JButton();
        addButton = new JButton();
        rightPanel = new JPanel();
        comboBox.setSelectedItem(0);

        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(1300, 700));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        topPanel.setMaximumSize(new Dimension(150, 70));
        topPanel.setMinimumSize(new Dimension(150, 70));
        topPanel.setPreferredSize(new Dimension(150, 70));
        topPanel.setBackground(Color.black);
        topPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        manage.setText("Manage");
        manage.setPreferredSize(new Dimension(150, 50));
        manage.setAlignmentX(0.5F);
        manage.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        manage.setHorizontalAlignment(SwingConstants.CENTER);
        manage.setForeground(Color.white);
        manage.setBorder(new EmptyBorder(5, 10, 5, 5));
        topPanel.add(manage);

        comboBox.setPreferredSize(new Dimension(150, 30));
        addToCombobox();
        comboBox.setSelectedIndex(0);
        mainComboBoxAction();
        topPanel.add(comboBox);
        contentPane.add(topPanel, BorderLayout.PAGE_START);

        {
            bottomPanel.setMaximumSize(new Dimension(1300, 150));
            bottomPanel.setMinimumSize(new Dimension(1300, 150));
            bottomPanel.setPreferredSize(new Dimension(1300, 150));
            bottomPanel.setBackground(Color.white);
            bottomPanel.setLayout(new BorderLayout());

            {
                leftPanel.setMaximumSize(new Dimension(300, 700));
                leftPanel.setPreferredSize(new Dimension(300, 700));
                leftPanel.setMinimumSize(new Dimension(300, 700));
                leftPanel.setBackground(new Color(66, 122, 161));
                leftPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

                {
                    filterPanel.setMaximumSize(new Dimension(400, 150));
                    filterPanel.setBackground(new Color(66, 122, 161));
                    filterPanel.setMinimumSize(null);
                    filterPanel.setBorder(new EmptyBorder(10, 5, 5, 5));
                    filterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

                    searchField.setMaximumSize(new Dimension(100, 40));
                    searchField.setPreferredSize(new Dimension(250, 40));
                    searchField.setMinimumSize(new Dimension(100, 40));
                    searchField.setToolTipText("filter expression");
                    filterPanel.add(searchField);

                }
                leftPanel.add(filterPanel);

                detailsButton.setText("Display details");
                detailsButton.setAlignmentX(0.5F);
                detailsButton.setMaximumSize(new Dimension(150, 60));
                detailsButton.setBackground(new Color(235, 242, 250));
                leftPanel.add(detailsButton);
                leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

                updateButton.setText("Update");
                updateButton.setAlignmentX(0.5F);
                updateButton.setMaximumSize(new Dimension(150, 60));
                updateButton.setBackground(new Color(235, 242, 250));
                leftPanel.add(updateButton);
                leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

                deleteButton.setText("Delete");
                deleteButton.setAlignmentX(0.5F);
                deleteButton.setMaximumSize(new Dimension(150, 60));
                leftPanel.add(deleteButton);
                leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

                addButton.setText("Add");
                addButton.setAlignmentX(0.5F);
                addButton.setMaximumSize(new Dimension(150, 60));
                leftPanel.add(addButton);
            }
            bottomPanel.add(leftPanel, BorderLayout.CENTER);

            {
                rightPanel.setBackground(new Color(235, 242, 250));
                rightPanel.setPreferredSize(new Dimension(1000, 0));
                rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
                rightPanel.setLayout(new BorderLayout());
                rightPanel.add(new JScrollPane(table), BorderLayout.CENTER);
            }
            bottomPanel.add(rightPanel, BorderLayout.LINE_END);
        }
        contentPane.add(bottomPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    public void reloadClients() throws SQLException {
        table = Requests.showClientsTable();
        TableRowFilter.create(searchField, table);
        Actions.setDetailOrUpdateClient(false,detailsButton,table,this);
        Actions.setDetailOrUpdateClient(true,updateButton,table,this);
        Actions.setDeleteButtonAction(deleteButton,"client",table,this);
        Actions.addClientAction(addButton,this);
        setTableDesign();
        update();
    }

    public void reloadAddress() throws SQLException {
        table = Requests.showAddresses();
        TableRowFilter.create(searchField, table);
        Actions.setDetailOrUpdateAddress(false,detailsButton,table,this);
        Actions.setDetailOrUpdateAddress(true,updateButton,table,this);
        Actions.setDeleteButtonAction(deleteButton, "address", table, this);
        Actions.addAddressAction(addButton,this);
        setTableDesign();
        update();
    }

    public void reloadAirport() throws SQLException {
        table = Requests.readAirports();
        TableRowFilter.create(searchField, table);
        Actions.setDetailOrUpdateAirport(false, detailsButton, table,this);
        Actions.setDetailOrUpdateAirport(true, updateButton, table,this);
        Actions.addAirportAction(addButton, this);
        Actions.setDeleteButtonAction(deleteButton,"airport",table,this);
        setTableDesign();
        update();
    }

    public void reloadPlane() throws SQLException {
        table = Requests.readPlaneTable();
        TableRowFilter.create(searchField, table);
        Actions.setDetailOrUpdatePlane(false,detailsButton, table, this);
        Actions.setDetailOrUpdatePlane(true,updateButton, table, this);
        Actions.addPlaneAction(addButton, this);
        Actions.setDeleteButtonAction(deleteButton,"plane",table,this);
        setTableDesign();
        update();
    }

    public void reloadAirline() throws SQLException {
        table = Requests.readAirlineTable();
        TableRowFilter.create(searchField, table);
        Actions.addAirlineAction(addButton,this);
        Actions.setDetailAirline(detailsButton,table);
        Actions.setUpdateAirline(updateButton,table,this);
        Actions.setDeleteButtonAction(deleteButton,"airline",table,this);
        setTableDesign();
        update();
    }

    public void reloadPilot() throws SQLException {
        table = Requests.readPilots();
        TableRowFilter.create(searchField, table);
        Actions.setDetailOrUpdatePilot(false,detailsButton, table, this);
        Actions.setDetailOrUpdatePilot(true,updateButton, table, this);
        Actions.addPilotAction(addButton, this);
        Actions.setDeleteButtonAction(deleteButton,"pilot",table,this);
        setTableDesign();
        update();
    }

    public void reloadFlight() throws SQLException {
        table = Requests.readFlights();
        TableRowFilter.create(searchField, table);
        Actions.setDetailOrUpdateFlight(false, detailsButton, table, this);
        Actions.setDetailOrUpdateFlight(true,updateButton, table, this);
        Actions.addFlightAction(addButton, this);
        Actions.setDeleteButtonAction(deleteButton,"flight",table,this);
        setTableDesign();
        update();
    }

    public void reloadBooking() throws SQLException {
        table = Requests.readBookings();
        TableRowFilter.create(searchField, table);
        Actions.setDetailBooking(detailsButton, table, this);
        Actions.setUpdateBooking(updateButton,table,this);
        Actions.setDeleteButtonAction(deleteButton,"booking",table, this);
        Actions.addBookingAction(addButton, this);
        setTableDesign();
        update();
    }

    public void reloadClass() throws SQLException {
        table = Requests.readClassTable();
        TableRowFilter.create(searchField, table);
        Actions.setDetailOrUpdateClass(false, detailsButton, table, this);
        Actions.setDetailOrUpdateClass(true, updateButton, table, this);
        Actions.addClassAction(addButton, this);
        Actions.setDeleteButtonAction(deleteButton,"class",table,this);
        setTableDesign();
        update();
    }

    public void reloadLuggage() throws SQLException {
        table = Requests.readLuggageTable();
        TableRowFilter.create(searchField, table);
        Actions.setDetailOrUpdateLuggage(false, detailsButton, table, this);
        Actions.setDetailOrUpdateLuggage(true, updateButton, table, this);
        Actions.addLuggageAction(addButton, this);
        Actions.setDeleteButtonAction(deleteButton,"luggage",table,this);
        setTableDesign();
        update();
    }

    public void mainComboBoxAction() {
        comboBox.addActionListener(e-> {
            switch (comboBox.getSelectedIndex()) {
                case 0:
                    try {
                        reloadClients();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 1:
                    try {
                        reloadAddress();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        reloadAirport();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        reloadPlane();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        reloadAirline();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        reloadPilot();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                        reloadFlight();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 7:
                    try {
                        reloadBooking();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 8:
                    try {
                        reloadLuggage();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 9:
                    try {
                        reloadClass();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                default:
                    return;
            }
            update();
        });
    }

    private void update() {
        TableRowFilter.create(searchField, table);
        rightPanel.removeAll();
        rightPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void addToCombobox() {
        tableCombobox.put(1, "Clients");
        tableCombobox.put(2, "Airport Addresses");
        tableCombobox.put(3, "Airports");
        tableCombobox.put(4, "Planes");
        tableCombobox.put(5, "Airlines");
        tableCombobox.put(6, "Pilots");
        tableCombobox.put(7, "Flights");
        tableCombobox.put(8, "Bookings");
        tableCombobox.put(9, "Luggages");
        tableCombobox.put(10, "Classes");

        for (int i = 1; i <= tableCombobox.size(); i++) {
            comboBox.addItem(tableCombobox.get(i));
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater((() -> {
            try {
                manageWindow = new ManageWindow();
                manageWindow.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
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
