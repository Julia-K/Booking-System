import java.awt.*;
import java.util.LinkedHashMap;
import javax.swing.*;
import javax.swing.border.*;

public class ManageWindow extends JFrame {
    private static ManageWindow manageWindow;
    private LinkedHashMap<Integer, String> tableCombobox;
    private JPanel topPanel;
    private JLabel manage;
    private JComboBox comboBox;
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
    private JScrollPane scrollTable;
    private JTable table;

    public ManageWindow() {
        initComponents();
    }

    private void addToCombobox() {
        tableCombobox.put(1, "client");
        tableCombobox.put(2, "address");
        tableCombobox.put(3, "airport");
        tableCombobox.put(4, "plane");
        tableCombobox.put(5, "airline");
        tableCombobox.put(6, "pilot");
        tableCombobox.put(7, "flight");
        tableCombobox.put(8, "booking");
        tableCombobox.put(9, "luggage");
        tableCombobox.put(10, "class");


        for (int i = 1; i < tableCombobox.size(); i++) {
            comboBox.addItem(tableCombobox.get(i));
        }
    }

    private void initComponents() {
        topPanel = new JPanel();
        manage = new JLabel();
        comboBox = new JComboBox();
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
        scrollTable = new JScrollPane();
        table = new JTable();

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

                {
                    table.setPreferredScrollableViewportSize(new Dimension(900, 550));
                    scrollTable.setViewportView(table);
                }
                rightPanel.add(scrollTable, BorderLayout.CENTER);
            }
            bottomPanel.add(rightPanel, BorderLayout.LINE_END);
        }
        contentPane.add(bottomPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
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



    //selectedItem - przekazać jako argument do funkcji i wyswietlać tak tabelę,


}
