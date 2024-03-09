/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.classRegistration.entities;

/**
 *
 * @author akhlaq-aqidah
 */
public class ClassTimings {
    private int Section;
    private String Day;
    private String Slot;
    private int NoOfSeats;

    public ClassTimings(int Section, String Day, String Slot, int NoOfSeats) {
        this.Section = Section;
        this.Day = Day;
        this.Slot = Slot;
        this.NoOfSeats = NoOfSeats;
    }

    public ClassTimings() {
    }
    
    

    public int getSection() {
        return Section;
    }

    public void setSection(int Section) {
        this.Section = Section;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String Day) {
        this.Day= Day;
    }

    public String getSlot() {
        return Slot;
    }

    public void setSlot(String Slot) {
        this.Slot = Slot;
    }

    public int getNoOfSeats() {
        return NoOfSeats;
    }

    public void setNoOfSeats(int NoOfSeats) {
        this.NoOfSeats = NoOfSeats;
    }

    @Override
    public String toString() {
        return "ClassTimings{" + "Section=" + Section + ", Day=" + Day + ", Slot=" + Slot + ", NoOfSeats=" + NoOfSeats + '}';
    }
    
    
    
    
}
