/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.classRegistration.utilities;

import java.sql.*;

public class ConnectionProvider {
    private static Connection connection;
    
    public static Connection getConnection(){
        
        if(connection == null){
            
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                String url = "jdbc:mysql://localhost:3306/cse310_assignment2";
                String username = "user1";
                String password = "us4r1";
                
                connection = DriverManager.getConnection(url, username, password);
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        System.out.println("CONNECTION ESTABLISHED");
        return connection;
    }
}
