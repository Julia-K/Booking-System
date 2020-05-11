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
        manage.setBorder(new EmptyBorder(5, 10, 5, 5));
        topPanel.add(manage);

        comboBox.setPreferredSize(new Dimension(150, 30));
        addToCombobox();
        topPanel.add(comboBox);

        contentPane.add(topPanel, BorderLayout.PAGE_START);


        //bottomPanel.setMaximumSize(new Dimension(1300, 150));
        //bottomPanel.setMinimumSize(new Dimension(1300, 150));
        bottomPanel.setPreferredSize(new Dimension(1300, 150));
        bottomPanel.setBackground(Color.white);
        bottomPanel.setLayout(new BorderLayout());

        contentPane.add(bottomPanel, BorderLayout.CENTER);
        pack();
        //setLocationRelativeTo(getOwner());
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



    //selectedItem - przekazać jako argument do funkcji i wyswietlać tak tabelę, + lewyPane


}
