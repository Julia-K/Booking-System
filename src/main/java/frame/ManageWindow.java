package main.java.frame;

import main.java.commands.Requests;
import main.java.utils.TableRowFilter;
import main.java.controller.Actions;

import java.awt.*;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.JTableHeader;

public class ManageWindow extends JFrame {
    private static ManageWindow manageWindow;
    private LinkedHashMap<Integer, String> tableCombobox;
    private JComboBox<String> comboBox;
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
        JPanel dialogPane = new JPanel();
        JButton goButton = new JButton();
        JPanel topPanel = new JPanel();
        JLabel manage = new JLabel();
        comboBox = new JComboBox<String>();
        tableCombobox = new LinkedHashMap<>();
        JPanel leftPanel = new JPanel();
        JPanel filterPanel = new JPanel();
        searchField = new JTextField();
        detailsButton = new JButton();
        updateButton = new JButton();
        deleteButton = new JButton();
        addButton = new JButton();
        rightPanel = new JPanel();
        JLabel filter = new JLabel();
        table = new JTable();
        comboBox.setSelectedItem(0);

        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(900, 600));
        setBackground(new Color(235, 242, 250));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        dialogPane.setBackground(new Color(235, 242, 250));
        dialogPane.setMaximumSize(new Dimension(100, 100));
        dialogPane.setMinimumSize(new Dimension(100, 100));
        dialogPane.setPreferredSize(new Dimension(100, 100));
        dialogPane.setLayout(new BorderLayout());

        topPanel.setPreferredSize(new Dimension(870, 80));
        topPanel.setMinimumSize(new Dimension(870,80));
        topPanel.setBackground(new Color(4, 72, 98));
        topPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        topPanel.setLayout(null);

        manage.setText("Manage");
        manage.setPreferredSize(new Dimension(150, 50));
        manage.setAlignmentX(0.5F);
        manage.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        manage.setHorizontalAlignment(SwingConstants.CENTER);
        manage.setForeground(Color.white);
        manage.setBorder(new EmptyBorder(5, 10, 5, 5));
        topPanel.add(manage);
        manage.setBounds(20, 10, 175, 60);

        comboBox.setPreferredSize(new Dimension(150, 30));
        comboBox.setBackground(Color.white);
        comboBox.setBounds(245, 23, 160, 35);
        ((JLabel)comboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER); //center text in combobox

        addToCombobox();
        comboBox.setSelectedIndex(0);
        mainComboBoxAction();
        topPanel.add(comboBox);

        goButton.setText("Go to statistics \uD83E\uDC46");
        topPanel.add(goButton);
        goButton.setBounds(690, 20, 150, 35);
        goButton.setBackground(new Color(235, 242, 250));
        goButton.addActionListener(e-> {
            StatisticsFrame.main(new String[]{});
            dispose();
        });

        dialogPane.add(topPanel, BorderLayout.PAGE_START);

            leftPanel.setMaximumSize(new Dimension(160, 150));
            leftPanel.setPreferredSize(new Dimension(210, 150));
            leftPanel.setMaximumSize(new Dimension(160, 150));
            leftPanel.setMinimumSize(new Dimension(160, 200));
            leftPanel.setBackground(new Color(235, 242, 250));
            leftPanel.setBorder(BorderFactory.createMatteBorder(10,0,0,0,new Color(235, 242, 250)));
            leftPanel.setLayout(null);

            {
                filterPanel.setMaximumSize(new Dimension(400, 100));
                filterPanel.setBackground(new Color(235, 242, 250));
                filterPanel.setMinimumSize(null);
                filterPanel.setBorder(new EmptyBorder(10, 5, 5, 5));
                filterPanel.setPreferredSize(new Dimension(400, 100));
                filterPanel.setLayout(null);

                filter.setText("Search: ");
                filter.setHorizontalAlignment(SwingConstants.LEFT);
                filter.setBounds(70,10,50,20);

                searchField.setMaximumSize(new Dimension(100, 40));
                searchField.setPreferredSize(new Dimension(100, 40));
                searchField.setMinimumSize(new Dimension(100, 40));
                searchField.setToolTipText("filter expression");
                filterPanel.add(searchField);
                searchField.setBounds(25, 30, 120, 30);

                filter.setText("Search:");
                filterPanel.add(filter);
                filter.setBounds(25, 0, 55, 25);

                filterPanel.setMinimumSize(new Dimension(160, 75));
                filterPanel.setPreferredSize(new Dimension(160, 75));

            }
            leftPanel.add(filterPanel);
            filterPanel.setBounds(20, 20, 175, 85);
            dialogPane.add(leftPanel, BorderLayout.WEST);

            detailsButton.setText("Details");
            detailsButton.setForeground(Color.white);
            detailsButton.setBackground(new Color(5, 102, 141));
            detailsButton.setBounds(45, 125, 120, 45);
            leftPanel.add(detailsButton);

            updateButton.setText("Update");
            updateButton.setForeground(Color.white);
            updateButton.setBackground(new Color(5, 102, 141));
            leftPanel.add(updateButton);
            updateButton.setBounds(45, 200, 120, 45);

            deleteButton.setText("Delete");
            deleteButton.setForeground(Color.white);
            deleteButton.setBackground(new Color(5, 102, 141));
            leftPanel.add(deleteButton);
            deleteButton.setBounds(45, 275, 120, 45);

            addButton.setText("Add");
            addButton.setBackground(new Color(5, 102, 141));
            addButton.setForeground(Color.white);
            leftPanel.add(addButton);
            addButton.setBounds(45, 350, 120, 45);

            {
                rightPanel.setBackground(new Color(235, 242, 250));
                rightPanel.setPreferredSize(new Dimension(428, 600));
                rightPanel.setBorder(new EmptyBorder(10, 10, 0, 0));
                rightPanel.setLayout(new BorderLayout());
                table.setBackground(new Color(235, 242, 250));
                table.setForeground(Color.black);
            }
            dialogPane.add(rightPanel, BorderLayout.CENTER);

        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    public void reloadClients() throws SQLException {
        table = Requests.readClientsTable();
        TableRowFilter.create(searchField, table);
        Actions.setDetailOrUpdateClient(false,detailsButton,table,this);
        Actions.setDetailOrUpdateClient(true,updateButton,table,this);
        Actions.setDeleteButtonAction(deleteButton,"client",table,this);
        Actions.addClientAction(addButton,this);
        setTableDesign();
        update();
    }

    public void reloadAddress() throws SQLException {
        table = Requests.readAddressTable();
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
        table.setPreferredScrollableViewportSize(new Dimension(500, 350));
        TableRowFilter.create(searchField, table);
        rightPanel.removeAll();
        JScrollPane x = new JScrollPane(table);
        x.getViewport().setBackground(new Color(235, 242, 250));
        rightPanel.add(x, BorderLayout.CENTER);
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

    private void setTableDesign() {
        table.setFont(new Font("Roboto", Font.PLAIN, 15));
        table.setRowHeight(30);
        table.setBackground(new Color(235, 242, 250));
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(5, 102, 141));
        tableHeader.setForeground(Color.white);
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

    public static ManageWindow getManageWindow() {
        return manageWindow;
    }

    public static void setManageWindow(ManageWindow manageWindow) {
        ManageWindow.manageWindow = manageWindow;
    }

    public LinkedHashMap<Integer, String> getTableCombobox() {
        return tableCombobox;
    }

    public void setTableCombobox(LinkedHashMap<Integer, String> tableCombobox) {
        this.tableCombobox = tableCombobox;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public void setComboBox(JComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public void setSearchField(JTextField searchField) {
        this.searchField = searchField;
    }

    public JButton getDetailsButton() {
        return detailsButton;
    }

    public void setDetailsButton(JButton detailsButton) {
        this.detailsButton = detailsButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(JButton updateButton) {
        this.updateButton = updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
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
}
