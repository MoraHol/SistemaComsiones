/*
 * DAO of the class Employees
 */
package com.comisionsystem.dao;

import com.comisionsystem.idao.IEmployeeDao;
import com.comisionsystem.model.Position;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.comisionsystem.model.Employee;
import java.util.ArrayList;

/**
 *
 * @author Adrian Hoyos
 */
public class EmployeeDaoImpl extends ConnectionSQL implements IEmployeeDao {
    
    /**
     * Function to bring all employees
     * @return List of all employees
     */
    public ArrayList<Employee> findAll() {
        ArrayList<Employee> list = new ArrayList<>();
        try {
            this.connect();
            String query = "SELECT * FROM `employees`";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int t = 1;
                Employee employee = new Employee();
                employee.setId(rs.getInt(t++));
                employee.setPersonalId(rs.getInt(t++));
                employee.setFirstName(rs.getString(t++));
                employee.setSecondName(rs.getString(t++));
                employee.setFirstSurName(rs.getString(t++));
                employee.setSecondSurName(rs.getString(t++));
                employee.setPosition(new PositionDaoImpl().findPositionById(t++));
                employee.setExt(rs.getString(t++));
                employee.setDependency(rs.getString(t++));
                employee.setCreatedAt(rs.getDate(t++));
                employee.setUpdatedAt(rs.getDate(t++));
                list.add(employee);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    /**
     * Function to save an user in the database
     * @param employee
     * @return 1 if the employee was saved, otherwise 0
     */
    public int save(Employee employee) {
        int status = 0;
        try {
            this.connect();
            String query = "INSERT INTO `employees` (`personal_id`, `first_name`, `second_name`, `fisrt_surname`,`second_surname`, `positions_id_positions`, `ext`, `dependency`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, employee.getPersonalId());
            pstm.setString(2, employee.getFirstName());
            pstm.setString(3, employee.getSecondName());
            pstm.setString(4, employee.getFirstSurName());
            pstm.setString(5, employee.getSecondSurName());
            pstm.setInt(6, employee.getPosition().getId());
            pstm.setString(7, employee.getExt());
            pstm.setString(8, employee.getDependency());
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.err.println("EmployeeDao Eror al insertar: " + e.getMessage());
        }
        return status;
    }
    
    /**
     * Function to delete an employee
     * @param id The id of the respective employee
     * @return 1 if the employee was deleted, otherwise 0
     */
    @Override
    public int delete(int id) {
        int status = 0;
        try {
            this.connect();
            String query = "DELETE FROM `employe` WHERE `employee`.`id` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.err.println("EmployeeDao:" + e.getMessage());
        }
        return status;
    }
    
    /**
     * Function to update the informaten about an specified employee
     * @param employee Object to update
     * @return 1 if the employee was updated, otherwise 0
     */
    public int update(Employee employee) {
        int status = 0;
        try {
            this.connect();
            String query = "UPDATE `employee` SET `personal_id` = ?, `first_name` = ?, `second_name` = ?, `fisrt_surname` = ?, `second_surname` = ?, `positions_id_positions` =? , `ext` = ?, `dependency` = ?, `password` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, employee.getPersonalId());
            pstm.setString(2, employee.getSecondName());
            pstm.setString(3, employee.getFirstSurName());
            pstm.setString(4, employee.getSecondSurName());
            pstm.setInt(5, employee.getPosition().getId());
            pstm.setString(6, employee.getExt());
            pstm.setString(7, employee.getDependency());
            pstm.setString(8,employee.getPassword());
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.err.println("EmployeeDao:" + e.getMessage());
        }
        return status;
    }
    
    /**
     * Function to find an employee by id
     * @param id
     * @return The espective employee
     */
    public Employee findById(int id){
        Employee employee = new Employee();
        try {
            this.connect();
            String query = "SELECT * FROM `employees` WHERE `id_employee` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int t = 1;
                employee.setId(rs.getInt(t++));
                employee.setPersonalId(rs.getInt(t++));
                employee.setFirstName(rs.getString(t++));
                employee.setSecondName(rs.getString(t++));
                employee.setFirstSurName(rs.getString(t++));
                employee.setSecondSurName(rs.getString(t++));
                employee.setPosition(new PositionDaoImpl().findPositionById(t++));
                employee.setExt(rs.getString(t++));
                employee.setDependency(rs.getString(t++));
                employee.setPassword(rs.getString(t++));
                employee.setCreatedAt(rs.getDate(t++));
                employee.setUpdatedAt(rs.getDate(t++));
            }
            
        } catch (Exception e) {
             System.err.println("EmployeeDao:" + e.getMessage());
        }
        return employee;
    }
    
    /**
     * Function to find the position an specified employee
     * @param idEmployee
     * @return The position of the employee
     */
    public Position findPositionById(int idEmployee) {
        Position positionOfTheEmployee = new Position();
        try {
            this.connect();
            String query = "SELECT P.id_positions, P.name \n" +
                           "FROM employees E\n" +
                           "INNER JOIN positions P\n" +
                           "ON E.positions_id_positions = P.id_positions\n" +
                           "WHERE `positions_id_positions` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, idEmployee);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int t = 1;
                positionOfTheEmployee.setId(rs.getInt(t++));
                positionOfTheEmployee.setName(rs.getString(t++));
            }
        } catch (Exception e) {
        }
        return positionOfTheEmployee;
    }
    
    public Employee findByPersonalId(String personalId){
        Employee employee = new Employee();
        try {
            this.connect();
            String query = "SELECT id_employee FROM `employees` WHERE `personal_id` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setString(1, personalId);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                employee = this.findById(rs.getInt(1));
            }
            this.disconnect();
        } catch (Exception e) {
            return null;
        }
        return employee;
    }
    
}
