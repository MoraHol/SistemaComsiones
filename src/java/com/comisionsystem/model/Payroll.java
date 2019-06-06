package com.comisionsystem.model;

/**
 *
 * @author David
 */
public class Payroll {
    private int id_payroll;
    private int amount;
    private int comision;
    private String month;
    private String year;
    private Employee employee;

    public int getId_payroll() {
        return id_payroll;
    }

    public void setId_payroll(int id_payroll) {
        this.id_payroll = id_payroll;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getComision() {
        return comision;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    
    
}
