/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comsionsystem.idao;

import com.comisionsystem.model.Employee;
import java.util.ArrayList;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
public interface IEmployeeDao {
    public int save(Employee employee);
    public int update(Employee employee);
    public int delete(int id);
    public Employee findEmployee(int id);
    public ArrayList<Employee> findAll();
    public Employee findByPersonalId(String personalId);
}
