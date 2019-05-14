/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comisionsystem.dao;

import com.comisionsystem.model.Client;
import com.comsionsystem.idao.IClientDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Alexis Holguin github:MoraHol
 */
public class ClientDaoImpl extends ConnectionSQL implements IClientDao {

    @Override
    public ArrayList<Client> findAll() {
        ArrayList<Client> list = new ArrayList<>();
        try {
            this.connect();
            String query = "SELECT * FROM `clients`";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(this.findClientById(rs.getInt("id_clients")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public Client findClientById(int id) {
        Client client = new Client();
        try {
            this.connect();
            String query = "SELECT * FROM `clients` WHERE id_clients = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int t = 1;
                client.setId(rs.getInt(t++));
                client.setNit(rs.getString(t++));
                client.setFirstName(rs.getString(t++));
                client.setSecondName(rs.getString(t++));
                client.setFirstSurName(rs.getString(t++));
                client.setSecondSurName(rs.getString(t++));
                client.setAdressPersonal(rs.getString(t++));
                client.setAddressCompany(rs.getString(t++));
                // consulta emails de cliente
                PreparedStatement pstm1 = this.getJdbcConnection().prepareStatement("SELECT emails.email FROM `clients` INNER JOIN emails ON id_clients = emails.clients_id_clients WHERE clients.id_clients = ?");
                pstm1.setInt(1, client.getId());
                ResultSet rs1 = pstm1.executeQuery();
                while (rs1.next()) {
                    client.getEmails().add(rs1.getString(1));
                }
                // consulta de numeros de telefono de cliente
                PreparedStatement pstm2 = this.getJdbcConnection().prepareStatement("SELECT cellphone_numbers.cell_phone_number FROM cellphone_numbers INNER JOIN clients ON clients.id_clients = cellphone_numbers.clients_id_clients WHERE clients.id_clients = ?");
                pstm2.setInt(1, client.getId());
                ResultSet rs2 = pstm2.executeQuery();
                while (rs2.next()) {
                    client.getCellPhoneNumbers().add(rs2.getString(1));
                }
                // consulta de telefono de cliente
                PreparedStatement pstm3 = this.getJdbcConnection().prepareStatement("SELECT phone_numbers.phone_number FROM phone_numbers INNER JOIN clients ON clients.id_clients = phone_numbers.clients_id_clients WHERE clients.id_clients = ?");
                pstm3.setInt(1, client.getId());
                ResultSet rs3 = pstm3.executeQuery();
                while (rs3.next()) {
                    client.getCellPhoneNumbers().add(rs3.getString(1));
                }
            }
            this.disconnect();
        } catch (Exception e) {
        }
        return client;
    }

    @Override
    public int save(Client client) {
        int result = 0;
        try {
            this.connect();
            String query = "INSERT INTO `clients` (`id_clients`, `nit`, `name_company`, `first_name`, `second_name`, `fisrt_surname`, `second_surname`, `address_personal`, `address_company`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setString(1, client.getNit());
            pstm.setString(2, client.getNameCompany());
            pstm.setString(3, client.getFirstName());
            pstm.setString(4, client.getSecondName());
            pstm.setString(5, client.getFirstSurName());
            pstm.setString(6, client.getSecondSurName());
            pstm.setString(7, client.getAdressPersonal());
            pstm.setString(8, client.getAddressCompany());
            result = pstm.executeUpdate();
            // guardar los emails
            for (String email : client.getEmails()) {
                PreparedStatement pstm1 = this.getJdbcConnection().prepareStatement("INSERT INTO `emails` (`id_email`, `email`, `clients_id_clients`) VALUES (NULL, ?, ?)");
                pstm1.setString(1, email);
                pstm1.setInt(2, client.getId());
                result = pstm.executeUpdate();
            }
            // guardar numeros de celular
            for (String cellPhoneNumber : client.getCellPhoneNumbers()) {
                PreparedStatement pstm2 = this.getJdbcConnection().prepareStatement("INSERT INTO `cellphone_numbers` (`id_cellphone_number`, `cell_phone_number`, `clients_id_clients`) VALUES (NULL, ?, ?);");
                pstm2.setString(1, cellPhoneNumber);
                pstm2.setInt(2, client.getId());
                result = pstm2.executeUpdate();
            }
            // guardar telefonos de cliente
            for (String phoneNumber : client.getPhoneNumbers()) {
                PreparedStatement pstm3 = this.getJdbcConnection().prepareStatement("INSERT INTO `phone_numbers` (`id_phone_number`, `phone_number`, `clients_id_clients`) VALUES (NULL, ?, ?)");
                pstm3.setString(1, phoneNumber);
                pstm3.setInt(2, client.getId());
                result = pstm3.executeUpdate();
            }
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
            String query = "DELETE FROM `clients` WHERE `clients`.`id_clients` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            result = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public int update(Client client) {
        int result = 0;
        try {
            this.connect();
            String query = "UPDATE `clients` SET `nit` = ?, `name_company` = ?, `first_name` = ?, `second_name` = ?, `fisrt_surname` = ?, `second_surname` = ?, `address_personal` = ?, `address_company` = ? WHERE `clients`.`id_clients` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setString(1, client.getNit());
            pstm.setString(2, client.getNameCompany());
            pstm.setString(3, client.getFirstName());
            pstm.setString(4, client.getSecondName());
            pstm.setString(5, client.getFirstSurName());
            pstm.setString(6, client.getSecondSurName());
            pstm.setString(7, client.getAdressPersonal());
            pstm.setString(8, client.getAddressCompany());
            pstm.setInt(9, client.getId());
            result = pstm.executeUpdate();
            // guardar los emails
            for (String email : client.getEmails()) {
                PreparedStatement pstm1 = this.getJdbcConnection().prepareStatement("UPDATE `emails` SET `email` = ? WHERE `emails`.`clients_id_clients` = ?");
                pstm1.setString(1, email);
                pstm1.setInt(2, client.getId());
                result = pstm.executeUpdate();
            }
            // guardar numeros de celular
            for (String cellPhoneNumber : client.getCellPhoneNumbers()) {
                PreparedStatement pstm2 = this.getJdbcConnection().prepareStatement("UPDATE `cellphone_numbers` SET `cell_phone_number` = ? WHERE  `cellphone_numbers`.`clients_id_clients` = ?");
                pstm2.setString(1, cellPhoneNumber);
                pstm2.setInt(2, client.getId());
                result = pstm2.executeUpdate();
            }
            // guardar telefonos de cliente
            for (String phoneNumber : client.getPhoneNumbers()) {
                PreparedStatement pstm3 = this.getJdbcConnection().prepareStatement("UPDATE `phone_numbers` SET `phone_number` = ? WHERE `phone_numbers`.`clients_id_clients` = ?");
                pstm3.setString(1, phoneNumber);
                pstm3.setInt(2, client.getId());
                result = pstm3.executeUpdate();
            }
            this.disconnect();
        }catch(Exception e){
            
        }
        return result;
    }
}
