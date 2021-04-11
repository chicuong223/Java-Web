/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.RoomDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import utils.DBContext;

/**
 *
 * @author chiuy
 */
public class RoomDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public RoomDAO(){}
    
    private void closeConnection() throws Exception{
        if(rs != null) rs.close();
        if(ps != null) ps.close();
        if(con != null) con.close();
    }
    
    public RoomDTO getRoomByID(String id) throws Exception{
        RoomDTO result = null;
        try{
            String sql = "SELECT RoomName, Building, FloorNumber FROM RoomTBL WHERE RoomID=?";
            DBContext db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                String name = rs.getString("RoomName");
                String building = rs.getString("Building");
                int floor = rs.getInt("FloorNumber");
                result = new RoomDTO(id, name, building, floor);
            }
        }
        finally{
            closeConnection();
        }
        return result;
    }
    
    public List<RoomDTO> getAllRooms() throws Exception{
        List<RoomDTO> result = null;
        try{
            String sql = "SELECT RoomID, RoomName, Building, FloorNumber FROM RoomTBL";
            DBContext db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                String id = rs.getString("RoomID");
                String name = rs.getString("RoomName");
                String building = rs.getString("Building");
                int floor = rs.getInt("FloorNumber");
                RoomDTO dto = new RoomDTO(id, name, building, floor);
                result.add(dto);
            }
        }
        finally{
            closeConnection();
        }
        return result;
    }
}
