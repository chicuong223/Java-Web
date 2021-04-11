/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOS;

import DTOS.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Utils.DBUtils;
import javax.naming.NamingException;

/**
 *
 * @author chiuy
 */
public class AccountDAO {
    public static Account checkLogin(String username, String password) throws SQLException, NamingException{
        Account validAccount = new Account(username, password);
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Account WHERE username=? and password=?";
        try {
            con = DBUtils.makeConnection();
            if(con != null){
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                
                rs = ps.executeQuery();
                
                if(rs.next()) {
                    return validAccount;
                }
                else {
                    return null;
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
    public static void main(String[] args) throws SQLException, NamingException {
        AccountDAO dao = new AccountDAO();
        System.out.println(dao.checkLogin("abc", "123456"));
    }
}
