/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.classRegistration.entities;

/**
 *
 * @author akhlaq-aqidah
 */
public class Faculty {
    
    private int fId;
    private String fName;
    private String fEmail;
    private String fPassword;

    public Faculty(int fId, String fName, String fEmail, String fPassword) {
        this.fId = fId;
        this.fName = fName;
        this.fEmail = fEmail;
        this.fPassword = fPassword;
    }

    public Faculty(String fName, String fEmail, String fPassword) {
        this.fName = fName;
        this.fEmail = fEmail;
        this.fPassword = fPassword;
    }

    public Faculty(String fEmail, String fPassword) {
        this.fEmail = fEmail;
        this.fPassword = fPassword;
    }

    
    
    public Faculty() {
    }

    
        
    
    
    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfEmail() {
        return fEmail;
    }

    public void setfEmail(String fEmail) {
        this.fEmail = fEmail;
    }

    public String getfPassword() {
        return fPassword;
    }

    public void setfPassword(String fPassword) {
        this.fPassword = fPassword;
    }

    @Override
    public String toString() {
        return "Faculty{" + "fId=" + fId + ", fName=" + fName + ", fEmail=" + fEmail + ", fPassword=" + fPassword + '}';
    }
    
    
    
    
    
}
