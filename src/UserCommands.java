import java.sql.*;

public class UserCommands {

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

}
