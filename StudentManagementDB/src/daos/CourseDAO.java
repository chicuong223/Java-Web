/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBConnect;

/**
 *
 * @author chiuy
 */
public class CourseDAO {

    public Course getCourseById(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Course c = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                ps = con.prepareStatement("SELECT * FROM Course WHERE id = ?");
                ps.setString(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    c = new Course(id, name);
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR FINDING COURSE: " + e.getMessage());
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
                System.out.println("ERROR GETTING COURSE: " + e.getMessage());
            }
        }
        return c;
    }

    public void printAllCourses() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                ps = con.prepareStatement("SELECT * FROM Course");
                rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString("id") + " - " + rs.getString("name"));
                }
                if (!rs.next()) {
                    System.out.println("End of List");
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR FINDING COURSES: " + e.getMessage());
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
                System.out.println("ERROR GETTING COURSE: " + e.getMessage());
            }
        }
    }
    
    public boolean addCourse(String id, String name) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO Course(id, name)"
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
            System.out.println("ERROR ADDING COURSE: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("ERROR ADDING COURSE: " + e.getMessage());
            }
        }
        return valid;
    }
    
     public boolean deleteCourse(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM Course WHERE id = ?";
        boolean valid = false;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, id);
                valid = ps.executeUpdate() >= 1;
            }
        } catch (SQLException e) {
            System.out.println("ERROR DELETING COURSE: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("ERROR DELETING COURSE: " + e.getMessage());
            }
        }
        return valid;
    }
}
