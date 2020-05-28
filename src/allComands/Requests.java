package allComands;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.sql.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;

public class Requests {

    public static boolean isAdmin(Connection cn, String login, String pass) throws SQLException {
        String sql = "select * from Admin";
        PreparedStatement ps=cn.prepareStatement("select * from admin", ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs=ps.executeQuery();
        while (rs.next()) {
            if (rs.getString("login").equals(login) && rs.getString("password").equals(pass)) {
                return true;
            }
        }
        return false;
    }

    //----------------------------- INSERT -----------------------------

    public static void createAddress(String country, String city, String postal_code, String street, int number) throws SQLException {
        String sql = "insert into address (country, city, postal_code, street, number) values (?, ?, ?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, country);
        statement.setString(2, city);
        statement.setString(3, postal_code);
        statement.setString(4, street);
        statement.setInt(5, number);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new address was inserted successfully!");
        }
    }

    public static void createClient(String first, String last, String email, String password, String birth_date) throws SQLException {
        String sql = "insert into client (first_name, last_name, email, password, birth_date) values (?, ?, ?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, first);
        statement.setString(2, last);
        statement.setString(3, email);
        statement.setString(4, password);
        statement.setString(5, birth_date);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new client was inserted successfully!");
        }
    }

    public static void createAirline(String name, String code) throws SQLException {
        String sql = "insert into airline (name, code) values (?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, code);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new airline was inserted successfully!");
        }
    }

    public static void createPlane(String brand, String model) throws SQLException {
        String sql = "insert into plane (brand, model) values (?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, brand);
        statement.setString(2, model);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new plane was inserted successfully!");
        }
    }

    public static void createAirport(String name, String code, int addressId) throws SQLException {
        String sql = "insert into airport (name, code, address_id) values (?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, code);
        statement.setInt(3, addressId);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new airport was inserted successfully!");
        }
    }

    public static void createPilot(String first, String last, String date, int airlineId) throws SQLException {
        String sql = "insert into pilot (first_name, last_name, employment_date, airline_id) values (?, ?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, first);
        statement.setString(2, last);
        statement.setString(3, date);
        statement.setInt(4, airlineId);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new pilot was inserted successfully!");
        }
    }

    public static void createPlaneAirplane(int planeId, int airlineId, int quantity) throws SQLException {
        String sql = "insert into plane_airline (plane_id, airline_id, planes_quantity) values (?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, planeId);
        statement.setInt(2, airlineId);
        statement.setInt(3, quantity);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new plane-airplane connection was inserted successfully!");
        }
    }

    public static void createClass(String name, float price) throws SQLException {
        String sql = "insert into class (name, price) values (?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setFloat(2, price);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new class was inserted successfully!");
        }
    }

    public static void createLuggage(String name, String price, String height, String weight) throws SQLException {
        String sql = "insert into luggage (name, price, height, weight) values (?, ?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setFloat(2, Float.parseFloat(price));
        statement.setInt(3, Integer.parseInt(height));
        statement.setInt(4, Integer.parseInt(weight));

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new luggage was inserted successfully!");
        }
    }

    public static void createFlight(int depA_id, int arrA_id, int pilotId, int planeId, String depTime, String depDate, String arrTime, String arrDate, int price) throws SQLException {
        String sql = "insert into flight (departureAirport_id, arrivalAirport_id, pilot_id, plane_id, departure_time, departure_date, arrival_time, arrival_date, price) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, depA_id);
        statement.setInt(2, arrA_id);
        statement.setInt(3, pilotId);
        statement.setInt(4, planeId);
        statement.setString(5, depTime);
        statement.setString(6, depDate);
        statement.setString(7, arrTime);
        statement.setString(8, arrDate);
        statement.setInt(9, price);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new flight was inserted successfully!");
        }
    }

    public static void createBooking(int flightId, int clientId, int luggageId, int classId, int seatNum) throws SQLException {
        String sql = "insert into booking (flight_id, client_id, luggage_id, class_id, seat_number) values (?, ?, ?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, flightId);
        statement.setInt(2, clientId);
        statement.setInt(3, luggageId);
        statement.setInt(4, classId);
        statement.setInt(5, seatNum);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new booking was inserted successfully!");
        }
    }

    //----------------------------- READ -----------------------------

    public static JTable showTable(String table) throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readByTableName(table);
        ResultSetMetaData rsmd = rs.getMetaData();
        String[] cells = new String[rsmd.getColumnCount()];

        for (int i = 1 ; i <= rsmd.getColumnCount(); i++) {
            model.addColumn(rsmd.getColumnName(i));
        }
        while (rs.next()) {
            for (int i =1; i <= rsmd.getColumnCount(); i++) {
                cells[i-1] = (rs.getString(rsmd.getColumnName(i)));
            }
            model.addRow(cells);
            Arrays.fill(cells,null);
        }
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(model);
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));

        //jTable.getColumnModel().getColumn(0).setMinWidth(0);
        //jTable.getColumnModel().getColumn(0).setMaxWidth(0);
        //jTable.getColumnModel().getColumn(0).setWidth(0);
        return jTable;
    }

    public static JTable showClientsTable() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readByTableName("client");

        model.addColumn("ClientID");
        model.addColumn("First name");
        model.addColumn("Last name");
        model.addColumn("E-mail");
        model.addColumn("Password");
        model.addColumn("Birth date");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(addRows(rs,model));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        return jTable;
    }

    public static JTable showAddresses() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class getColumnClass(int column) {
                if (column == 5) {
                    return Integer.class;
                } else {
                    return String.class;
                }
            }
        };
        ResultSet rs = Requests.readByTableName("address");
        model.addColumn("addressID");
        model.addColumn("Country");
        model.addColumn("City");
        model.addColumn("Postal code");
        model.addColumn("Street");
        model.addColumn("Number");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        sorter.setComparator(5, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        jTable.setRowSorter(sorter);
        jTable.setModel(addRows(rs,model));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        return jTable;
    }

    public static JTable readClassTable() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readByTableName("class");
        model.addColumn("classID");
        model.addColumn("Name");
        model.addColumn("Price");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(addRows(rs,model));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        return jTable;
    }

    public static JTable readPlaneTable() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readByTableName("plane");
        model.addColumn("planeID");
        model.addColumn("Brand");
        model.addColumn("Model");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(addRows(rs,model));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        return jTable;
    }

    public static JTable readAirlineTable() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readByTableName("airline");
        model.addColumn("airlineID");
        model.addColumn("Name");
        model.addColumn("Code");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(addRows(rs,model));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        return jTable;
    }

    public static JTable readLuggageTable() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readByTableName("luggage");
        model.addColumn("luggageID");
        model.addColumn("Name");
        model.addColumn("Price");
        model.addColumn("Height");
        model.addColumn("Weight");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(addRows(rs,model));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        return jTable;
    }

    public static JTable readAirports() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readTableByRequest("select * from airport");
        model.addColumn("airportID");
        model.addColumn("Airport name");
        model.addColumn("Code");
        model.addColumn("Address");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(addRows(rs,model));
        rs.beforeFirst();
        while (rs.next()) {
            String address = getStringAddress(rs.getInt(4));
            model.setValueAt(address,rs.getRow()-1,3);
        }
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        return jTable;
    }

    public static JTable readPilots() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readTableByRequest("select pilotID, first_name, last_name, employment_date, airline.name from pilot\n" +
                "inner join airline on airline.airlineID = pilot.airline_id");
        model.addColumn("pilotID");
        model.addColumn("First Name");
        model.addColumn("Last name");
        model.addColumn("Employment date");
        model.addColumn("Airline name");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(addRows(rs,model));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        return jTable;
    }



    public static JTable readFlights() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readTableByRequest("select flightID, dep.name as dname, arr.name as aname, departure_time, departure_date, arrival_time, arrival_date, price from flight\n" +
                "inner join airport as dep on dep.airportID = flight.departureAirport_id\n" +
                "inner join airport as arr on arr.airportID = flight.arrivalAirport_id");
        model.addColumn("flightID");
        model.addColumn("Departure Airport");
        model.addColumn("Arrival Airport");
        model.addColumn("Departure time");
        model.addColumn("Departure date");
        model.addColumn("Arrival time");
        model.addColumn("Arrival date");
        model.addColumn("Price");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(addRows(rs,model));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        return jTable;
    }

    public static String getStringAddress(int id) throws SQLException {
        String address = null;
        ResultSet rs = Requests.readTableByRequest("select * from address where addressID = " + id);
        while (rs.next()) {
            if(rs.getObject(5)==null) {
                address = rs.getString(2) + ", " + rs.getString(4) + " " + rs.getString(3) + ", " + rs.getString(6);
            } else {
                address = rs.getString(2) + ", " + rs.getString(4) + " " + rs.getString(3) + ", " + rs.getString(5) +" " + rs.getString(6);
            }
        }
        return address;
    }

    public static String getStringPlane(int id) throws SQLException {
        String plane = null;
        ResultSet rs = Requests.readTableByRequest("select * from plane where planeID=" + id);
        while (rs.next()) {
            plane = rs.getString(2) + " - " + rs.getString(3);
        }
        return plane;
    }

    public static String getStringAirline(int id) throws SQLException {
        String airline = null;
        ResultSet rs = Requests.readTableByRequest("select * from airline where airlineID=" + id);
        while (rs.next()) {
            airline = rs.getString(2) + " (" + rs.getString(3) + ")";
        }
        return airline;
    }

    public static JTable showPlaneAirlineTable() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readTableByRequest("select plane.brand, airline.name, planes_quantity from plane_airline\n" +
                "inner join plane on plane.planeID = plane_airline.plane_id\n" +
                "inner join airline on airline.airlineID = plane_airline.airline_id");
       // model.addColumn("PlaneAirlineID");
        model.addColumn("Plane brand");
        model.addColumn("Airline name");
        model.addColumn("Quantity of planes");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(addRowsForPlaneAirline(rs,model));
       // jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        return jTable;
    }

    public static DefaultTableModel addRowsForPlaneAirline(ResultSet rs, DefaultTableModel model) throws SQLException {
        int j = 1;
        ResultSetMetaData rsmd = rs.getMetaData();
        String[] cells = new String[rsmd.getColumnCount()];
        while (rs.next()) {
            cells[0] = String.valueOf(j);
            j++;
            for (int i = 2; i <= rsmd.getColumnCount(); i++) {
                cells[i - 1] = (rs.getString(rsmd.getColumnName(i)));
            }
            model.addRow(cells);
            Arrays.fill(cells,null);
        }
        return model;
    }


    public static DefaultTableModel addRows(ResultSet rs, DefaultTableModel model) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        String[] cells = new String[rsmd.getColumnCount()];
        while (rs.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                cells[i - 1] = (rs.getString(rsmd.getColumnName(i)));
            }
            model.addRow(cells);
            Arrays.fill(cells,null);
        }
        return model;
    }

    public static LinkedHashMap getAddressesWihtId() throws SQLException {
        int i = 0;
        LinkedHashMap<Integer, Integer> addressesWithId = new LinkedHashMap<>();
        ResultSet rs = readTableByRequest("select addressID from address");
        while (rs.next()) {
            int id = rs.getInt("addressID");
            addressesWithId.put(i,id);
            i++;
        }
        return addressesWithId;
    }

    public static LinkedHashMap getAirportsWithId() throws SQLException {
        int i = 0;
        LinkedHashMap<Integer, Integer> airportsWithID = new LinkedHashMap<>();
        ResultSet rs = readTableByRequest("select airportID from airport");
        while (rs.next()) {
            int id = rs.getInt("airportID");
            airportsWithID.put(i,id);
            i++;
        }
        return airportsWithID;
    }

    public static LinkedHashMap getPilotsWithId() throws SQLException {
        int i = 0;
        LinkedHashMap<Integer, Integer> pilotsWithId = new LinkedHashMap<>();
        ResultSet rs = readTableByRequest("select pilotID from pilot");
        while (rs.next()) {
            int id = rs.getInt("pilotID");
            pilotsWithId.put(i,id);
            i++;
        }
        return pilotsWithId;
    }

    public static LinkedHashMap getPlanesWithId() throws SQLException {
        int i = 0;
        LinkedHashMap<Integer, Integer> planesWithId = new LinkedHashMap<>();
        ResultSet rs = readTableByRequest("select planeID from plane");
        while (rs.next()) {
            int id = rs.getInt("planeID");
            planesWithId.put(i,id);
            i++;
        }
        return planesWithId;
    }

    public static LinkedHashMap getPlanesByAirlineID(int airlineId) throws SQLException {
        int i = 0;
        LinkedHashMap<Integer, Integer> planesWithId = new LinkedHashMap<>();
        ResultSet rs = readTableByRequest("select plane_id from plane_airline where airline_id="+airlineId);
        while (rs.next()) {
            int id = rs.getInt("plane_id");
            planesWithId.put(i,id);
            i++;
        }
        return planesWithId;
    }

    public static LinkedHashMap getAirlinesWithId() throws SQLException {
        int i = 0;
        LinkedHashMap<Integer, Integer> airlinesWithId = new LinkedHashMap<>();
        ResultSet rs = readTableByRequest("select airlineID from airline");
        while (rs.next()) {
            int id = rs.getInt("airlineID");
            airlinesWithId.put(i, id);
            i++;
        }
        return airlinesWithId;
    }

    public static ResultSet readTableByRequest(String sql) throws SQLException {
        Statement st = DBConnection.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        return st.executeQuery(sql);
    }

    public static ResultSet readByTableName(String table) throws SQLException {
        Statement st = DBConnection.getConnection().createStatement();
        return st.executeQuery("select * from " + table);
    }

    public static ResultSet readById(int id, String table) throws SQLException {
        Statement st = DBConnection.getConnection().createStatement();
        return st.executeQuery("select * from " + table +" where "+table+"ID = " + id);
    }

    //----------------------------- UPDATE -----------------------------

    public static void updateClient(int id, String... a) throws SQLException {
        ResultSet rs = Requests.readById(id, "client");
        rs.next();
        if(!a[2].equals(rs.getString(3))) {
            String sql = "update client set email=? WHERE clientID=?";
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            statement.setString(1, a[2]);
            statement.setInt(2,id);
            statement.executeUpdate();
        }
        String sql = "update client set first_name=?, last_name=?, password=?, birth_date=? WHERE clientID=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, a[0]);
        statement.setString(2, a[1]);
        statement.setString(3, a[3]);
        statement.setString(4, a[4]);
        statement.setInt(5, id);
        statement.executeUpdate();
    }

    public static void updateAddress(int id,int i, String... a) throws SQLException {
        String sql = "update address set country=?, city=?, postal_code=?,street=?, number=? WHERE addressID=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, a[0]);
        statement.setString(2, a[1]);
        statement.setString(3, a[2]);
        statement.setString(4, a[3]);
        statement.setInt(5, i);
        statement.setInt(6,id);
        statement.executeUpdate();
    }

    public static void updateAdmin(int id, String login, String password) throws SQLException {
        String sql = "update admin set login=?, password=? WHERE adminID=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, login);
        statement.setString(2, password);
        statement.setInt(3, id);
        statement.executeUpdate();
    }

    public static void updateAirline(int id, String name, String code) throws SQLException {
        String sql = "update airline set name=?, code=? WHERE airlineID=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, code);
        statement.setInt(3, id);
        statement.executeUpdate();
    }

    public static void updatePlane(int id, String brand, String model) throws SQLException {
        String sql = "update plane set brand=?, model=? WHERE planeID=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, brand);
        statement.setString(2, model);
        statement.setInt(3, id);
        statement.executeUpdate();
    }

    public static void updateAirport(int id, String name, String code, int addressId) throws SQLException {
        String sql = "update airport set name=?, code=?, address_id=? WHERE airportID=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, code);
        statement.setInt(3, addressId);
        statement.setInt(4, id);
        statement.executeUpdate();
    }

    public static void updatePilot(int id, String first, String last, String date, int airlineId) throws SQLException {
        String sql = "update pilot set first_name=?, last_name=?, employment_date=?, airline_id=? WHERE pilotID=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, first);
        statement.setString(2, last);
        statement.setString(3, date);
        statement.setInt(4, airlineId);
        statement.setInt(5, id);
        statement.executeUpdate();
    }

    public static void updatePlaneAirline(int planeId, int airlineId, int quantity) throws SQLException {
        String sql = "update plane_airline set planes_quantity=? WHERE plane_id=? and airline_id=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, quantity);
        statement.setInt(2, planeId);
        statement.setInt(3, airlineId);
        statement.executeUpdate();
    }

    public static void updateClass(int id, String name, float price) throws SQLException {
        String sql = "update class set name=?, price=? WHERE classID=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setFloat(2, price);
        statement.setInt(3, id);
        statement.executeUpdate();
    }

    public static void updateLuggage(int id, String name, String price, String height, String weight) throws SQLException {
        String sql = "update luggage set name=?, price=?, height=?, weight=? WHERE luggageID=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setFloat(2, Float.parseFloat(price));
        statement.setInt(3, Integer.parseInt(height));
        statement.setInt(4, Integer.parseInt(weight));
        statement.setInt(5,id);
        statement.executeUpdate();
    }

    public static void updateFlight(int id, int dAId, int aAId, int pilotId, int planeId, String dTime, String dDate, String aTime, String aDate, int price) throws SQLException {
        String sql = "update flight set departureAirport_id=?, arrivalAirport_id=?, pilot_id=?, plane_id=?, departure_time=?, departure_date=?, arrival_time=?, arrival_date=?, price=? WHERE flightID=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, dAId);
        statement.setInt(2, aAId);
        statement.setInt(3, pilotId);
        statement.setInt(4, planeId);
        statement.setString(5, dTime);
        statement.setString(6, dDate);
        statement.setString(7, aTime);
        statement.setString(8, aDate);
        statement.setInt(9, price);
        statement.setInt(10, id);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new flight was updated successfully!");
        }

    }

    public static void updateBooking(int id, int flightId, int clientId, int luggageId,int classId, int seat, String date) throws SQLException {
        String sql = "update booking set flight_id=?, client_id=?, luggage_id=?, class_id=?, seat_number=?, approval_date=? WHERE bookingID=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, flightId);
        statement.setInt(2, clientId);
        statement.setInt(3, luggageId);
        statement.setInt(4, classId);
        statement.setInt(5, seat);
        statement.setString(6, date);
        statement.setInt(7,id);
        statement.executeUpdate();
    }

    //----------------------------- DELETE -----------------------------

    public static void deleteRow(int id, String table){
        try {
            String sql = "delete from " + table +" WHERE "+table+"ID=" + id;
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nie można usunąć czegoś połączonego");
            //e.printStackTrace();
        }
    }

    public static void deleteByCondidion(String table, String condition){
        try {
            String sql = "delete from " + table +" WHERE "+condition;
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Nie można usunąć czegoś połączonego");
            //e.printStackTrace();
        }
    }
}
