package fu.ex.dbhelper;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    public static Connection makeConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=databaseJavaDemo";
            Connection con = DriverManager.getConnection(url, "chicuong", "879546231");
            return con;
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }
}
