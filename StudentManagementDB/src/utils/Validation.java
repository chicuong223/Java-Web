/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author chiuy
 */
public class Validation {
    public static boolean validID(String sID, String regex){
        return sID.matches(regex);
    }
}
