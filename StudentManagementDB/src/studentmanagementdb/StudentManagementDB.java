/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmanagementdb;

import daos.CourseDAO;
import daos.EnrollDAO;
import daos.StudentDAO;
import dtos.Enroll;
import java.util.Scanner;
import utils.Validation;

/**
 *
 * @author chiuy
 */
public class StudentManagementDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        StudentDAO sDAO = new StudentDAO();
        EnrollDAO eDAO = new EnrollDAO();
        CourseDAO cDAO = new CourseDAO();
        int choice = 0;
        boolean input = true;
        String sIDRegex = "^([A-Z]{2})([0-9]{6})$";
        String cIDRegex = "^([A-Z]{3})([0-9]{3})$";
        Scanner sc = new Scanner(System.in);
        do {
            String sID = new String();
            String sName = new String();
            String cID = new String();
            String cName = new String();
            String confirm = new String();
            boolean valid;
            try {
                System.out.println("1. View all Students");
                System.out.println("2. Add a Student");
                System.out.println("3. Delete a Student");
                System.out.println("4. View all Courses");
                System.out.println("5. Add a Course");
                System.out.println("6. Delete a Course");
                System.out.println("7. View enrollments by Student");
                System.out.println("8. View enrollments by Course");
                System.out.println("9. Add an enrollment");
                System.out.println("10. Delete an enrollment");
                System.out.print("Choice: ");
                choice = sc.nextInt();
                input = false;
                if (choice >= 1 && choice <= 10) {
                    input = true;
                }
                switch (choice) {
                    case 1:
                        sDAO.printAllStudents();
                        break;
                    case 2:
                        System.out.print("Student ID: ");
                        sc = new Scanner(System.in);
                        sID = sc.nextLine();
                        valid = Validation.validID(sID, sIDRegex);
                        if(valid == false){
                            System.out.println("INVALID STUDENT ID!");
                            break;
                        }
                        if (sDAO.getStudentById(sID) != null) {
                            System.out.println("This Student ID already exists!");
                            break;
                        }
                        System.out.print("Student Name: ");
                        sc = new Scanner(System.in);
                        sName = sc.nextLine();
                        sDAO.addStudent(sID, sName);
                        System.out.println("Successfully added!");
                        break;
                    case 3:
                        System.out.print("Student ID: ");
                        sc = new Scanner(System.in);
                        sID = sc.nextLine();
                        valid = Validation.validID(sID, sIDRegex);
                        if(valid == false){
                            System.out.println("INVALID STUDENT ID!");
                            break;
                        }
                        if (!eDAO.getEnrollByStudent(sID).isEmpty()) {
                            System.out.println("This Student has enrolled in 1 or more courses. Therefore can not be deleted");
                            break;
                        }
                        if (sDAO.getStudentById(sID) == null) {
                            System.out.println("This Student ID does not exist");
                            break;
                        }
                        System.out.print("Are you sure? (Y/N) ");
                        sc = new Scanner(System.in);
                        confirm = sc.nextLine().toLowerCase();
                        switch (confirm) {
                            case "y":
                                sDAO.deleteStudent(sID);
                                System.out.println("Deleted successfully");
                                break;
                            case "n":
                                System.out.println("Did not delete this Student");
                                break;
                            default:
                                System.out.println("INVALID CHOICE");
                                break;
                        }
                        break;
                    case 4:
                        cDAO.printAllCourses();
                        break;
                    case 5:
                        System.out.print("Course ID: ");
                        sc = new Scanner(System.in);
                        cID = sc.nextLine();
                        valid = Validation.validID(cID, cIDRegex);
                        if(valid == false){
                            System.out.println("INVALID COURSE ID!");
                            break;
                        }
                        if (cDAO.getCourseById(cID) != null) {
                            System.out.println("This Course ID already exists!");
                            break;
                        }
                        System.out.print("Course Name: ");
                        cName = sc.nextLine();
                        cDAO.addCourse(cID, cName);
                        System.out.println("Added successfully !");
                        break;
                    case 6:
                        System.out.print("Course ID: ");
                        sc = new Scanner(System.in);
                        cID = sc.nextLine();
                        valid = Validation.validID(cID, cIDRegex);
                        if(valid == false){
                            System.out.println("INVALID COURSE ID!");
                            break;
                        }
                        if(cDAO.getCourseById(cID) == null){
                            System.out.println("This Course ID does not exist !");
                            break;
                        }
                        if (!eDAO.getEnrollByCourse(cID).isEmpty()) {
                            System.out.println("This course is enrolled by 1 or more students. Therefore it can not be deleted");
                            break;
                        }
                        System.out.print("Are you sure? (Y/N) ");
                        sc = new Scanner(System.in);
                        confirm = sc.nextLine().toLowerCase();
                        switch (confirm) {
                            case "y":
                                cDAO.deleteCourse(cID);
                                System.out.println("Deleted successfully");
                                break;
                            case "n":
                                System.out.println("Did not delete this Course");
                                break;
                            default:
                                System.out.println("INVALID CHOICE");
                                break;
                        }
                        break;
                    case 7:
                        System.out.print("Student ID: ");
                        sc = new Scanner(System.in);
                        sID = sc.nextLine();
                        valid = Validation.validID(sID, sIDRegex);
                        if(valid == false){
                            System.out.println("INVALID STUDENT ID!");
                            break;
                        }
                        if (sDAO.getStudentById(sID) == null) {
                            System.out.println("This Student ID does not exist!");
                            break;
                        }
                        if (eDAO.getEnrollByStudent(sID).isEmpty()) {
                            System.out.println("This Student has not enrolled in any course");
                            break;
                        }
                        for (Enroll en : eDAO.getEnrollByStudent(sID)) {
                            System.out.println(en.getCourse().getId() + " - " + en.getCourse().getName());
                        }
                        break;
                    case 8:
                        System.out.print("Course ID: ");
                        sc = new Scanner(System.in);
                        cID = sc.nextLine();
                        valid = Validation.validID(cID, cIDRegex);
                        if(valid == false){
                            System.out.println("INVALID COURSE ID!");
                            break;
                        }
                        if (cDAO.getCourseById(cID) == null) {
                            System.out.println("This Course ID does not exist!");
                            break;
                        }
                        if (eDAO.getEnrollByCourse(cID).isEmpty()) {
                            System.out.println("This Course is not enrolled by any student");
                            break;
                        }
                        for (Enroll en : eDAO.getEnrollByCourse(cID)) {
                            System.out.println(en.getStd().getId() + " - " + en.getStd().getName());
                        }
                        break;
                    case 9:
                        System.out.print("Student ID: ");
                        sc = new Scanner(System.in);
                        sID = sc.nextLine();
                        valid = Validation.validID(sID, sIDRegex);
                        if(valid == false){
                            System.out.println("INVALID STUDENT ID!");
                            break;
                        }
                        if(sDAO.getStudentById(sID) == null){
                            System.out.println("This Student ID does not exist!");
                            break;
                        }
                        System.out.print("Course ID: ");
                        sc = new Scanner(System.in);
                        cID = sc.nextLine();
                        valid = Validation.validID(cID, cIDRegex);
                        if(valid == false){
                            System.out.println("INVALID COURSE ID!");
                            break;
                        }
                        if(cDAO.getCourseById(cID) == null){
                            System.out.println("This Course ID does not exist!");
                            break;
                        }
                        if(eDAO.getEnroll(cID, sID) != null){
                            System.out.println("This enrollment already exists!");
                            break;
                        }
                        eDAO.addEnroll(cID, sID);
                        System.out.println("Added successfully!");
                        break;
                    case 10:
                        System.out.print("Student ID: ");
                        sc = new Scanner(System.in);
                        sID = sc.nextLine();
                        valid = Validation.validID(sID, sIDRegex);
                        if(valid == false){
                            System.out.println("INVALID STUDENT ID!");
                            break;
                        }
                        if(sDAO.getStudentById(sID) == null){
                            System.out.println("This Student ID does not exist!");
                            break;
                        }
                        System.out.print("Course ID: ");
                        sc = new Scanner(System.in);
                        cID = sc.nextLine();
                        valid = Validation.validID(cID, cIDRegex);
                        if(valid == false){
                            System.out.println("INVALID COURSE ID!");
                            break;
                        }
                        if(cDAO.getCourseById(cID) == null){
                            System.out.println("This Course ID does not exist!");
                            break;
                        }
                        if(eDAO.getEnroll(cID, sID) == null){
                            System.out.println("This enrollment does not exist!");
                            break;
                        }
                        System.out.print("Are you sure? (Y/N) ");
                        sc = new Scanner(System.in);
                        confirm = sc.nextLine().toLowerCase();
                        switch (confirm) {
                            case "y":
                                eDAO.deleteEnroll(cID, sID);
                                System.out.println("Deleted successfully");
                                break;
                            case "n":
                                System.out.println("Did not delete this Enrollment");
                                break;
                            default:
                                System.out.println("INVALID CHOICE");
                                break;
                        }
                        break;
                    default:
                        System.out.println("Goodbye !");
                        break;
                }
            } catch (Exception e) {
                System.out.println("INVALID CHOICE");
                input = true;
            } finally {
                System.out.println("Press ENTER to continue...");
                sc = new Scanner(System.in);
                sc.nextLine();
            }
        } while (input);
    }

}
