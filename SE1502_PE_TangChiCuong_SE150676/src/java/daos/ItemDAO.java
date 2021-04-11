/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Item;
import dtos.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBContext;

/**
 *
 * @author chiuy
 */
public class ItemDAO {

    public ArrayList<Item> getAllItems() throws Exception {
        DBContext db = new DBContext();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Item> lst = new ArrayList<>();
        try {
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement("SELECT * FROM Items");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String itemCode = rs.getString("ItemCode");
                    String itemName = rs.getString("ItemName");
                    String unit = rs.getString("Unit");
                    int price = rs.getInt("Price");
                    boolean supplying = rs.getBoolean("Supplying");
                    String supCode = rs.getString("SupCode");

                    Supplier sup = new SupplierDAO().getSupplierIdSupCode(supCode);
                    Item item = new Item(itemCode, itemName, unit, price, supplying, sup);
                    lst.add(item);
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return lst;
    }

    public boolean delete(String itemCode) throws SQLException {
        DBContext db = new DBContext();
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement("DELETE FROM Items WHERE ItemCode=?");
                ps.setString(1, itemCode);
                ps.executeUpdate();
                return true;
            }
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public Item getItemByCode(String itemCode) throws Exception {
        DBContext db = new DBContext();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Item item = null;
        try {
            con = db.getConnection();
            if (con != null) {
                ps = con.prepareStatement("SELECT * FROM Items WHERE ItemCode=?");
                ps.setString(1, itemCode);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String itemName = rs.getString("ItemName");
                    String unit = rs.getString("Unit");
                    int price = rs.getInt("Price");
                    boolean supplying = rs.getBoolean("Supplying");
                    String supCode = rs.getString("SupCode");
                    Supplier sup = new SupplierDAO().getSupplierIdSupCode(supCode);
                    item = new Item(itemCode, itemName, unit, price, supplying, sup);
                }
            }
        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return item;
    }

    public boolean addItem(Item i) throws SQLException {
        DBContext db = new DBContext();
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = db.getConnection();
            if(con != null){
                ps = con.prepareStatement("INSERT INTO Items(ItemCode, ItemName, Unit, Price, Supplying,SupCode)"
                        + "VALUES(?,?,?,?,?,?)");
                ps.setString(1, i.getItemCode());
                ps.setString(2, i.getItemName());
                ps.setString(3, i.getUnit());
                ps.setInt(4, i.getPrice());
                ps.setBoolean(5, i.isSupplying());
                ps.setString(6, i.getSup().getSupCode());
                ps.executeUpdate();
                return true;
            }
        } catch (Exception e) {
        }
        finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

}
