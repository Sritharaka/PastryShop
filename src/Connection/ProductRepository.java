/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Product;

/**
 *
 * @author ASUS
 */
public class ProductRepository implements Repository<Product>{
    
    private ConnectionManager connectionManager;
    
    public ProductRepository() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        connectionManager = new ConnectionManager();
        connectionManager.connect();
    }

    @Override
    public void Insert(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> Read(String query) {
        List<Product> products = new ArrayList<>();
        try {
            
            ResultSet rs = connectionManager.executeResults(query);
            
            // iterate through the java resultset
            while (rs.next())
            {
                Product p = new Product();
                int id = rs.getInt("ProductID");
                double price = rs.getDouble("ProductPrice");
                String name = rs.getString("ProductName");
                
                p.setId(id);
                p.setName(name);
                p.setPrice(price);
                
                products.add(p);
                // print the results
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return products;
    }

}
