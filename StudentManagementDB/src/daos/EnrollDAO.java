/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Course;
import dtos.Enroll;
import dtos.Student;
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
public class EnrollDAO {

    public ArrayList<Enroll> getEnrollByStudent(String sID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Enroll> lst = new ArrayList<>();
        StudentDAO sDAO = new StudentDAO();
        CourseDAO cDAO = new CourseDAO();
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                ps = con.prepareStatement("SELECT * FROM Enroll WHERE std_id = ?");
                ps.setString(1, sID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Student s = sDAO.getStudentById(sID);
                    Course c = cDAO.getCourseById(rs.getString("course_id"));
                    Enroll en = new Enroll(s, c);
                    lst.add(en);
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR FIND ENROLLMENTS: " + e.getMessage());
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
                System.out.println("ERROR:" + e.getMessage());
            }
        }
        return lst;
    }

    public ArrayList<Enroll> getEnrollByCourse(String cID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Enroll> lst = new ArrayList<>();
        StudentDAO sDAO = new StudentDAO();
        CourseDAO cDAO = new CourseDAO();
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                ps = con.prepareStatement("SELECT * FROM Enroll WHERE course_id = ?");
                ps.setString(1, cID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Student s = sDAO.getStudentById(rs.getString("std_id"));
                    Course c = cDAO.getCourseById(cID);
                    Enroll en = new Enroll(s, c);
                    lst.add(en);
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR FIND ENROLLMENTS: " + e.getMessage());
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
                System.out.println("ERROR:" + e.getMessage());
            }
        }
        return lst;
    }

    public boolean addEnroll(String cID, String sID) {
        boolean success = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                ps = con.prepareStatement("INSERT INTO Enroll(std_id, course_id)"
                        + "VALUES(?, ?)");
                ps.setString(1, sID);
                ps.setString(2, cID);
                success = ps.executeUpdate() >= 1;
            }
        } catch (SQLException e) {
            System.out.println("ERROR ADDING ENROLLMENT: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("ERROR:" + e.getMessage());
            }
        }
        return success;
    }

    public boolean deleteEnroll(String cID, String sID) {
        boolean success = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                ps = con.prepareStatement("DELETE FROM Enroll WHERE std_id = ? AND course_id = ?");
                ps.setString(1, sID);
                ps.setString(2, cID);
                success = ps.executeUpdate() >= 1;
            }
        } catch (SQLException e) {
            System.out.println("ERROR DELETING ENROLLMENT: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("ERROR:" + e.getMessage());
            }
        }
        return success;
    }

    public Enroll getEnroll(String cID, String sID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StudentDAO sDAO = new StudentDAO();
        CourseDAO cDAO = new CourseDAO();
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                ps = con.prepareStatement("SELECT *  FROM Enroll WHERE std_id = ? AND course_id = ?");
                ps.setString(1, sID);
                ps.setString(2, cID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    Student s = sDAO.getStudentById(sID);
                    Course c = cDAO.getCourseById(cID);
                    Enroll en = new Enroll(s, c);
                    return en;
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR DELETING ENROLLMENT: " + e.getMessage());
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
                System.out.println("ERROR:" + e.getMessage());
            }
        }
        return null;
    }
}
