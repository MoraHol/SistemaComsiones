/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comisionsystem.service;

import com.comisionsystem.dao.EmployeeDaoImpl;
import com.comisionsystem.model.Employee;
import com.comsionsystem.idao.IEmployeeDao;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
public class EmployeeService {
    private IEmployeeDao employeeDao;
    
    public EmployeeService(){
        employeeDao = new EmployeeDaoImpl();
    }
    public ArrayList<Employee> list(){
        try {
            return employeeDao.findAll();
        } catch (Exception e) {
        }
        return null;
    }
    
    public Employee consultByPersonalId(String personalId) throws Exception {
        Employee user = new Employee();
        try {
            //user = employeeDao.(email);
        } catch (Exception e) {
            System.out.println("UsuarioService: Se presento un error al "
                    + "consultar por id en la tabla: " + e.getMessage());
        }
        return user;
    }
    public Employee authenticateEmployee(String personalId, String password) {
        return null;
    }
    
    public String convertSHA256(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

        byte[] hash = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();

        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}