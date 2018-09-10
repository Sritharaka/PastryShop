/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    private final ConnectionManager connectionManager;
    
    public ProductRepository(){
        connectionManager = new ConnectionManager();
        
    }

    @Override
    public void Insert(Product product) {
        PreparedStatement pst;
        try {
            connectionManager.connect();
            pst = connectionManager.conn.prepareStatement("insert into product values(?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, product.getName());
            pst.setString(3, product.getDescription());
            pst.setString(4, product.getSuppliers());
            pst.setDouble(5, product.getPrice());          
            pst.executeUpdate();
            pst.close();
            connectionManager.conn.close();
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }

    @Override
    public List<Product> Read(String query) {
        List<Product> products = new ArrayList<>();
        try {
            connectionManager.connect();
            ResultSet rs = connectionManager.executeResults(query);
            
            // iterate through the java resultset
            while (rs.next())
            {
                Product p = new Product();
                int id = rs.getInt("ProductID");
                double price = rs.getDouble("ProductPrice");
                String name = rs.getString("ProductName");
                String suppliers = rs.getString("ProductSuppliers");
                
                p.setId(id);
                p.setName(name);
                p.setPrice(price);
                p.setSuppliers(suppliers);
                
                products.add(p);
                // print the results
                 connectionManager.close();
            }
            
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return products;
    }

    @Override
    public List<Product> ReadAll() {
       List<Product> products = new ArrayList<>();
        try {
            connectionManager.connect();
            ResultSet rs = connectionManager.executeResults("SELECT * FROM Product");
            
            // iterate through the java resultset
            while (rs.next())
            {
                Product p = new Product();
                int id = rs.getInt("ProductID");
                double price = rs.getDouble("ProductPrice");
                String name = rs.getString("ProductName");
                String suppliers = rs.getString("ProductSuppliers");
                
                p.setId(id);
                p.setName(name);
                p.setPrice(price);
                p.setSuppliers(suppliers);
                 
                products.add(p);
                // print the results
            }
            
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return products;
    }

}
