/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import DTOS.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Utils.DBUtils;

/**
 *
 * @author chiuy
 */
public class BookDAO{
    public ArrayList<Book> getAllBooks() throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from Books";
        
        ArrayList<Book> lst = new ArrayList<>();
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                    String bookID = rs.getString("BookID");
                    String name = rs.getString("Name");
                    String author = rs.getString("Author");
                    int year = rs.getInt("PublishYear");
                    String description = rs.getString("ShortDescription");
                    String status = rs.getString("Status");
                    String categoryID = rs.getString("CategoryID");
                    
                    Book b = new Book(bookID, name, author, year, description, status, categoryID);
                    lst.add(b);
                }
            }
        }
        catch(Exception e){
            
        }
        finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(con != null) con.close();
            lst.sort(Book.byCategory);
        }
        return lst;
    }
    
    public boolean createBook(Book b) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO Books VALUES(?, ?, ?, ? ,? ,?, ?)";
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                Book foundBook = this.getBookByID(b.getBookID());
                if(foundBook != null) return false;
                else{
                    ps = con.prepareStatement(sql);
                    ps.setString(1, b.getBookID());
                    ps.setString(2, b.getName());
                    ps.setString(3, b.getAuthor());
                    ps.setInt(4, b.getYear());
                    ps.setString(5, b.getDescription());
                    ps.setString(6, b.getStatus());
                    ps.setString(7, b.getCategoryID());

                    ps.executeUpdate();
                    return true;
                }
            }
        }
        catch(Exception e){
            
        }
        finally{
            if(ps != null) ps.close();
            if(con != null) con.close();
        }
        return false;
    }
    
    public Book getBookByID(String id) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Books WHERE BookID=?";
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                rs = ps.executeQuery();
                if(rs.next()){
                    String bookID = rs.getString("BookID");
                    String name = rs.getString("Name");
                    String author = rs.getString("Author");
                    int year = rs.getInt("PublishYear");
                    String description = rs.getString("ShortDescription");
                    String status = rs.getString("Status");
                    String categoryID = rs.getString("CategoryID");
                    Book b = new Book(bookID, name, author, year, description, status, categoryID);
                    return b;
                }
            }
        }
        catch(Exception e){
            
        }
        finally{
            
        }
        return null;
    }
    
    public boolean deleteBook(String bookID) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM Books WHERE BookID=?";
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                ps = con.prepareStatement(sql);
                ps.setString(1, bookID);
                ps.executeUpdate();
                return true;
            }
        }
        catch(Exception e){
            
        }
        finally{
            if(ps != null) ps.close();
            if(con != null) con.close();
        }
        return false;
    }
    
    public boolean updateBook(Book b) throws SQLException{
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "UPDATE Books SET Name=?, Author=?, PublishYear=?, ShortDescription=?, Status=?, CategoryID=? WHERE BookID=?";
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                ps = con.prepareStatement(sql);
                ps.setString(7, b.getBookID());
                ps.setString(1, b.getName());
                ps.setString(2, b.getAuthor());
                ps.setInt(3, b.getYear());
                ps.setString(4, b.getDescription());
                ps.setString(5, b.getStatus());
                ps.setString(6, b.getCategoryID());
                
                ps.executeUpdate();
                return true;
            }
        }
        catch(Exception e){
            
        }
        finally{
            if(ps != null) ps.close();
            if(con != null) con.close();
        }
        return false;
    }
    
    public ArrayList<Book> getBooksByCategory(String catID) throws SQLException{
        ArrayList<Book> bookCatList = new ArrayList<>();
        ArrayList<Book> bookList = this.getAllBooks();
        for (Book book : bookList) {
            if(book.getCategoryID().equals(catID)) bookCatList.add(book);
        }
        return bookCatList;
    }
}
