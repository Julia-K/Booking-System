package allComands;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;

public class Requests {

    //----------------------------- INSERT -----------------------------

    public static void createAdmin(String login, String password) throws SQLException {
        String codedPassword = PasswordUtils.hashing(password);
        String sql = "insert into admin (login, password) values (?, ?)";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, login);
        statement.setString(2, codedPassword);
    }

    public static void createAddress(String country, String city, String postal_code, String street, int number) throws SQLException {
        String sql = "insert into address (country, city, postal_code, street, number) values (?, ?, ?, ?, ?)";
        System.out.println("insert into address (country, city, postal_code, street, number) values ('"+country+"','"+city+"','"+postal_code+"','"+"','"+street+"',"+number+")");

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, country);
        statement.setString(2, city);
        statement.setString(3, postal_code);
        statement.setString(4, street);
        statement.setInt(5, number);
        statement.executeUpdate();
    }

    public static void createClient(String first, String last, String email, String password, String birth_date) throws SQLException {
        String sql = "insert into client (first_name, last_name, email, password, birth_date) values (?, ?, ?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, first);
        statement.setString(2, last);
        statement.setString(3, email);
        statement.setString(4, password);
        statement.setString(5, birth_date);
        statement.executeUpdate();
    }

    public static void createAirline(String name, String code) throws SQLException {
        String sql = "insert into airline (name, code) values (?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, code);
        statement.executeUpdate();
    }

    public static void createPlane(String brand, String model) throws SQLException {
        String sql = "insert into plane (brand, model) values (?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, brand);
        statement.setString(2, model);
        statement.executeUpdate();
    }

    public static void createAirport(String name, String code, int addressId) throws SQLException {
        String sql = "insert into airport (name, code, address_id) values (?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, code);
        statement.setInt(3, addressId);
        statement.executeUpdate();
    }

    public static void createPilot(String first, String last, String date, int airlineId) throws SQLException {
        String sql = "insert into pilot (first_name, last_name, employment_date, airline_id) values (?, ?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, first);
        statement.setString(2, last);
        statement.setString(3, date);
        statement.setInt(4, airlineId);
        statement.executeUpdate();
    }

    public static void createPlaneAirplane(int planeId, int airlineId, int quantity) throws SQLException {
        String sql = "insert into plane_airline (plane_id, airline_id, planes_quantity) values (?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, planeId);
        statement.setInt(2, airlineId);
        statement.setInt(3, quantity);
        statement.executeUpdate();
    }

    public static void createClass(String name, float price) throws SQLException {
        String sql = "insert into class (name, price) values (?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setFloat(2, price);
        statement.executeUpdate();
    }

    public static void createLuggage(String name, String price, String height, String weight) throws SQLException {
        String sql = "insert into luggage (name, price, height, weight) values (?, ?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setFloat(2, Float.parseFloat(price));
        statement.setInt(3, Integer.parseInt(height));
        statement.setInt(4, Integer.parseInt(weight));
        statement.executeUpdate();
    }

    public static void createFlight(int depA_id, int arrA_id, int pilotId, int planeId, String depTime, String depDate, String arrTime, String arrDate, String price) throws SQLException {
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
        statement.setFloat(9, Float.parseFloat(price));
        statement.executeUpdate();
    }

    public static void createBooking(int flightId, int clientId, int luggageId, int classId, int seatNum) throws SQLException {
        String sql = "insert into booking (flight_id, client_id, luggage_id, class_id, seat_number) values (?, ?, ?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, flightId);
        statement.setInt(2, clientId);
        statement.setInt(3, luggageId);
        statement.setInt(4, classId);
        statement.setInt(5, seatNum);
        statement.executeUpdate();
    }

    //----------------------------- READ -----------------------------

        public static JTable readClientsTable() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readTableByRequest("select clientID, first_name, last_name, email from client");

        model.addColumn("ClientID");
        model.addColumn("First name");
        model.addColumn("Last name");
        model.addColumn("E-mail");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(addRows(rs,model));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        return jTable;
    }

    public static JTable readAddressTable() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readTableByRequest("select addressID, country, city from address");
        model.addColumn("addressID");
        model.addColumn("Country");
        model.addColumn("City");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
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

    public static JTable readTableAfterFullSearching(String dateFrom, String dateTo, float priceFrom, float priceTo, int depId, int arrId) throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        try {
            ResultSet rs = readTableByRequest("select flightID, dep.name as dname, arr.name as aname, departure_date, arrival_date from flight\n" +
                    "inner join airport as dep on dep.airportID = flight.departureAirport_id\n" +
                    "inner join airport as arr on arr.airportID = flight.arrivalAirport_id\n" +
                    "where price between " + priceFrom + " and " + priceTo + "\n" +
                    "and dep.airportID = " + depId + " and arr.airportID = " + arrId + "\n" +
                    "and departure_date >= '" + dateFrom + "' \n" +
                    "and arrival_date <= '" + dateTo + "'");
            model.addColumn("flightID");
            model.addColumn("Departure Airport");
            model.addColumn("Arrival Airport");
            model.addColumn("Departure date");
            model.addColumn("Arrival date");
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            jTable.setRowSorter(sorter);
            jTable.setModel(Requests.addRows(rs, model));
            jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return jTable;
    }

    public static JTable readTableAfterSearchingWithoutPrice(String dateFrom, String dateTo, int depId, int arrId) throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        try {
            ResultSet rs = readTableByRequest("select flightID, dep.name as dname, arr.name as aname, departure_date, arrival_date from flight\n" +
                    "inner join airport as dep on dep.airportID = flight.departureAirport_id\n" +
                    "inner join airport as arr on arr.airportID = flight.arrivalAirport_id\n" +
                    "where dep.airportID = " + depId + " and arr.airportID = " + arrId + "\n" +
                    "and departure_date >= '" + dateFrom + "' \n" +
                    "and arrival_date <= '" + dateTo + "'");
            model.addColumn("flightID");
            model.addColumn("Departure Airport");
            model.addColumn("Arrival Airport");
            model.addColumn("Departure date");
            model.addColumn("Arrival date");
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            jTable.setRowSorter(sorter);
            jTable.setModel(Requests.addRows(rs, model));
            jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return jTable;
    }

    public static JTable readTableAfterSearchingWithOnePrice(boolean isFrom, String dateFrom, String dateTo, float price,int depId, int arrId) throws SQLException {
        String sql;
        if(isFrom) {
            sql = "select flightID, dep.name as dname, arr.name as aname, departure_date, arrival_date from flight\n" +
                    "inner join airport as dep on dep.airportID = flight.departureAirport_id\n" +
                    "inner join airport as arr on arr.airportID = flight.arrivalAirport_id\n" +
                    "where price >= " + price + "\n" +
                    "and dep.airportID = " + depId + " and arr.airportID = " + arrId + "\n" +
                    "and departure_date >= '" + dateFrom + "' \n" +
                    "and arrival_date <= '" + dateTo + "'";
        } else {
            sql = "select flightID, dep.name as dname, arr.name as aname, departure_date, arrival_date from flight\n" +
                    "inner join airport as dep on dep.airportID = flight.departureAirport_id\n" +
                    "inner join airport as arr on arr.airportID = flight.arrivalAirport_id\n" +
                    "where price <= " + price + "\n" +
                    "and dep.airportID = " + depId + " and arr.airportID = " + arrId + "\n" +
                    "and departure_date >= '" + dateFrom + "' \n" +
                    "and arrival_date <= '" + dateTo + "'";
        }
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        try {
            ResultSet rs = readTableByRequest(sql);
            model.addColumn("flightID");
            model.addColumn("Departure Airport");
            model.addColumn("Arrival Airport");
            model.addColumn("Departure date");
            model.addColumn("Arrival date");
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            jTable.setRowSorter(sorter);
            jTable.setModel(Requests.addRows(rs, model));
            jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(addRows(rs,model));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        return jTable;
    }

    public static JTable readAirports() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readTableByRequest("select airportID, name, code from airport");
        model.addColumn("airportID");
        model.addColumn("Airport name");
        model.addColumn("Code");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(addRows(rs,model));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        return jTable;
    }

    public static JTable readPilots() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readTableByRequest("select pilotID, first_name, last_name, employment_date from pilot\n" +
                "inner join airline on airline.airlineID = pilot.airline_id");
        model.addColumn("pilotID");
        model.addColumn("First Name");
        model.addColumn("Last name");
        model.addColumn("Employment date");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(addRows(rs,model));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        return jTable;
    }

    public static JTable readBookings() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readTableByRequest("select bookingID, client.email, luggage.name from booking\n" +
                "inner join luggage on luggage.luggageID=luggage_id\n" +
                "inner join client on client.clientID=client_id");
        model.addColumn("bookingID");
        model.addColumn("Client E-mail");
        model.addColumn("Luggage name");
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(addRows(rs,model));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        return jTable;
    }


    public static JTable readFlights() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readTableByRequest("select flightID, dep.name as dname, arr.name as aname, departure_date, arrival_date from flight\n" +
                "inner join airport as dep on dep.airportID = flight.departureAirport_id\n" +
                "inner join airport as arr on arr.airportID = flight.arrivalAirport_id");
        model.addColumn("flightID");
        model.addColumn("Departure Airport");
        model.addColumn("Arrival Airport");
        model.addColumn("Departure date");
        model.addColumn("Arrival date");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(addRows(rs,model));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        return jTable;
    }

    public static JTable readFligthsById(int id) throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readTableByRequest("select flightID, dep.name as dname, arr.name as aname, departure_date, arrival_date from flight\n" +
                "inner join airport as dep on dep.airportID = flight.departureAirport_id\n" +
                "inner join airport as arr on arr.airportID = flight.arrivalAirport_id\n" +
                "where flightID=" + id);
        model.addColumn("flightID");
        model.addColumn("Departure Airport");
        model.addColumn("Arrival Airport");
        model.addColumn("Departure date");
        model.addColumn("Arrival date");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(addRows(rs,model));
        jTable.removeColumn(jTable.getColumnModel().getColumn(0));
        return jTable;
    }

    /*----------------------------- STRINGS -----------------------------*/

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

    /*----------------------------- STATISTICS -----------------------------*/

    public static JTable showClassStatistic() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readTableByRequest("select class.name as 'name', concat(convert(decimal(5,2),(count(class.name) * 100.0 / (select count(*) from booking))),' %') as 'procent' from class\n" +
                "inner join booking on booking.class_id = class.classID\n" +
                "group by name\n" +
                "order by procent desc");
        model.addColumn("Class name");
        model.addColumn("%");
        jTable.setModel(addRows(rs,model));
        return jTable;
    }

    public static JTable showPilotStatistics() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readTableByRequest("select concat(pilot.first_name, ' ',pilot.last_name) as name,\n" +
                " datediff(YEAR,pilot.employment_date,GETDATE()) as ilosc from pilot\n" +
                "order by ilosc desc");
        model.addColumn("Pilot name");
        model.addColumn("Number of years");
        jTable.setModel(addRows(rs,model));
        return jTable;
    }

    public static JTable showClientStatistics() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readTableByRequest("select client.email, count(booking.client_id) as 'liczba' from booking\n" +
                "inner join client on booking.client_id = client.clientID\n" +
                "group by client.email\n" +
                "order by liczba desc");
        model.addColumn("Customer E-mail");
        model.addColumn("Number of reservations");
        jTable.setModel(addRows(rs,model));
        return jTable;
    }

    public static JTable showYearsStatistics() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readTableByRequest("select YEAR(booking.approval_date) as rok, count(*) as liczba from booking\n" +
                "group by YEAR(booking.approval_date)\n" +
                "order by liczba desc");
        model.addColumn("Year");
        model.addColumn("Number of reservations");
        jTable.setModel(addRows(rs,model));
        return jTable;
    }

    public static JTable showMonthStatistics(String year) throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readTableByRequest("select DATENAME(month,booking.approval_date) as miesiac, count (*) as liczba from booking\n" +
                "where YEAR(booking.approval_date)='"+year+"'\n" +
                "group by DATENAME(month,booking.approval_date)\n" +
                "order by miesiac desc");
        model.addColumn("Year");
        model.addColumn("Number of reservations");
        jTable.setModel(addRows(rs,model));
        return jTable;
    }

    /*----------------------------- STATISTICS -----------------------------*/

    public static JTable showPlaneAirlineTable() throws SQLException {
        JTable jTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        ResultSet rs = Requests.readTableByRequest("select plane.brand, airline.name, planes_quantity from plane_airline\n" +
                "inner join plane on plane.planeID = plane_airline.plane_id\n" +
                "inner join airline on airline.airlineID = plane_airline.airline_id");
        model.addColumn("Plane brand");
        model.addColumn("Airline name");
        model.addColumn("Quantity of planes");

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        jTable.setModel(addRowsForPlaneAirline(rs,model));
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

    public static LinkedHashMap getClientsWithId() throws SQLException {
        int i = 0;
        LinkedHashMap<Integer, Integer> clientsWithId = new LinkedHashMap<>();
        ResultSet rs = readTableByRequest("select clientID from client");
        while (rs.next()) {
            int id = rs.getInt("clientID");
            clientsWithId.put(i,id);
            i++;
        }
        return clientsWithId;
    }

    public static LinkedHashMap getLuggageWithId() throws SQLException {
        int i = 0;
        LinkedHashMap<Integer, Integer> luggageWithId = new LinkedHashMap<>();
        ResultSet rs = readTableByRequest("select luggageID from luggage");
        while (rs.next()) {
            int id = rs.getInt("luggageID");
            luggageWithId.put(i,id);
            i++;
        }
        return luggageWithId;
    }

    public static LinkedHashMap getClassesWithId() throws SQLException {
        int i = 0;
        LinkedHashMap<Integer, Integer> classesWithId = new LinkedHashMap<>();
        ResultSet rs = readTableByRequest("select classID from class");
        while (rs.next()) {
            int id = rs.getInt("classID");
            classesWithId.put(i,id);
            i++;
        }
        return classesWithId;
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

    public static LinkedHashMap getAirlinesWithId(int id) throws SQLException {
        int i = 0;
        LinkedHashMap<Integer, Integer> airlinesWithId = new LinkedHashMap<>();
        ResultSet rs = readTableByRequest("select airline_id from plane_airline where plane_id='"+id+"'");
        while (rs.next()) {
            int airlineid = rs.getInt(1);
            airlinesWithId.put(i, airlineid);
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

    public static void updateFlight(int id, int dAId, int aAId, int pilotId, int planeId, String dTime, String dDate, String aTime, String aDate, String price) throws SQLException {
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
        statement.setFloat(9, Float.parseFloat(price));
        statement.setInt(10, id);
        statement.executeUpdate();
    }

    public static void updateBooking(int id, int flightId, int clientId, int luggageId,int classId, int seat) throws SQLException {
        String sql = "update booking set flight_id=?, client_id=?, luggage_id=?, class_id=?, seat_number=? WHERE bookingID=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, flightId);
        statement.setInt(2, clientId);
        statement.setInt(3, luggageId);
        statement.setInt(4, classId);
        statement.setInt(5, seat);
        statement.setInt(6,id);
        statement.executeUpdate();
    }

    //----------------------------- DELETE -----------------------------

    public static void deleteRow(int id, String table){
        try {
            String sql = "delete from " + table +" WHERE "+table+"ID=" + id;
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new Frame(), "You can not delete this. Delete the combined values first.");
        }
    }

    public static void deleteFromPlaneAirline(int airline, int plane) {
        try {
            String sql = "delete from plane_airplane WHERE plane_id =" + plane + " and airline_id =" + airline;
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(new Frame(), "You can not delete this. Delete the combined values first.");
        }
    }

    //----------------------------- LOAD/CHECK -----------------------------


    public static LinkedHashMap loadListOfSeats() throws SQLException {
        LinkedHashMap<Integer, LinkedList<Integer>> listOfSeats = new LinkedHashMap<>();
        ResultSet rs = Requests.readTableByRequest("select flightID from flight");
        while (rs.next()) {
            LinkedList<Integer> linkedList = new LinkedList<>();
            for (int i = 1; i <= 50; i++) {
                linkedList.add(i);
            }
            listOfSeats.put(rs.getInt(1), linkedList);
        }

        ResultSet rs2 = Requests.readTableByRequest("select flight_id, seat_number from booking");
        while (rs2.next()) {
            for (int k : listOfSeats.keySet()) {
                if (rs2.getInt(1) == k) {
                    LinkedList<Integer> x = listOfSeats.get(k);
                    x.remove(Integer.valueOf(rs2.getInt(2)));
                    listOfSeats.put(k, x);
                }
            }
        }
        return listOfSeats;
    }

    public static LinkedHashMap loadListOfSeats(int flight, int seat) throws SQLException {
        LinkedHashMap<Integer, LinkedList<Integer>> listOfSeats = new LinkedHashMap<>();
        ResultSet rs = Requests.readTableByRequest("select flightID from flight");
        while (rs.next()) {
            LinkedList<Integer> linkedList = new LinkedList<>();
            for (int i = 1; i <= 50; i++) {
                linkedList.add(i);
            }
            listOfSeats.put(rs.getInt(1), linkedList);
        }

        ResultSet rs2 = Requests.readTableByRequest("select flight_id, seat_number from booking");
        while (rs2.next()) {
            for (int k : listOfSeats.keySet()) {
                if (rs2.getInt(1) == k) {
                    LinkedList<Integer> x = listOfSeats.get(k);
                    if(k == flight && seat == rs2.getInt(2)) {
                    } else {
                        x.remove(Integer.valueOf(rs2.getInt(2)));
                    }
                    listOfSeats.put(k, x);
                }
            }
        }
        return listOfSeats;
    }

    public static boolean isAdmin(String login, String pass) throws SQLException, NoSuchAlgorithmException, IOException {
        String hash = "";
        ResultSet rs = readTableByRequest("select password from admin where login like '" + login + "'");
        if (rs.next()) {
            hash = rs.getString(1);
        }
        if (hash.equals("")) {
            return false;
        } else {
            return (hash.equals(PasswordUtils.hashing(pass)));
        }
    }
}
