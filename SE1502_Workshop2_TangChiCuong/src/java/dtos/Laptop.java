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
public class Laptop {
    private String id;
    private String laptopName;
    private String technicalInfo;
    private int yearOfManufacture;
    private String producer;
    private String status;
    private Supplier supplier;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLaptopName() {
        return laptopName;
    }

    public void setLaptopName(String laptopName) {
        this.laptopName = laptopName;
    }

    public String getTechnicalInfo() {
        return technicalInfo;
    }

    public void setTechnicalInfo(String technicalInfo) {
        this.technicalInfo = technicalInfo;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Laptop(String id, String laptopName, String technicalInfo, int yearOfManufacture, String producer, String status, Supplier supplier) {
        this.id = id;
        this.laptopName = laptopName;
        this.technicalInfo = technicalInfo;
        this.yearOfManufacture = yearOfManufacture;
        this.producer = producer;
        this.status = status;
        this.supplier = supplier;
    }
    
    public Laptop(){}

   

    
}
