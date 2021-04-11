/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author chiuy
 */
public class DemoProductTableConnection {
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            //JDBC Type 4 Native Protocol
            
            //1. Declare Driver class used to connect to Databse
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            //2. Create a Connection object to connect to Database. 3 Parameters: url, username, password
            String url = "jdbc:sqlserver://localhost:1433;databaseName=databaseJavaDemo";
            Connection c = DriverManager.getConnection(url, "chicuong", "879546231");
            
            //3. Create a Statement or PreparedState to execute query commands
            String sql = "SELECT * FROM Products";
            PreparedStatement ps =  c.prepareStatement(sql);
            
            //4. Create a result object after executing query
            ResultSet rs = ps.executeQuery();
            while(rs.next()){   //while rs can move to next message
                String id = rs.getString("ProductId");
                String proName = rs.getString("ProductName");
                String proDesc = rs.getString("ProductDescription");
                int quantity = rs.getInt("Quantity");
                double price = rs.getDouble("Price");
                String imageURL = rs.getString("ImageUrl");
                System.out.println("ID: " + id + " - " + proName + " - " + proDesc + " - " + quantity + " - " + price);
            }
            
            //5. Close connection
            rs.close();
            ps.close();
            c.close();

        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
