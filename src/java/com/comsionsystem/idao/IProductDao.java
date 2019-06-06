package com.comsionsystem.idao;

import com.comisionsystem.model.Product;
import java.util.ArrayList;

/**
 *
 * @author David Viuche
 */
public interface IProductDao {
    public ArrayList<Product> findAll();
    public Product findProductById(int id);
    public int save(Product product);
    public int delete(int id);
    public int update(Product product);
}
