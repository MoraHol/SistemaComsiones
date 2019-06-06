
package com.comisionsystem.dao;

import com.comisionsystem.model.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author David Viuche
 */
public class CategoryDaoImpl extends ConnectionSQL  {

   
    public Category findCategoryById(int id) {
        Category category = new Category();
        try {
            this.connect();
            String query = "SELECT * FROM `categories_products` WHERE id_categories_products = ?";
            PreparedStatement pstm = this.getJdbcConnection().prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int t = 1;
                category.setId(rs.getInt(t++));
                category.setName(rs.getString(t++));
            }
            this.disconnect();
        } catch (Exception e) {
        }
        return category;
    }
}
