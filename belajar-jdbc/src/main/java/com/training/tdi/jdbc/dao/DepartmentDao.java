/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.tdi.jdbc.dao;

import com.training.tdi.jdbc.entity.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nabila farapasyet
 */
public class DepartmentDao {

    private Connection connection;

    public DepartmentDao() {
    }

    public DepartmentDao(Connection connection) {
        this.connection = connection;
    }

    public Department save(Department dep) throws SQLException {
        String sqlInsert = "insert into departments (department_id,department_name,location_id) values (nextval('departments_department_id_seq'), ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
//      preparedStatement.setInt(1, dep.getId());
        preparedStatement.setString(1, dep.getNama());
        preparedStatement.setInt(2, dep.getLocationId());
        preparedStatement.executeUpdate();
        
        ResultSet primarKey = preparedStatement.getGeneratedKeys();
        if (primarKey.next()){
            dep.setId(primarKey.getInt(1));
        }
        
        preparedStatement.close();
        primarKey.close();
        return dep;
    }

    public void update(Department dep) throws SQLException {
        String sqlInsert = "update departments set department_name, location_id=?, manager_id = ? where department_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
        preparedStatement.setInt(4, dep.getId());
        preparedStatement.setNull(3, Types.INTEGER);
        preparedStatement.setString(1, dep.getNama());
        preparedStatement.setInt(2, dep.getLocationId());
        preparedStatement.executeUpdate();
        preparedStatement.close();

    }

    public void delete(Integer departmentId) throws SQLException {
        String sqlInsert = "delete from departments where department_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
        preparedStatement.setInt(1, departmentId);
        preparedStatement.executeUpdate();
        preparedStatement.close();

    }

    public List<Department> findAll() throws SQLException {
        List<Department> values = new ArrayList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from departments");

        while (resultSet.next()) {
            Department dep = new Department(
                    resultSet.getInt("department_id"),
                    resultSet.getString("department_name"),
                    resultSet.getInt(3),
                    resultSet.getInt(4)
            );
            values.add(dep);
        }
        resultSet.close();
        statement.close();

        return values;

    }

}
