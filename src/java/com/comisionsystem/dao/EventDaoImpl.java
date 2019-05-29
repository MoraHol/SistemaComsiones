package com.comisionsystem.dao;


import com.comisionsystem.dao.ConnectionSQL;
import com.comisionsystem.idao.IEventDao;
import com.comisionsystem.model.Client;
import com.comisionsystem.idao.IClientDao;
import com.comisionsystem.dao.ClientDaoImpl;
import com.comisionsystem.model.Event;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/*
 * DAO of the class Event
 */

/**
 *
 * @author Adrian Hoyos
 */
public class EventDaoImpl extends ConnectionSQL implements IEventDao{
    private IClientDao clientDao  = new ClientDaoImpl();
    
    /**
     * Function to bring all employees
     * @return List of all events
     */
    public ArrayList<Event> findAll() {
        ArrayList<Event> list = new ArrayList<>();
        try {
            this.connect();
            String query = "SELECT * FROM `events`";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int t = 1;
                Event event = new Event();
                event.setId(rs.getInt(t++));
                event.setDate(rs.getDate(t++));
                event.setDescription(rs.getString(t++));
                event.setClient(clientDao.findClientById(rs.getInt(t++)));
                list.add(event);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    /**
     * Function to save a event
     * @param employee
     * @return 1 if the event was saved, otherwise 0
     */
    public int save(Event event) {
        int status = 0;
        try {
            this.connect();
            String query = "INSERT INTO `events` (`id_event`, `date`, `description`, `clients_id_clients`) "
                        + " VALUES (NULL, ?, ?, ?);";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setDate(1, (java.sql.Date) new Date());
            pstm.setString(1, event.getDescription());
            pstm.setInt(2, event.getClient().getId());
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.err.println("EventDao: " + e.getMessage());
        }
        return status;
    }
    
     /**
     * Method to delete a function
     * @param id 
     * @return 1 if the function was deleted, otherwise 0
     */
    public int delete(int id) {
        int status = 0;
        try {
            this.connect();
            String query = "DELETE FROM `events` WHERE `id_event = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.err.println("EventDao:" + e.getMessage());
        }
        return status;
    }
    
    /**
     * Function to update the atributes of a respective event
     * @param event Object to update
     * @return 1 if the event was updated, otherwise 0
     */
    public int update(Event event) {
        int status = 0;
        try {
            this.connect();
            String query = "UPDATE `events` SET `date` = ?, `description` = ?, `clients_id_clients` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, event.getId());
            pstm.setString(2, event.getDescription());
            pstm.setInt(3, event.getClient().getId());
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.err.println("EventDao:" + e.getMessage());
        }
        return status;
        
    }
    
    /**
     * Function to find an event by id
     * @param id
     * @return The espective event
     */
    public Event findById(int id){
        Event event = new Event();
        try {
            this.connect();
            String query = "SELECT * FROM `events` WHERE `id_event` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int t = 1;
                event.setId(rs.getInt(t++));
                event.setDate(rs.getDate(t++));
                event.setDescription(rs.getString(t++));
                event.setClient(clientDao.findClientById(rs.getInt(t++)));
            }
            
        } catch (Exception e) {
             System.err.println("EventDao:" + e.getMessage());
        }
        return event;
    }
    
    
   
}
