import tableFrames.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Actions {

    public static void detailsClientBA ( JButton jButton, JTable jTable) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            Object id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            String name = jTable.getValueAt(row, 0).toString();
            String last = jTable.getValueAt(row, 1).toString();
            String email = jTable.getValueAt(row, 2).toString();
            String password = jTable.getValueAt(row, 3).toString();
            String birthDate = jTable.getValueAt(row, 4).toString();
            new ClientDetailsFrame(name,last,email,password,birthDate);
        });
    }

    public static void detailsAddresses (JButton jButton, JTable jTable) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            Object id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            String country = jTable.getValueAt(row, 0).toString();
            String city = jTable.getValueAt(row, 1).toString();
            String postal = jTable.getValueAt(row, 2).toString();
            String street = jTable.getValueAt(row, 3).toString();
            String number = jTable.getValueAt(row, 4).toString();
            new AddressDetailsFrame(country,city,postal,street,number);
        });
    }

    public static void detailsFlight(JButton jButton, JTable jTable) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            String from = jTable.getValueAt(row, 0).toString();
            String to = jTable.getValueAt(row, 1).toString();
            String dep = jTable.getValueAt(row, 3).toString() + " "+ jTable.getValueAt(row,2).toString().split("\\.")[0];
            String arr = jTable.getValueAt(row, 5).toString() + " "+ jTable.getValueAt(row, 4).toString().split("\\.")[0];
            String price = (String) jTable.getValueAt(row, 6);
            new FlightDetailsFrame(from,to,dep,arr,price);
        });
    }

    public static void detailsAirport (JButton jButton, JTable jTable) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            //Object id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            String name = jTable.getValueAt(row, 0).toString();
            String code = jTable.getValueAt(row, 1).toString();
            String address = jTable.getValueAt(row, 2).toString();
            try {
                new AirportDetailsFrame(name,code,address);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void detailsAirline (JButton jButton, JTable jTable) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            int id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            String name = jTable.getValueAt(row, 0).toString();
            String code = jTable.getValueAt(row, 1).toString();
            try {
                ResultSet rs = Requests.readTableByRequest("select brand,model,planes_quantity from plane_airline\n" +
                        "inner join airline on airlineID = plane_airline.airline_id\n" +
                        "inner join plane on planeID = plane_airline.plane_id\n" +
                        "where airline_id=" + id);
                List<String> x = getPlanesList(rs);
                String[] list = x.toArray(new String[0]);
                new AirlineDetailsFrame(name,code,list);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        });
    }

    public static void detailsPlane (JButton jButton, JTable jTable) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            String brand = jTable.getValueAt(row, 0).toString();
            String model = jTable.getValueAt(row, 1).toString();
            new PlaneDetailsFrame(brand,model);
        });
    }

    public static void detailsPilot(JButton jButton, JTable jTable) {
        removeActions(jButton);
        jButton.addActionListener(e -> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            int id = Integer.parseInt(jTable.getModel().getValueAt(jTable.convertRowIndexToModel(row), 0).toString());
            String name = jTable.getValueAt(row, 0).toString() + " " + jTable.getValueAt(row, 1).toString();
            String date = jTable.getValueAt(row, 2).toString();
            try {
                ResultSet rs = Requests.readTableByRequest("select airline.name, airline.code from airline\n" +
                        "inner join pilot on pilot.airline_id = airline.airlineID\n" +
                        "where pilotID=" + id);
                while (rs.next()) {
                    String airline = rs.getString(1) + " (" + rs.getString(2) + ")";
                    new PilotDetailsFrame(name, date, airline);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void detailsLuggage(JButton jButton, JTable jTable) {
        removeActions(jButton);
        jButton.addActionListener(e-> {
            if (jTable.getSelectedRow() == -1) return;
            int row = jTable.getSelectedRow();
            String name = jTable.getValueAt(row, 0).toString();
            String price = jTable.getValueAt(row, 1).toString();
            String height = jTable.getValueAt(row, 2).toString();
            String weight = jTable.getValueAt(row, 3).toString();
        });
    }

    public static void addAirportAction (JButton jButton, ManageWindow mw) {
        for (ActionListener x : jButton.getActionListeners())
            jButton.removeActionListener(x);
        jButton.addActionListener(e -> {
            try {
                new AirportDetailsFrame().addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        try {
                            mw.reloadAirline();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static List<String> getPlanesList(ResultSet rs) throws SQLException {
        List<String> list = new ArrayList<>();
        while (rs.next()) {
            String x = rs.getString(1) + " - " + rs.getString(2) + "  (Quantity: " + rs.getInt(3) + ")";
            System.out.println(x);
            list.add(x);
        }
        return list;
    }

    public static JButton removeActions(JButton jButton) {
        for (ActionListener x : jButton.getActionListeners())
            jButton.removeActionListener(x);

        return jButton;
    }

}
