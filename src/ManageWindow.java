import java.awt.*;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import javax.swing.*;
import javax.swing.border.*;

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
    private JButton searchButton;
    private JButton detailsButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton addButton;
    private JPanel rightPanel;
    private JTable table;

    public ManageWindow() throws SQLException {
        initComponents();
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
        searchButton = new JButton();
        detailsButton = new JButton();
        updateButton = new JButton();
        deleteButton = new JButton();
        addButton = new JButton();
        rightPanel = new JPanel();
        table = Requests.showTable("client");

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

        manage.setText("Zarządzaj");
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
                leftPanel.setBackground(Color.red);
                leftPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
                leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

                {
                    filterPanel.setMaximumSize(new Dimension(400, 150));
                    filterPanel.setBackground(Color.red);
                    filterPanel.setMinimumSize(null);
                    filterPanel.setBorder(new EmptyBorder(10, 5, 5, 5));
                    filterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

                    searchField.setMaximumSize(new Dimension(100, 40));
                    searchField.setPreferredSize(new Dimension(250, 40));
                    searchField.setMinimumSize(new Dimension(100, 40));
                    searchField.setToolTipText("filter expression");
                    filterPanel.add(searchField);

                    searchButton.setMaximumSize(new Dimension(150, 40));
                    searchButton.setText("Search");
                    filterPanel.add(searchButton);
                }
                leftPanel.add(filterPanel);

                detailsButton.setText("Display details");
                detailsButton.setAlignmentX(0.5F);
                detailsButton.setMaximumSize(new Dimension(150, 60));
                leftPanel.add(detailsButton);
                leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));

                updateButton.setText("Update");
                updateButton.setAlignmentX(0.5F);
                updateButton.setMaximumSize(new Dimension(150, 60));
                updateButton.setBackground(Color.pink);
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
                rightPanel.setBackground(Color.green);
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

    public void mainComboBoxAction() {
        comboBox.addActionListener(e-> {
            System.out.println(comboBox.getSelectedIndex());
            switch (comboBox.getSelectedIndex()) {
                case 0:
                    try {
                        table = Requests.showClientsTable();
                        Actions.detailsClientBA(detailsButton,table);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 1:
                    try {
                        table = Requests.showAddresses();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        table = Requests.readAirports();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        table = Requests.readPlaneTable();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        table = Requests.readAirlineTable();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        table = Requests.readPilots();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                        table = Requests.readFlights();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 7:
                    try {
                        table = Requests.showTable("booking");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 8:
                    try {
                        table = Requests.readLuggageTable();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 9:
                    try {
                        table = Requests.readClassTable();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 10:
                    System.out.println(comboBox.getSelectedIndex());
                    try {
                        table = Requests.showPlaneAirlineTable();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    break;
                default:
                    return;
            }
            Actions.detailsClientBA(detailsButton,table);
            rightPanel.removeAll();
            rightPanel.add(new JScrollPane(table), BorderLayout.CENTER);
            rightPanel.revalidate();
            rightPanel.repaint();
        });
    }
    private void addToCombobox() {
        tableCombobox.put(1, "clients");
        tableCombobox.put(2, "airport addresses");
        tableCombobox.put(3, "airports");
        tableCombobox.put(4, "planes");
        tableCombobox.put(5, "airlines");
        tableCombobox.put(6, "pilots");
        tableCombobox.put(7, "flights");
        tableCombobox.put(8, "bookings");
        tableCombobox.put(9, "luggages");
        tableCombobox.put(10, "classes");
        tableCombobox.put(11, "airplanes in airlines");

        for (int i = 1; i <= tableCombobox.size(); i++) {
            comboBox.addItem(tableCombobox.get(i));
        }
    }

    public static void main(String[] args) {
        //setUndecorated(true);
        //mainWindow.setVisible(true);
        EventQueue.invokeLater((() -> {
            try {
                manageWindow = new ManageWindow();
                manageWindow.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
    }
}
