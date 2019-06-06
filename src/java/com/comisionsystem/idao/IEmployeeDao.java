/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comisionsystem.idao;

import com.comisionsystem.model.Employee;
import com.comisionsystem.model.Position;
import java.util.ArrayList;

/**
 *
 * @author Adrian Hoyos
 */
public interface IEmployeeDao {
    public ArrayList<Employee> findAll();
    public int save(Employee employee);
    public int delete(int id);
    public int update(Employee employee);
    public Employee findById(int id);
    public Position findPositionById(int idEmployee);

}
