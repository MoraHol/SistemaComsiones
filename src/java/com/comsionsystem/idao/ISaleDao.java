/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comsionsystem.idao;

import com.comisionsystem.model.Sale;
import java.util.ArrayList;

/**
 *
 * @author David Viuche
 */
public interface ISaleDao {
    public ArrayList<Sale> findAll();
    public Sale findSaleById(int id);
    public int save(Sale sale);
    public int delete(int id);
    public int update(Sale sale);
}
