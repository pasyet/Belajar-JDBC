/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.tdi.jdbc.entity;

/**
 *
 * @author nabila farapasyet
 */
public class Department {

    public Department() {
    }

    public Department(Integer id, String nama, Integer locationId, Integer managerId) {
        this.id = id;
        this.nama = nama;
        this.locationId = locationId;
        this.managerId = managerId;
    }
    
    
    private Integer id;
    private String nama;
    private Integer locationId;
    private Integer managerId;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return String.format("{departemntId : %s, deparetmentName :%s, managerId :%s, locationId: %s }",
                        id,nama,managerId,locationId
        );
    }  
}
