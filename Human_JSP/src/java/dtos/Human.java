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
public class Human {
    private String humanid;
    private String humanname;
    private String humangender;
    private String humandob;
    private String humantypeid;
    
    public Human(){
        humanid = "";
        humanname = "";
        humangender = "";
        humandob = "";
        humantypeid = "";
    }

    public Human(String humanid, String humanname, String humangender, String humandob, String humantypeid) {
        this.humanid = humanid;
        this.humanname = humanname;
        this.humangender = humangender;
        this.humandob = humandob;
        this.humantypeid = humantypeid;
    }

    public String getHumanid() {
        return humanid;
    }

    public void setHumanid(String humanid) {
        this.humanid = humanid;
    }

    public String getHumanname() {
        return humanname;
    }

    public void setHumanname(String humanname) {
        this.humanname = humanname;
    }

    public String getHumangender() {
        return humangender;
    }

    public void setHumangender(String humangender) {
        this.humangender = humangender;
    }

    public String getHumandob() {
        return humandob;
    }

    public void setHumandob(String humandob) {
        this.humandob = humandob;
    }

    public String getHumantypeid() {
        return humantypeid;
    }

    public void setHumantypeid(String humantypeid) {
        this.humantypeid = humantypeid;
    }
    
    
}
