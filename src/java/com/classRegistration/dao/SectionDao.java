/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.classRegistration.dao;

import com.classRegistration.entities.ClassTimings;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author akhlaq-aqidah
 */
public class SectionDao {
    private Connection connection;
    
    public SectionDao(Connection connection){
        this.connection = connection;
    }
    
    public List<ClassTimings> getClassTimings(){
        List<ClassTimings> classTimingList = new ArrayList();
        
        try{
            String query = "SELECT * FROM ClassTimings";
            
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                ClassTimings classTimings = new ClassTimings();
                classTimings.setSection(resultSet.getInt("Section"));
                classTimings.setDay(resultSet.getString("Day"));
                classTimings.setSlot(resultSet.getString("Slot"));
                classTimings.setNoOfSeats(resultSet.getInt("NoOfSeats"));
                classTimingList.add(classTimings);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return classTimingList;
    }
    
    public Boolean setSection(ClassTimings classTimings){
        Boolean state = false;
        try{
            String query = "UPDATE ClassTimings SET NoOfSeats=? WHERE Section=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, classTimings.getNoOfSeats());
            preparedStatement.setInt(2, classTimings.getSection());
            preparedStatement.executeUpdate();
            state = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return state;
    }
    
    public ClassTimings getSectionById(int section){
        
        ClassTimings classTimings = new ClassTimings();
        try{
            String query = "SELECT * FROM ClassTimings WHERE Section=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, section);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                classTimings.setSection(resultSet.getInt("Section"));
                classTimings.setDay(resultSet.getString("Day"));
                classTimings.setSlot(resultSet.getString("Slot"));
                classTimings.setNoOfSeats(resultSet.getInt("NoOfSeats"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return classTimings;
    }
    
    public Boolean changeSection(){
    
        Boolean responseValue = false;
        
        return responseValue;
    }
}
