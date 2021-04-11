/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author chiuy
 */
public class DBUtils {
    public static Connection makeConnection() throws SQLException, NamingException{
//        try{
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  //Create an object to get jdbc driver
//            String url = "jdbc:sqlserver://localhost:1433;databaseName=SE1502_Workshop1_TangChiCuong";  //an url linked to database
//            
//            Connection con = DriverManager.getConnection(url, "chicuong", "879546231"); //a connection object to connect to database
//            return con;
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        return null;
        Context context = new InitialContext();
        Context end = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) end.lookup("DBConnection");
        Connection con = ds.getConnection();
        return con;
    }
    
}
