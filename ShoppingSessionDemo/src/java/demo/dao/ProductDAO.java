/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.dao;

import demo.dbhelper.DBUtils;
import demo.entities.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chiuy
 */
public class ProductDAO {
    private List<Product> products;
    
    public ProductDAO(){
        try {
            this.products = getAllProducts();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Product> findAll(){
        return this.products;
    }
    
    public Product find(String id){
        for (Product product : this.products) {
            if(product.getId().equalsIgnoreCase(id)){
                return product;
            }
        }
        return null;
    }
    
    public ArrayList<Product> getAllProducts() throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM FlowerProduct";
        ArrayList<Product> lst = new ArrayList<>();
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    String id = rs.getString("ProductId");
                    String name = rs.getString("ProductName");
                    double price = rs.getDouble("ProductPrice");
                    String imgURL = rs.getString("ProductURL");
                    
                    Product p = new Product(id, name, price, imgURL);
                    lst.add(p);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(con != null) con.close();
        }
        return lst;
    }
    
    public static void main(String[] args) throws SQLException {
        ProductDAO dao = new ProductDAO();
        for (Product product : dao.findAll()) {
            System.out.println(product.getId());
            System.out.println(product.getPhoto());
        }
    }
}
