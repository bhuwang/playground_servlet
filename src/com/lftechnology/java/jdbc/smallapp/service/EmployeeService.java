/**
 * 
 */
package com.lftechnology.java.jdbc.smallapp.service;

import java.sql.SQLException;
import java.util.List;

import com.lftechnology.java.jdbc.smallapp.model.Employee;
import com.lftechnology.java.jdbc.smallapp.util.Role;

/**
 * @author bhuwan
 */
public interface EmployeeService {

    public Employee login(String username, String password) throws SQLException;

    public boolean addUser(Employee emp) throws SQLException;
    
    public boolean checkUserWithRole(Role role) throws SQLException;
    
    public void insertUserIfNotExist(Role role) throws SQLException;
    
    public List<Employee> getAllEmployees() throws SQLException;

    Employee constructEmployee(String username, String password, String fullname, String address, String department,
            Role role);

}
