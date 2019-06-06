/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comisionsystem.dao;

import com.comisionsystem.model.Position;
import java.util.ArrayList;
import com.comisionsystem.idao.IPositionDao;
import com.comisionsystem.model.Function;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Adrian Hoyos
 */
public class PositionDaoImpl extends ConnectionSQL implements IPositionDao{
    
    /**
     * Function to bring all positions
     * @return List of all positions
     */
    public ArrayList<Position> findAll() {
        ArrayList<Position> list = new ArrayList<>();
        try {
            this.connect();
            String query = "SELECT * FROM `positions`";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int t = 1;
                Position position = new Position();
                position.setId(rs.getInt(t++));
                position.setName(rs.getString(t++));
                position.setFunctions(findFunctionsByIdPosition(rs.getInt(t++)));
                list.add(position);
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    /**
     * Function to find position of the employee
     * @param id Param to identify an employee
     * @return the position of the employee
     */
    public Position findPositionById(int id) {
        Position position = new Position();
        try {
            this.connect();
            String query = "SELECT * FROM `positions` WHERE `id_positions` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int t = 1;
                position.setId(rs.getInt(t++));
                position.setName(rs.getString(t++));
            }
        } catch (Exception e) {
        }
        return position;
    }
    
    /**
     * Function to save a position in the Data Base
     * @return 1 if the position was saved, otherwise 0
     */
    public int save(Position position){
        int status = 0;
        try {
            this.connect();
            String query = "INSERT INTO `positions` (`id_positions`, `name`) VALUES (NULL, ?);";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setString(1, position.getName());
            status = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.out.println("PositionDao: "+e.getMessage());
        }
        return status;
    }
    
    /**
     * Function to find Functions an specified position
     * @return List of Functions of the specified position
     */
    public ArrayList<Function> findFunctionsByIdPosition(int id){
        ArrayList<Function> functionsOfPosition = new ArrayList<Function>();
        
        try {
            String query = "SELECT F.id_functions, F.name, F.description \n" +
                           "FROM positions_has_functions PF \n" +
                           "INNER JOIN functions F \n" +
                           "ON PF.functions_id_functions = F.id_functions \n" +
                           "WHERE PF.positions_id_positions = 3";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                int t = 1;
                
                Function function = new Function();
                function.setId(rs.getInt(t++));
                function.setName(rs.getString(t++));
                function.setDescription(rs.getString(t++));
                
                functionsOfPosition.add(function);
            }
            
        } catch (Exception e) {
            System.out.println("PositionDao: "+e.getMessage());
        }
        
        return functionsOfPosition;
    }
    
}
