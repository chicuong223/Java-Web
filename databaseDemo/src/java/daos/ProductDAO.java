/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBConnect;

/**
 *
 * @author chiuy
 */
public class ProductDAO {
    public ArrayList<Product> getAllProducts() throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM Products";
        
        ArrayList<Product> list = new ArrayList<>();
        
        try {
            con = DBConnect.makeConnection();
            if(con != null){
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                
                while(rs.next()){
                    String id = rs.getString("ProductId");
                    String proName = rs.getString("ProductName");
                    String proDesc = rs.getString("ProductDescription");
                    int quantity = rs.getInt("Quantity");
                    double price = rs.getDouble("Price");
                    String imageURL = rs.getString("ImageUrl");
                    
                    Product pro = new Product(id, proName, proDesc, quantity, price, imageURL);
                    list.add(pro);
                }
            }
            
        } 
        finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(con != null) con.close();
        }
        return list;
    }
    
    public boolean createProduct(Product pd){
        Connection con = null;
        PreparedStatement ps =null;
        String sql = "insert into Products(ProductId," +
                "ProductName, ProductDescription, Quantity, Price, ImageURL)" +
                "values(?, ?, ?, ?, ?, ?)";
        try {
            con = DBConnect.makeConnection();
            if(con != null){
                ps = con.prepareStatement(sql);
                ps.setString(1, pd.getId());
                ps.setString(2, pd.getProName());
                ps.setString(3, pd.getProDesc());
                ps.setInt(4, pd.getQuantity());
                ps.setDouble(5, pd.getPrice());
                ps.setString(6, pd.getImageURL());
                
                ps.executeUpdate();
                return true;
            }
        } 
        catch (Exception e) {
            return false;
        }
        return false;
    }
    
    public Product getProductById(String proId) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from Products where ProductId = ?";
        try {
            con = DBConnect.makeConnection();
            if(con != null){
                ps = con.prepareStatement(sql);
                ps.setString(1, proId);     //gán tham số 1 là biến truyền vô
                rs = ps.executeQuery();
                if(rs.next()){
                    String id = rs.getString("ProductId");
                    String name = rs.getString("ProductName");
                    String description = rs.getString("ProductDescription");
                    int quantity = rs.getInt("Quantity");
                    double price = rs.getDouble("Price");
                    String imageURL = rs.getString("ImageURL");
                    Product p = new Product(id, name, proId, quantity, price, imageURL);
                    return p;
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
    
    public boolean updateProduct(Product pro) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "update Products set ProductName=?, ProductDescription=?,"
                + "Quantity=?, Price=?, imageURL=? where ProductId=?";
        try {
            con = DBConnect.makeConnection();
            if(con != null){
                ps = con.prepareStatement(sql);
                ps.setString(1, pro.getProName());
                ps.setString(2, pro.getProDesc());
                ps.setInt(3, pro.getQuantity());
                ps.setDouble(4, pro.getPrice());
                ps.setString(5, pro.getImageURL());
                ps.setString(6, pro.getId());
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
    
    public boolean deleteProduct(String id) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM Products WHERE ProductId=?";
        
        try {
            con = DBConnect.makeConnection();
            if(con != null){
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                ps.executeUpdate();
                return true;
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally{
            if(ps != null){
                ps.close();
            }
            if(con !=null){
                con.close();
            }
        }
        return false;
    }
}
