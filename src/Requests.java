import java.sql.*;

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

    public static void createClient(String fisrt, String last, String email, String password, Date birth_date) throws SQLException {
        String sql = "insert into client (first_name, last_name, email, password, birth_date) values (?, ?, ?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, fisrt);
        statement.setString(2, last);
        statement.setString(3, email);
        statement.setString(4, password);
        statement.setDate(5, birth_date);

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

    public static void createPilot(String first, String last, Date date, int airlineId) throws SQLException {
        String sql = "insert into pilot (first_name, last_name, employment_date, airline_id) values (?, ?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, first);
        statement.setString(2, last);
        statement.setDate(3, date);
        statement.setInt(4, airlineId);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new pilot was inserted successfully!");
        }
    }

    public static void createPlaneAirplane(String planeId, String airlineId, int quantity) throws SQLException {
        String sql = "insert into plane_airline (plane_id, airline_id, planes_quantity) values (?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, planeId);
        statement.setString(2, airlineId);
        statement.setInt(3, quantity);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new plane-airplane connection was inserted successfully!");
        }
    }

    public static void createClass(String name, int price) throws SQLException {
        String sql = "insert into class (name, price) values (?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setInt(2, price);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new class was inserted successfully!");
        }
    }

    public static void createLuggage(String name, int price, int height, int weight) throws SQLException {
        String sql = "insert into luggage (name, price, height, weight) values (?, ?, ?, ?)";

        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setInt(2, price);
        statement.setInt(3, height);
        statement.setInt(4, weight);

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
        statement.setDate(6, Date.valueOf(depDate));
        statement.setTime(7, Time.valueOf(arrTime));
        statement.setDate(8, Date.valueOf(arrDate));
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

    //public static ResultSet readToTable(String table, )

    public static ResultSet readTable(String table) throws SQLException {
        Statement st = DBConnection.getConnection().createStatement();
        return st.executeQuery("select * from " + table);
    }

    public static ResultSet readById(int id, String table) throws SQLException {
        System.out.println("select * from " + table + " where " + table + "ID = " + id);
        Statement st = DBConnection.getConnection().createStatement();
        return st.executeQuery("select * from " + table +" where "+table+"ID = " + id);
    }

    //----------------------------- UPDATE -----------------------------

    public static void updateClient(int id, String... a) throws SQLException {
        String sql = "update client set first_name=?, last_name=?, email=?, password=?, birth_date=? WHERE clientID=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, a[0]);
        statement.setString(2, a[1]);
        statement.setString(3, a[2]);
        statement.setString(4, a[3]);
        statement.setString(5, a[4]);
        statement.setInt(6, id);
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
        String sql = "update airport set name=?, code=?, address_id=? WHERE planeID=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, code);
        statement.setInt(3, addressId);
        statement.setInt(4, id);
        statement.executeUpdate();
    }

    public static void updatePilot(int id, String first, String last, String date, int addressId) throws SQLException {
        String sql = "update airport set first_name=?, last_name=?, employment_date=?, airline_id=? WHERE planeID=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, first);
        statement.setString(2, last);
        statement.setString(3, date);
        statement.setInt(4, addressId);
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

    public static void updateClass(int id, String name, int price) throws SQLException {
        String sql = "update class set name=?, price=? WHERE classID=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setInt(2, price);
        statement.setInt(3, id);
        statement.executeUpdate();
    }

    public static void updateLuggage(int id, String name, int price, int height, int weight) throws SQLException {
        String sql = "update luggage set name=?, price=?, height=?, weight=? WHERE luggageID=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setString(1, name);
        statement.setInt(2, price);
        statement.setInt(3, height);
        statement.setInt(4, weight);
        statement.setInt(5,id);
        statement.executeUpdate();
    }

    public static void updateFlight(int id, int dAId, int aAId, int pilotId,int planeId, String dTime, String dDate, String aTime, String aDate, int price) throws SQLException {
        String sql = "update flight set departureAirport_id=?, arrivalAirport_id=?, pilot_id=?, plane_id=?, departure_time=?, departure_date=?, arrival_time=?, arrival_date=?, price=? WHERE flightID=?";
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.setInt(1, dAId);
        statement.setInt(2, aAId);
        statement.setInt(3, pilotId);
        statement.setInt(4, planeId);
        statement.setString(5, dTime);
        statement.setDate(6, Date.valueOf(dDate));
        statement.setString(7, aTime);
        statement.setDate(8, Date.valueOf(aDate));
        statement.setInt(9, price);
        statement.setInt(10, id);
        statement.executeUpdate();

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


    public static void deleteRow(int id, String table) throws SQLException {
        String sql = "delete from " + table +" WHERE "+table+"ID=" + id;
        PreparedStatement statement = DBConnection.getConnection().prepareStatement(sql);
        statement.executeUpdate();
    }
}
