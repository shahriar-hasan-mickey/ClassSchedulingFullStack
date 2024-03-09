/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.classRegistration.entities;

/**
 *
 * @author akhlaq-aqidah
 */
public class PromptMessage {

    private String content;
    private String type;
    private String cssClass;

    public PromptMessage(String content, String type, String cssClass) {
        this.content = content;
        this.type = type;
        this.cssClass = cssClass;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    public String getCssClass() {
        return cssClass;
    }
    
    
    
}
