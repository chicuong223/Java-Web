/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import db.DBConnect;

/**
 *
 * @author chiuy
 */
public class UserDAO {
    public User getUserByUsernameAndPassword(String username, String password){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            DBConnect db = new DBConnect();
            con = db.makeConnection();
            if(con != null){
                ps = con.prepareStatement("SELECT * FROM Users WHERE Username=? AND Password=?\n");
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if(rs.next()){
                    String name = rs.getString("Name");
                    user = new User(username, password, name);
                }
            }
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally{
            try {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
                if(con != null) con.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
           
        }
        return user;
    }
}
