/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import DTOS.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Utils.DBUtils;
import javax.naming.NamingException;

/**
 *
 * @author chiuy
 */
public class CategoryDAO {
    public ArrayList<Category> getAllCategories() throws SQLException, NamingException{
        ArrayList<Category> lst = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Categories";
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                 ps = con.prepareStatement(sql);
                 rs = ps.executeQuery();
                 while(rs.next()){
                     String categoryID  = rs.getString("CategoryID");
                     String categoryName = rs.getString("CategoryName");
                     String description = rs.getString("Description");
                     
                     Category cat = new Category(categoryID, categoryName, description);
                     lst.add(cat);
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
    
    public static void main(String[] args) throws SQLException, NamingException {
        CategoryDAO dao = new CategoryDAO();
        ArrayList<Category> lst = dao.getAllCategories();
        for (Category category : lst) {
            System.out.println(category.getCategoryID());
        }
    }
}
