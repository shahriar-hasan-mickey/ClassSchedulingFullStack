/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.classRegistration.dao;
import com.classRegistration.entities.Faculty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author akhlaq-aqidah
 */
public class FacultyDao {
    private Connection connection ;

    public FacultyDao(Connection connection) {
        this.connection = connection;
    }
    
    public Faculty getFacultyByEmailAndPassword(String fEmail, String fPassword){
        Faculty faculty = new Faculty();
        try{
            String query = "SELECT * FROM Faculty WHERE fEmail=? AND fPassword=?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, fEmail);
            preparedStatement.setString(2, fPassword);
    //        preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            
            
            if(resultSet.next()){
                faculty.setfName(resultSet.getString("fName"));
                faculty.setfEmail(resultSet.getString("fEmail"));
                faculty.setfPassword(resultSet.getString("fPassword"));
                faculty.setfId(resultSet.getInt("fid"));
        }
        
        System.out.println("inside dao=>"+faculty);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return faculty;
    }
    
    
}
