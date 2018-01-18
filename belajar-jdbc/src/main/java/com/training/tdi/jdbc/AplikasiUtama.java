/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.tdi.jdbc;

import com.training.tdi.jdbc.dao.DepartmentDao;
import com.training.tdi.jdbc.entity.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author nabila farapasyet
 */
public class AplikasiUtama {

    public static void koneksi() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUsername("hr");
        ds.setPassword("hr");
        ds.setUrl("jdbc:postgresql://localhost:5432/hr");
        ds.setDriverClassName("org.postgresql.Driver");
        Connection connection = null;

        try {
            // membuka koneksi ke database
//            Connection connection = ds.getConnection();
            connection = ds.getConnection();
            connection.setAutoCommit(false);
            System.out.println("berhasil koneksi ke database");
            DepartmentDao dao = new DepartmentDao(connection);
//          dao.save(new Department(2001,"Sistem Analis",1000,null));

//            String sqlInsert ="insert into departments (department_id,department_name,location_id) values (?, ?, ?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
//            preparedStatement.setInt(1, 1006);
//            preparedStatement.setString(2, "Publisher");
//            preparedStatement.setInt(3, 1000);
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from departments");
            // untuk mengambil data perbaris
//            while(resultSet.next()){ // DARI CLASS DEPARTEMENT
//                Department dep = new Department(
//                    resultSet.getInt("department_id"),
//                    resultSet.getString("department_name"),
//                    resultSet.getInt(3),
//                    resultSet.getInt(4)
//                    );
////                Integer departmentId = resultSet.getInt("department_id");
////                String departmentName = resultSet.getString("department_name");
////                Integer managerId = resultSet.getInt(3);
////                
////                System.out.println(String.format("{departemntId : %s, deparetmentName :%s, managerId :%s }",
////                        departmentId,departmentName,managerId));
//                System.out.println(dep.toString());
//            }
            // save nilai department
            Department departmentBaru = dao.save(new Department(null, "Sistem Analis", 1000, null));
            System.out.println(departmentBaru.toString());
            dao.save(new Department(null, "Sistem Analis", 1000, null));
            //error karena duplikate
            //dao.update(new Department(3003, "Sistem Analis",1000,null));
            dao.delete(3003);

            //untuk ambil nilainya
            List<Department> daftarDepartment = dao.findAll();
            for (Department d : daftarDepartment) {
                System.out.println(d.toString());
            }
//            
            // menutup koneksi ke database
//            resultSet.close();
//            statement.close();

            connection.commit();
            connection.close();
        } catch (SQLException sqle) {
            System.err.println("tidak dapat koneksi ke database");
            sqle.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Halo ini aplikasi maven pertama");

        koneksi();
    }
}
