/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.classRegistration.dao;

import com.classRegistration.entities.Students;
//import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author akhlaq-aqidah
 */
public class StudentDao {
    
    private Connection connection;
    
    public StudentDao(Connection connection){
        this.connection = connection;
    }
    
    public Students getStudentByEmailAndPassword(String email, String password) throws Exception{
        String query = "SELECT * FROM Students WHERE sEmail=? AND sPassword=?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
//        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.executeQuery();
        Students students = null;
        if(resultSet.next()){
            students = new Students();
            students.setsName(resultSet.getString("sName"));
            students.setEmail(resultSet.getString("sEmail"));
            students.setPassword(resultSet.getString("sPassword"));
            students.setsId(resultSet.getInt("sid"));
            students.setClass_section(resultSet.getInt("class_section"));
        }
        
        System.out.println("inside dao=>"+students);
        
        return students;
    }
    
    public Boolean saveStudent(Students students){
        Boolean hasqueryExecutedSuccessfully = false;
        try{
            String query = "INSERT INTO Students(sName, sEmail, sPassword) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, students.getsName());
            preparedStatement.setString(2, students.getEmail());
            preparedStatement.setString(3, students.getPassword());
            
            preparedStatement.executeUpdate();
            hasqueryExecutedSuccessfully = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return hasqueryExecutedSuccessfully;
    }
    
    
    public List<Students> getAllRegisteredStudentsList(){
        List<Students> registeredStudentsList = new ArrayList();
        try{
            String query = "SELECT * FROM Students WHERE NOT class_section=0 OR NOT class_section=null";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                Students students = new Students(resultSet.getInt("sId"), resultSet.getString("sName"), resultSet.getInt("class_section"));
                registeredStudentsList.add(students);
                System.out.println("studentDao"+students);
            }
            
            System.out.println("after while"+registeredStudentsList);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return registeredStudentsList;
    }
    
    
    public List<Students> getAllStudentsBySection(int section){
        List<Students> sectionStudents = new ArrayList();
        try{
            String query = "SELECT * FROM Students WHERE class_section=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, section);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                Students students = new Students(resultSet.getInt("sId"), resultSet.getString("sName"), resultSet.getInt("class_section"));
                sectionStudents.add(students);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return sectionStudents;
    }
    

    public Boolean updateStudentSection(Students students) {
        
        Boolean queryExecutionSuccessStatus = false;
        try{
            String query = "UPDATE Students SET class_section=? WHERE sid=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, students.getClass_section());
            preparedStatement.setInt(2, students.getsId());
            
            preparedStatement.executeUpdate();
            queryExecutionSuccessStatus = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return queryExecutionSuccessStatus;
    }
}
