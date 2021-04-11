/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.HumanType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utilities.ConnectDatabase;

/**
 *
 * @author chiuy
 */
public class HumanTypeDAO {
    public ArrayList<HumanType> getAllTypes() throws SQLException{
        ArrayList<HumanType> lst = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConnectDatabase.makeConnection();
            if(con != null){
                ps = con.prepareStatement("SELECT * FROM HumanType");
                rs = ps.executeQuery();
                while(rs.next()){
                    String typeid = rs.getString("typeid");
                    String typename = rs.getString("typename");
                    
                    HumanType type = new HumanType(typeid, typename);
                    lst.add(type);
                }
            }
        } 
        finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(con != null) con.close();
        }
        return lst;
    }
}
