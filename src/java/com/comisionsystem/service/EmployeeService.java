/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comisionsystem.service;

import com.comisionsystem.dao.EmployeeDaoImpl;
import com.comisionsystem.model.Employee;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.comisionsystem.idao.IEmployeeDao;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
public class EmployeeService {

    private IEmployeeDao employeeDao;
    private Employee employee;

    public EmployeeService() {
        employeeDao = new EmployeeDaoImpl();
    }

    public ArrayList<Employee> list() {
        try {
            return employeeDao.findAll();
        } catch (Exception e) {
        }
        return null;
    }

    public Employee consultByPersonalId(String personalId) throws Exception {
        Employee employee = new Employee();
        try {
            employee = employeeDao.findByPersonalId(personalId);
        } catch (Exception e) {
            System.out.println("UsuarioService: Se presento un error al "
                    + "consultar por id en la tabla: " + e.getMessage());
        }
        return employee;
    }

    public Employee authenticateEmployee(String personalId, String password) {
        try {
            employee = consultByPersonalId(personalId);
            if (employee != null) {
                if (employee.getPassword().equals(convertSHA256(password))) {
                    return employee;
                } else {
                    throw new Exception("Contraseña Incorrecta");
                }
            } else {
                throw new Exception("El Correo no se encuentra registrado");
            }
        } catch (Exception ex) {
            Logger.getLogger(EmployeeService.class.getName()).log(Level.SEVERE, null, ex);
        }
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
