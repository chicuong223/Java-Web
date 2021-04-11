/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Laptop;
import dtos.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import db.DBConnect;

/**
 *
 * @author chiuy
 */
public class LaptopDAO {
    public ArrayList<Laptop> getAllLaptops(){
        ArrayList<Laptop> lst = new ArrayList<>();
        DBConnect dbCon = new DBConnect();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SupplierDAO dao = new SupplierDAO();
        try {
            con = dbCon.makeConnection();
            if(con != null){
                ps = con.prepareStatement("SELECT * FROM Laptops");
                rs = ps.executeQuery();
                while(rs.next()){
                    String id = rs.getString("ID");
                    String laptopName = rs.getString("LaptopName");
                    String techInfo = rs.getString("TechnicalInformation");
                    int year = rs.getInt("YearOfManufacture");
                    String producer = rs.getString("Producer");
                    String status = rs.getString("Status");
                    String supplierID = rs.getString("SupplierID");
                    Supplier sup = dao.getSupplierByID(supplierID);
                    Laptop lap = new Laptop(id, laptopName, techInfo, year, producer, status, sup);
                    lst.add(lap);
                }
            }
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
            try {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
                if(con != null) con.close();
            } 
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lst;
    }
    
    public Laptop getLapByID(String id){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Laptop lap = null;
        try {
            DBConnect db = new DBConnect();
            con = db.makeConnection();
            if(con != null){
                ps = con.prepareStatement("SELECT * FROM Laptops WHERE ID=?");
                ps.setString(1, id);
                rs = ps.executeQuery();
                if(rs.next()){
                    String lapName = rs.getString("LaptopName");
                    String techInfo = rs.getString("TechnicalInformation");
                    int year = rs.getInt("YearOfManufacture");
                    String producer = rs.getString("Producer");
                    String status = rs.getString("Status");
                    String supID = rs.getString("SupplierID");
                    SupplierDAO dao = new SupplierDAO();
                    Supplier sup = dao.getSupplierByID(supID);
                    lap = new Laptop(id, lapName, techInfo, year, producer, status, sup);
                }
            }
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
            try {
                if(rs != null) rs.close();
                if(ps != null) ps.close();
                if(con != null) con.close();
            } 
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lap;
    }
    
    public boolean deleteLaptop(Laptop lap){
        Connection con = null;
        PreparedStatement ps = null;
        try {
            DBConnect db = new DBConnect();
            con = db.makeConnection();
            if(con != null){
                ps = con.prepareStatement("DELETE FROM Laptops WHERE ID=?");
                ps.setString(1, lap.getId());
                ps.executeUpdate();
                return true;
            }
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
            try {
                if(ps != null) ps.close();
                if(con != null) con.close();
            } 
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
    
    public boolean updateLaptop(Laptop lap){
        Connection con = null;
        PreparedStatement ps = null;
        try {
            DBConnect db = new DBConnect();
            con = db.makeConnection();
            if(con != null){
                ps = con.prepareStatement("UPDATE Laptops SET LaptopName=?, TechnicalInformation=?, YearOfManufacture=?, Producer=?, Status=?, SupplierID=? WHERE ID=?");
                ps.setString(1, lap.getLaptopName());
                ps.setString(2, lap.getTechnicalInfo());
                ps.setInt(3, lap.getYearOfManufacture());
                ps.setString(4, lap.getProducer());
                ps.setString(5, lap.getStatus());
                ps.setString(6, lap.getSupplier().getSupplierID());
                ps.setString(7, lap.getId());
                ps.executeUpdate();
                return true;
            }
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
            try {
                if(ps != null) ps.close();
                if(con != null) con.close();
            } 
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
    
    public boolean addLaptop(Laptop lap){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO Laptops(ID, LaptopName, Producer, TechnicalInformation, YearOfManufacture, Status, SupplierID)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            DBConnect db = new DBConnect();
            con = db.makeConnection();
            if(con != null){
                ps = con.prepareStatement(sql);
                ps.setString(1, lap.getId());
                ps.setString(2, lap.getLaptopName());
                ps.setString(3, lap.getProducer());
                ps.setString(4, lap.getTechnicalInfo());
                ps.setInt(5, lap.getYearOfManufacture());
                ps.setString(6, lap.getStatus());
                ps.setString(7, lap.getSupplier().getSupplierID());
                ps.executeUpdate();
                return true;
            }
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
            try {
                 if(ps != null) ps.close();
                 if(con != null) con.close();
            } 
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }
}
