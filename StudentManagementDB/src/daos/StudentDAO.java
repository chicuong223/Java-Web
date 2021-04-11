/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBConnect;

/**
 *
 * @author chiuy
 */
public class StudentDAO {

    public void printAllStudents() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                ps = con.prepareStatement("SELECT * FROM Student");
                rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString("id") + " - " + rs.getString("name"));
                }
                if(!rs.next()) System.out.println("End of List");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("ERROR GET ALL STUDENTS: " + e.getMessage());
            }
        }
    }

    public boolean addStudent(String id, String name) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO Student(id, name)"
                + "VALUES(?, ?)";
        boolean valid = false;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                ps.setString(2, name);
                valid = ps.executeUpdate() >= 1;
            }
        } catch (SQLException e) {
            System.out.println("ERROR ADDING STUDENT: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("ERROR ADDING STUDENT: " + e.getMessage());
            }
        }
        return valid;
    }

    public Student getStudentById(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student s = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                ps = con.prepareStatement("SELECT * FROM Student WHERE id = ?");
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    s = new Student(id, name);
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR FINDING STUDENT: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("ERROR GETTING STUDENT: " + e.getMessage());
            }
        }
        return s;
    }

    public boolean deleteStudent(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM Student WHERE id=?";
        boolean valid = false;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                valid = ps.executeUpdate() >= 1;
            }
        } catch (SQLException e) {
            System.out.println("ERROR DELETING STUDENT: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("ERROR GET ALL STUDENTS: " + e.getMessage());
            }
        }
        return valid;
    }
}
