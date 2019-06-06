/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comisionsystem.dao;

import com.comisionsystem.model.Payroll;
import com.comsionsystem.idao.IPayDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author David Viuche
 */
public class PayrollDaoImpl extends ConnectionSQL implements IPayDao {

    @Override
    public ArrayList<Payroll> findAll() {
        ArrayList<Payroll> list = new ArrayList<>();
        try {
            this.connect();
            String query = "SELECT * FROM `payrolls`";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(this.findPayrollById(rs.getInt("id_payroll")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public Payroll findPayrollById(int id) {
        Payroll payroll = new Payroll();
        try {
            this.connect();
            String query = "SELECT * FROM `payrolls` WHERE id_payroll = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int t = 1;
                payroll.setId_payroll(rs.getInt(t++));
                payroll.setAmount(rs.getInt(t++));
                payroll.setComision(rs.getInt(t++));
                payroll.setMonth(rs.getString(t++));
                payroll.setYear(rs.getString(t++));
                payroll.setEmployee(new EmployeeDaoImpl().findById(rs.getInt(t++)));
            }
            this.disconnect();
        } catch (Exception e) {
        }
        return payroll;
    }

    @Override
    public int save(Payroll payroll) {
        int result = 0;
        try {
            this.connect();
            String query = "INSERT INTO `payrolls` (`id_payroll`, `amount`, `comision`, `month`, `year`, `employees_id_employee`) VALUES (NULL, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, payroll.getId_payroll());
            pstm.setInt(2, payroll.getAmount());
            pstm.setInt(3, payroll.getComision());
            pstm.setString(4, payroll.getMonth());
            pstm.setString(5, payroll.getYear());
            pstm.setInt(6, payroll.getEmployee().getId());
            result = pstm.executeUpdate();
           
            this.disconnect();
        } catch (Exception e) {

        }
        return result;
    }

    @Override
    public int delete(int id) {
        int result = 0;
        try {
            this.connect();
            String query = "DELETE FROM `payrolls` WHERE `payrolls`.`id_payroll` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            result = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public int update(Payroll payroll) {
        int result = 0;
        try {
            this.connect();
            String query = "UPDATE `payrolls` SET `amount` = ?, `comision` = ?, `month` = ?, `year` = ?, `employees_id_employee` = ? WHERE `payrolls`.`id_payroll` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, payroll.getId_payroll());
            pstm.setInt(2, payroll.getAmount());
            pstm.setInt(3, payroll.getComision());
            pstm.setString(4, payroll.getMonth());
            pstm.setString(5, payroll.getYear());
            pstm.setInt(6, payroll.getEmployee().getId());
            result = pstm.executeUpdate();
            
            this.disconnect();
        }catch(Exception e){
            
        }
        return result;
    }
}
