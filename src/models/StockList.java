/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author ASUS
 */
public class StockList extends Stock {
    
    private String productName;
    private String productDescription;
    private double productPrice;
    private String productSuppliers;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductSuppliers() {
        return productSuppliers;
    }

    public void setProductSuppliers(String productSuppliers) {
        this.productSuppliers = productSuppliers;
    }
    
    
    
}
