package com.comsionsystem.idao;

import com.comisionsystem.model.Payroll;
import java.util.ArrayList;

/**
 *
 * @author David Viuche
 */
public interface IPayDao {
    public ArrayList<Payroll> findAll();
    public Payroll findPayrollById(int id);
    public int save(Payroll payroll);
    public int delete(int id);
    public int update(Payroll payroll);
}
