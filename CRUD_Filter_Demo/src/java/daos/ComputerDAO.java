/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.ComputerDTO;
import dtos.RoomDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBContext;

/**
 *
 * @author chiuy
 */
public class ComputerDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public ComputerDAO(){}
    
    private void closeConnection() throws Exception{
        if(rs != null) rs.close();
        if(ps != null) ps.close();
        if(con != null) con.close();
    }
    
    public List<ComputerDTO> getAllComputers() throws Exception{
        List<ComputerDTO> result = new ArrayList<>();
        try{
            String sql = "SELECT ComputerID, CPU, HardDisk, RAM, VGA, Monitor, RoomID FROM ComputerTBL";
            DBContext db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                String id = rs.getString("ComputerID");
                String cpu = rs.getString("CPU");
                String hardDisk = rs.getString("HardDisk");
                String ram = rs.getString("RAM");
                String vga = rs.getString("VGA");
                String monitor = rs.getString("Monitor");
                String roomID = rs.getString("RoomID");
                RoomDAO dao = new RoomDAO();
                RoomDTO room = dao.getRoomByID(roomID);
                ComputerDTO computer = new ComputerDTO(id, cpu, hardDisk, ram, vga, monitor, room);
                result.add(computer);
            }
        }
        finally{
            closeConnection();
        }
        return result;
    }
    
    public boolean delete(String id) throws Exception{
        boolean check = false;
        try{
            String sql = "DELETE FROM ComputerTBL WHERE ComputerID=?";
            DBContext db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            check = ps.executeUpdate() > 0;
        }
        finally{
            closeConnection();
        }
        return check;
    }
    
    public boolean insert(ComputerDTO computer) throws Exception{
        boolean check = false;
        try{
            String sql = "INSERT INTO ComputerTBL(ComputerID, CPU, HardDisk, RAM, VGA, Monitor, RoomID"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?)";
            DBContext db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, computer.getId());
            ps.setString(2, computer.getCpu());
            ps.setString(3, computer.getHardDisk());
            ps.setString(4, computer.getRam());
            ps.setString(5, computer.getVga());
            ps.setString(6, computer.getMonitor());
            ps.setString(7, computer.getRoom().getId());
            check = ps.executeUpdate() > 0;
        }
        finally{
            closeConnection();
        }
        return check;
    }
    
    public ComputerDTO getComputerByID(String id) throws Exception{
        ComputerDTO result = null;
        try{
            String sql = "SELECT ComputerID, CPU, HardDisk, RAM, VGA, Monitor, RoomID\n"
                    + "FROM ComputerTBL\n"
                    + "WHERE ComputerID=?\n";
            DBContext db = new DBContext();
            con = db.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                String cpu = rs.getString("CPU");
                String hardDisk = rs.getString("HardDisk");
                String ram = rs.getString("RAM");
                String vga = rs.getString("VGA");
                String monitor = rs.getString("Monitor");
                String roomID = rs.getString("RoomID");
                RoomDAO dao = new RoomDAO();
                RoomDTO room = dao.getRoomByID(roomID);
                result = new ComputerDTO(id, cpu, hardDisk, ram, vga, monitor, room);
            }
        }
        finally{
            closeConnection();
        }
        return result;
    }
    public static void main(String[] args) throws Exception {
        ComputerDAO dao = new ComputerDAO();
        List<ComputerDTO> lst = dao.getAllComputers();
        for (ComputerDTO computerDTO : lst) {
            System.out.println(computerDTO.getId());
        }
    }
}
