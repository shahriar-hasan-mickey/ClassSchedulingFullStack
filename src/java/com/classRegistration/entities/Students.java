/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.classRegistration.entities;

/**
 *
 * @author akhlaq-aqidah
 */
public class Students {
    private int sId;
    private String sName;
    private String email;
    private String password;
    private int class_section;

    public Students(int sId, String sName, String email, String password) {
        this.sId = sId;
        this.sName = sName;
        this.email = email;
        this.password = password;
        this.class_section = 0;
    }

    public Students(String sName, String email, String password) {
        this.sName = sName;
        this.email = email;
        this.password = password;
        this.class_section = 0;
    }

    public Students(int sId, String sName, int class_section) {
        this.sId = sId;
        this.sName = sName;
        this.class_section = class_section;
    }

    
    
    
    public Students() {
    }

    
    
    
    
    
    
    
    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getClass_section() {
        return class_section;
    }

    public void setClass_section(int class_section) {
        this.class_section = class_section;
    }

    @Override
    public String toString() {
        return "Students{" + "sId=" + sId + ", sName=" + sName + ", email=" + email + ", password=" + password + ", class_section=" + class_section + '}';
    }
    
    
    
    
    
}
