package com.lftechnology.java.jdbc.smallapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.lftechnology.java.jdbc.smallapp.model.Employee;
import com.lftechnology.java.jdbc.smallapp.util.Role;


public interface EmployeeDao {
    public Employee login(String username, String password) throws SQLException;
    
    public boolean addUser(Employee emp) throws SQLException;
    
    public boolean checkUserWithRole(Role role) throws SQLException;
    
    public List<Employee> getAllEmployees() throws SQLException;
}
