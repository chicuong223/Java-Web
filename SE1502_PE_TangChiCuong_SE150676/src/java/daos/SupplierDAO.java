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
import java.util.ArrayList;
import utils.DBContext;

/**
 *
 * @author chiuy
 */
public class SupplierDAO {
    public Supplier getSupplierIdSupCode(String supCode){
        DBContext db = new DBContext();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Supplier sup = null;
        try {
            con = db.getConnection();
            if(con != null){
                ps = con.prepareStatement("SELECT * FROM Suppliers WHERE SupCode=?");
                ps.setString(1, supCode);
                rs = ps.executeQuery();
                if(rs.next()){
                    String supName = rs.getString("supName");
                    String address = rs.getString("Address");
                    boolean collaborating = rs.getBoolean("Collaborating");
                    sup = new Supplier(supCode, supName, address, collaborating);
                }
            }
        } catch (Exception e) {
        }
        return sup;
    }
    
    public ArrayList<Supplier> getAllSuppliers(){
        DBContext db = new DBContext();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Supplier> lst = new ArrayList<>();
        try {
            con = db.getConnection();
            if(con != null){
                ps = con.prepareStatement("SELECT * FROM Suppliers");
                rs = ps.executeQuery();
                while(rs.next()){
                    String supCode = rs.getString("SupCode");
                    String supName = rs.getString("supName");
                    String address = rs.getString("Address");
                    boolean collaborating = rs.getBoolean("Collaborating");
                    Supplier sup = new Supplier(supCode, supName, address, collaborating);
                    lst.add(sup);
                }
            }
        } catch (Exception e) {
        }
        return lst;
    }
    
    public static void main(String[] args) {
        SupplierDAO dao = new SupplierDAO();
        System.out.println(dao.getSupplierIdSupCode("1231").isCollaborating());
    }
}
