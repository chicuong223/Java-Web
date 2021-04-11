/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.StaffLoginDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBContext;

/**
 *
 * @author chiuy
 */
public class StaffLoginDAO implements Serializable{
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public StaffLoginDAO(){}
    
    private void closeConnection() throws Exception{
        if(rs != null) rs.close();
        if(ps != null) ps.close();
        if(con != null) con.close();
    }
    
    public StaffLoginDTO checkLogin(String username, String password) throws Exception{
        StaffLoginDTO result = null;
        try{
            String sql = "SELECT FullName FROM StaffLoginTBL WHERE UserName=? AND UserPassword=?";
            DBContext db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next()){
                String fullname = rs.getString("FullName");
                result = new StaffLoginDTO(username, fullname);
            }
        }
        finally{
            closeConnection();
        }
        return result;
    }
}
