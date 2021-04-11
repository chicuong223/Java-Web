/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DBConnect;
import java.util.ArrayList;

/**
 *
 * @author chiuy
 */
public class SupplierDAO {
    public Supplier getSupplierByID(String id){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBConnect db = new DBConnect();
        try {
            con = db.makeConnection();
            if(con != null){
                ps = con.prepareStatement("SELECT * FROM Suppliers WHERE SupplierID=?");
                ps.setString(1, id);
                rs = ps.executeQuery();
                if(rs.next()){
                    String supplierID = rs.getString("SupplierID");
                    String supplierName = rs.getString("SupplierName");
                    String desc = rs.getString("Description");
                    Supplier sup = new Supplier(supplierID, supplierName, desc);
                    return sup;
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
        return null;
    }
    
    public ArrayList<Supplier> getAllSuppliers(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Supplier> lst = new ArrayList<>();
        try {
            DBConnect db = new DBConnect();
            con = db.makeConnection();
            if(con != null){
                ps = con.prepareStatement("SELECT * FROM Suppliers");
                rs = ps.executeQuery();
                while(rs.next()){
                    String supplierID = rs.getString("SupplierID");
                    String supplierName = rs.getString("SupplierName");
                    String desc = rs.getString("Description");
                    Supplier sup = new Supplier(supplierID, supplierName, desc);
                    lst.add(sup);
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
}
