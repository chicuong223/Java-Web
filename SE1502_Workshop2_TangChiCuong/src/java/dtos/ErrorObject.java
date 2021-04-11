/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author chiuy
 */
public class ErrorObject implements Serializable{
    private String IDError;
    private String nameError;
    private String producerError;
    private String techInfoError;
    
    public ErrorObject(){}

    public ErrorObject(String IDError, String nameError, String producerError, String techInfoError) {
        this.IDError = IDError;
        this.nameError = nameError;
        this.producerError = producerError;
        this.techInfoError = techInfoError;
    }

    public String getIDError() {
        return IDError;
    }

    public void setIDError(String IDError) {
        this.IDError = IDError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getProducerError() {
        return producerError;
    }

    public String getTechInfoError() {
        return techInfoError;
    }

    public void setTechInfoError(String techInfoError) {
        this.techInfoError = techInfoError;
    }

    public void setProducerError(String producerError) {
        this.producerError = producerError;
    }
    
    
}
