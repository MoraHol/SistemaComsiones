/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comisionsystem.bean;

import com.comisionsystem.model.Employee;
import com.comisionsystem.service.EmployeeService;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
    private String personalId;
    private String password;
    private Employee employee;
    private EmployeeService employeeService;
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        employeeService = new EmployeeService();
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public boolean isAuthenticated() throws Exception {
        employee = employeeService.authenticateEmployee(personalId, password);
        return employee != null;
    }
    
    public void doLogin() throws Exception {
        try {
            if (isAuthenticated()) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
                FacesContext.getCurrentInstance().getExternalContext().redirect("pages/user/dashboard.xhtml");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("messagesApp",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }
}
