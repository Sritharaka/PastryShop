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
import models.Stock;
import models.StockList;


/**
 *
 * @author ASUS
 */
public class StockRepository implements Repository<Stock>{

    private final ConnectionManager connectionManager;

    public StockRepository() {
        this.connectionManager = new ConnectionManager();
    }
    
    
    
    @Override
    public void Insert(Stock item) {
         PreparedStatement pst;
        try {
            connectionManager.connect();
            pst = connectionManager.conn.prepareStatement("insert into stock values(?,?,?,?)");
            pst.setString(1, null);            
            pst.setString(2, item.getDescription());
            pst.setInt(3, item.getProductID());
            pst.setInt(4, item.getQuantity());
           
            pst.executeUpdate();
            pst.close();
            connectionManager.conn.close();
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }

    @Override
    public List<StockList> Read(String sql) {
        String query = "select s.StockID, p.ProductName,  s.StockDesc, s.ProductID, s.Quantity,  p.ProductDesc, p.ProductSuppliers, p.ProductPrice from stock s inner join product p on s.ProductID = p.ProductID";
        List<StockList> stock = new ArrayList<>();
        try {
            connectionManager.connect();
            ResultSet rs = connectionManager.executeResults(query);
            
            // iterate through the java resultset
            while (rs.next())
            {
                StockList item = new StockList();
                int id = rs.getInt("StockID");
                int quantity = rs.getInt("Quantity");
                String description = rs.getString("StockDesc");
                int productId = rs.getInt("ProductID");
                double price = rs.getDouble("ProductPrice");
                String name = rs.getString("ProductName"); 
                String productDescription = rs.getString("ProductDesc");
                String suppliers = rs.getString("ProductSuppliers");
                
                item.setDescription(description);
                item.setId(id);
                item.setProductID(productId);
                item.setQuantity(quantity);
                item.setProductDescription(productDescription);
                item.setProductName(name);
                item.setProductPrice(price);
                item.setProductSuppliers(suppliers);
                
                stock.add(item);
                // print the results
            }
            
            connectionManager.conn.close();
            
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return stock;
    }

    @Override
    public List<Stock> ReadAll() {
        List<Stock> stock = new ArrayList<>();
        try {
            connectionManager.connect();
            ResultSet rs = connectionManager.executeResults("SELECT * FROM Product");
            
            // iterate through the java resultset
            while (rs.next())
            {
                Stock item = new Stock();
                int id = rs.getInt("StockID");
                int quantity = rs.getInt("Quantity");
                String description = rs.getString("StockDesc");
                int productId = rs.getInt("ProductID");
                
                item.setDescription(description);
                item.setId(id);
                item.setProductID(productId);
                item.setQuantity(quantity);
               
                stock.add(item);
                // print the results
            }
            
            connectionManager.conn.close();
            
        } catch (SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return stock;
    }
    
}
