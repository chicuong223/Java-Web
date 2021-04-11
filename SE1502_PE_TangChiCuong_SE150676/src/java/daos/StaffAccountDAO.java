/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.StaffAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBContext;

/**
 *
 * @author chiuy
 */
public class StaffAccountDAO {
    public StaffAccount checkLogin(String username, String password) throws SQLException{
        DBContext db = new DBContext();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StaffAccount acc = null;
        try {
            con = db.getConnection();
            if(con != null){
                ps = con.prepareStatement("SELECT * FROM StaffAccount WHERE UserName=? and UserPassword=?");
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if(rs.next()){
                    String fullname = rs.getString("FullName");
                    acc = new StaffAccount(username, password, fullname);
                }
            }
        } catch (Exception e) {
        }
        finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(con != null) con.close();
        }
        return acc;
    }
    
}
