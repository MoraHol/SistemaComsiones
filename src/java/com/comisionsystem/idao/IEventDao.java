/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comisionsystem.idao;

import com.comisionsystem.model.Event;
import java.util.ArrayList;

/**
 *
 * @author BOG-A406-E-005
 */
public interface IEventDao {
    public ArrayList<Event> findAll();
    public int save(Event event);
    public int delete(int id);
    public int update(Event event);
    public Event findById(int id);
    
}
