/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Human;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import utilities.ConnectDatabase;

/**
 *
 * @author chiuy
 */
public class HumanDAO {
    public ArrayList<Human> getAllHumans() throws SQLException{
        ArrayList<Human> lst = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConnectDatabase.makeConnection();
            if(con != null){
                ps = con.prepareStatement("SELECT * FROM Human");
                rs = ps.executeQuery();
                SimpleDateFormat dateFormat;
                while(rs.next()){
                    String humanID = rs.getString("humanid");
                    String humanName = rs.getString("humanname");
                    String humanGender = rs.getString("humangender");
                    String humanDOB = rs.getString("humandob");
                    String humanTypeID = rs.getString("typeid");
                    
                    Human h = new Human(humanID, humanName, humanGender, humanDOB, humanTypeID);
                    lst.add(h);
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
    
    public boolean addHuman(Human h) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO Human(humanid,humanname,humangender,humandob,typeid)" + 
                "VALUES(?, ?, ?, ?, ?)";
        try {
            con = ConnectDatabase.makeConnection();
            if(con != null){
                ps = con.prepareStatement(sql);
                ps.setString(1, h.getHumanid());
                ps.setString(2, h.getHumanname());
                ps.setString(3, h.getHumangender());
                ps.setString(4, h.getHumandob());
                ps.setString(5, h.getHumantypeid());
                
                ps.executeUpdate();
                return true;
            }
        }
        finally{
            if(ps != null) ps.close();
            if(con != null) con.close();
        }
        return false;
    }
    
    public boolean deleteHuman(String humanID) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = ConnectDatabase.makeConnection();
            if(con != null){
                ps = con.prepareStatement("DELETE FROM Human WHERE humanid=?");
                ps.setString(1, humanID);
                ps.executeUpdate();
                return true;
            }
        }
        finally{
            if(ps != null) ps.close();
            if(con != null) con.close();
        }
        return false;
    }
    
    public Human getHumanByID(String id) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Human WHERE humanid=?";
        try{
            con = ConnectDatabase.makeConnection();
            if(con != null){
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                rs = ps.executeQuery();
                if(rs.next()){
                  String hid = rs.getString("humanid");
                  String hname = rs.getString("humanname");
                  String hdob = rs.getString("humandob");
                  String hgender = rs.getString("humangender");
                  String typeid = rs.getString("typeid");
                  
                  Human h = new Human(hid, hname, hgender, hdob, typeid);
                  return h;
                }
            }
        }
        finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(con != null) con.close();
        }
        return null;
    }
    
    public boolean updateHuman(Human h) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "UPDATE Human SET humanname=?, humangender=?, humandob=?, typeid=? WHERE humanid=?";
        try{
            con = ConnectDatabase.makeConnection();
            if(con != null){
                ps = con.prepareStatement(sql);
                ps.setString(1, h.getHumanname());
                ps.setString(2, h.getHumangender());
                ps.setString(3, h.getHumandob());
                ps.setString(4, h.getHumantypeid());
                ps.setString(5, h.getHumanid());
                
                ps.executeUpdate();
                return true;
            }
        }
        finally{
            if(ps != null) ps.close();
            if(con != null) con.close();
        }
        return false;
    }
}
