/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comisionsystem.model;

import java.util.ArrayList;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
public class Client {
    private int id;
    private String nit;
    private String nameCompany;
    private String firstName;
    private String secondName;
    private String firstSurName;
    private String secondSurName;
    private String adressPersonal;
    private String AddressCompany;
    private ArrayList<String> emails;
    private ArrayList<String> phoneNumbers;
    private ArrayList<String> cellPhoneNumbers;
    private ArrayList<Event> events;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstSurName() {
        return firstSurName;
    }

    public void setFirstSurName(String firstSurName) {
        this.firstSurName = firstSurName;
    }

    public String getSecondSurName() {
        return secondSurName;
    }

    public void setSecondSurName(String secondSurName) {
        this.secondSurName = secondSurName;
    }

    

    public String getAdressPersonal() {
        return adressPersonal;
    }

    public void setAdressPersonal(String adressPersonal) {
        this.adressPersonal = adressPersonal;
    }

    public String getAddressCompany() {
        return AddressCompany;
    }

    public void setAddressCompany(String AddressCompany) {
        this.AddressCompany = AddressCompany;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public ArrayList<String> getCellPhoneNumbers() {
        return cellPhoneNumbers;
    }

    public void setCellPhoneNumbers(ArrayList<String> cellPhoneNumbers) {
        this.cellPhoneNumbers = cellPhoneNumbers;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
    
    
}
