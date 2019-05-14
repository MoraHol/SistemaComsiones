/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comisionsystem.service;


import com.comisionsystem.dao.ClientDaoImpl;
import com.comsionsystem.idao.IClientDao;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
public class ClientService {
    private static IClientDao clientDAO;
    
    public ClientService(){
        clientDAO = new ClientDaoImpl();
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
