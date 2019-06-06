/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comisionsystem.bean;

import com.comisionsystem.dao.ClientDaoImpl;
import com.comisionsystem.model.Client;
import com.comsionsystem.idao.IClientDao;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alexis Holguin
 */
@ManagedBean
@ViewScoped
public class ClientBean {
    private Client client;
    IClientDao clientDao;
    private String phoneNumber1;
    private String phoneNumber2;
    private String phoneNumber3;
    private String cellPhoneNumber1;
    private String cellPhoneNumber2;
    private String cellPhoneNumber3;
    private String email1;
    private String email2;
    private String email3;
    /**
     * Creates a new instance of RegisterClientBean
     */
    public ClientBean() {
        client = new Client();
        clientDao = new ClientDaoImpl();
        phoneNumber1 = new String();
        phoneNumber2 = new String();
        phoneNumber3 = new String();
        cellPhoneNumber1 = new String();
        cellPhoneNumber2 = new String();
        cellPhoneNumber3 = new String();
        email1 = new String();
        email2 = new String();
        email3 = new String();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public void register(){
        client.getPhoneNumbers().add(phoneNumber1);
        client.getPhoneNumbers().add(phoneNumber2);
        client.getPhoneNumbers().add(phoneNumber3);
        client.getCellPhoneNumbers().add(cellPhoneNumber1);
        client.getCellPhoneNumbers().add(cellPhoneNumber2);
        client.getCellPhoneNumbers().add(cellPhoneNumber3);
        client.getEmails().add(email1);
        client.getEmails().add(email2);
        client.getEmails().add(email3);
        if(clientDao.save(client) > 0){
            FacesContext.getCurrentInstance().addMessage("messagesApp", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Se ha registrado el cliente"));
        }else{
            FacesContext.getCurrentInstance().addMessage("messagesApp", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "No se ha registrado el cliente"));
        }
    }
    public ArrayList<Client> getClients(){
        return clientDao.findAll();
    }

    public IClientDao getClientDao() {
        return clientDao;
    }

    public void setClientDao(IClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getPhoneNumber3() {
        return phoneNumber3;
    }

    public void setPhoneNumber3(String phoneNumber3) {
        this.phoneNumber3 = phoneNumber3;
    }

    public String getCellPhoneNumber1() {
        return cellPhoneNumber1;
    }

    public void setCellPhoneNumber1(String cellPhoneNumber1) {
        this.cellPhoneNumber1 = cellPhoneNumber1;
    }

    public String getCellPhoneNumber2() {
        return cellPhoneNumber2;
    }

    public void setCellPhoneNumber2(String cellPhoneNumber2) {
        this.cellPhoneNumber2 = cellPhoneNumber2;
    }

    public String getCellPhoneNumber3() {
        return cellPhoneNumber3;
    }

    public void setCellPhoneNumber3(String cellPhoneNumber3) {
        this.cellPhoneNumber3 = cellPhoneNumber3;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }


    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getEmail3() {
        return email3;
    }

    public void setEmail3(String email3) {
        this.email3 = email3;
    }
    
}
