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
public class HumanType {
    private String typeid;
    private String typename;
    
    public HumanType(){
        typeid = "";
        typename = "";
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public HumanType(String typeid, String typename) {
        this.typeid = typeid;
        this.typename = typename;
    }
    
}
