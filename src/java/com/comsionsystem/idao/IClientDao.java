/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comsionsystem.idao;

import com.comisionsystem.model.Client;
import java.util.ArrayList;

/**
 *
 * @author AlexisHolguin github:MoraHol
 */
public interface IClientDao {
    public ArrayList<Client> findAll();
    public Client findClientById(int id);
    public int save(Client client);
    public int delete(int id);
    public int update(Client client);
}
