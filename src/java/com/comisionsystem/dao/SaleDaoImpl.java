/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comisionsystem.dao;

import com.comisionsystem.model.Sale;
import com.comsionsystem.idao.ISaleDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author David Viuche
 */
public class SaleDaoImpl extends ConnectionSQL implements ISaleDao {

    @Override
    public ArrayList<Sale> findAll() {
        ArrayList<Sale> list = new ArrayList<>();
        try {
            this.connect();
            String query = "SELECT * FROM `sales`";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(this.findClientById(rs.getInt("id_sales")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public Sale findClientById(int id) {
        Sale sale = new Sale();
        try {
            this.connect();
            String query = "SELECT * FROM `sales` WHERE id_sales = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int t = 1;
                sale.setId(rs.getInt(t++));
                sale.setEmployee(new EmployeeDaoImpl().findById(rs.getInt(t++)));
                sale.setClient(new ClientDaoImpl().findClientById(rs.getInt(t++)));
                sale.setProduct(new ProductDaoImpl().findProductById(rs.getInt(t++)));
                sale.setQuantity(rs.getInt(t++));
                sale.setCreateAt(rs.getDate(t++));                
            }
            this.disconnect();
        } catch (Exception e) {
        }
        return sale;
    }

    @Override
    public int save(Sale sale) {
        int result = 0;
        try {
            this.connect();
            String query = "INSERT INTO `sales` (`id_sales`, `employees_id_employee`, `products_id_product`, `clients_id_clients`, `quantity`, `create_at`) VALUES (NULL, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, sale.getEmployee().getId());
            pstm.setInt(2, sale.getProduct().getId());
            pstm.setInt(3, sale.getClient().getId());
            pstm.setInt(4, sale.getQuantity());
            pstm.setDate(5, sale.getCreateAt());
            
            result = pstm.executeUpdate();
           
            this.disconnect();
        } catch (Exception e) {

        }
        return result;
    }

    @Override
    public int delete(int id) {
        int result = 0;
        try {
            this.connect();
            String query = "DELETE FROM `sales` WHERE `sales`.`id_sales` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            result = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public int update(Sale sale) {
        int result = 0;
        try {
            this.connect();
            String query = "UPDATE `sales` SET `employees_id_employee` = ?, `products_id_product` = ?, `clients_id_clients` = ?, `quantity` = ?, `create_at` = ? WHERE `sales`.`id_sales` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, sale.getEmployee().getId());
            pstm.setInt(2, sale.getProduct().getId());
            pstm.setInt(3, sale.getClient().getId());
            pstm.setInt(4, sale.getQuantity());
            pstm.setDate(5, sale.getCreateAt());
            result = pstm.executeUpdate();
            
            this.disconnect();
        }catch(Exception e){
            
        }
        return result;
    }
}
