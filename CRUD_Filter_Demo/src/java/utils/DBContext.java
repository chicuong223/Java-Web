/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author chiuy
 */
public class DBContext {
    private final String serverName = "localhost";
    private final String dbName = "ComputerManagement";
    private final String portNumber = "1433";
    private final String userID = "chicuong";
    private final String password = "879546231";
    
    public Connection getConnection() throws Exception{
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }
}
