
package com.comisionsystem.dao;

import com.comisionsystem.model.Product;
import com.comsionsystem.idao.IProductDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @author David Viuche
 */
public class ProductDaoImpl extends ConnectionSQL implements IProductDao {

    @Override
    public ArrayList<Product> findAll() {
        ArrayList<Product> list = new ArrayList<>();
        try {
            this.connect();
            String query = "SELECT * FROM `products`";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(this.findProductById(rs.getInt("id_product")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public Product findProductById(int id) {
        Product product = new Product();
        try {
            this.connect();
            String query = "SELECT * FROM `products` WHERE id_product = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int t = 1;
                product.setId(rs.getInt(t++));
                product.setName(rs.getString(t++));
                product.setDescription(rs.getString(t++));
                product.setPrice(rs.getDouble(t++));
                
                
               
            }
            this.disconnect();
        } catch (Exception e) {
        }
        return product;
    }

    @Override
    public int save(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        int result = 0;
        try {
            this.connect();
            String query = "DELETE FROM `products` WHERE `products`.`id_product` = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            result = pstm.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
        }
        return result;
    }
}