/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author chiuy
 */
public class Enroll {
    private Student std;
    private Course course;
    
    public Enroll(){}

    public Enroll(Student std, Course course) {
        this.std = std;
        this.course = course;
    }

    public Student getStd() {
        return std;
    }

    public void setStd(Student std) {
        this.std = std;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
    
}
