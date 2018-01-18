/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.tdi.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author nabila farapasyet
 */
public class AplikasiUtama {
   
    public static void koneksi(){
        BasicDataSource ds =new BasicDataSource(); 
        ds.setUsername("hr");
        ds.setPassword("hr");
        ds.setUrl("jdbc:postgresql://localhost:5432/hr");
        ds.setDriverClassName("org.postgresql.Driver");
        
        try{
            // membuka koneksi ke database
            Connection connection = ds.getConnection();
            System.out.println("berhasil koneksi ke database");
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from departments");
            
            // untuk mengambil data perbaris
            while(resultSet.next()){
                Integer departmentId = resultSet.getInt("department_id");
                String departmentName = resultSet.getString("department_name");
                Integer managerId = resultSet.getInt(3);
                
                System.out.println(String.format("{departemntId : %s, deparetmentName :%s, managerId :%s }",
                        departmentId,departmentName,managerId));
                
            }
            
            // menutup koneksi ke database
            resultSet.close();
            statement.close();
            connection.close();
        }catch (SQLException sqle){
            System.err.println("tidak dapat koneksi ke database");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Halo ini aplikasi maven pertama");
        
        koneksi();
    }
}
