/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

/**
 *
 * @author c0572709
 */
@ApplicationScoped
public class ProductList {
    private List<Product> productList = new ArrayList<>();
    
    public ProductList() {
        //connect to database
        //select all products
        //create Products
        //add Product to the List
        
    }
    
    public JsonArray toJSON() {
        JsonArrayBuilder json = Json.createArrayBuilder();
        for(Product p : productList)
            json.add(p.toJSON());
        return json.build();
    }
    
    //Connection //DB connector
    
    public Product get(int productId) {
        Product result = null;
        for (Product p : productList)
            result = p;
        return result;
    }
    
    public void add(Product p) {
        
    }
    
    public void remove(Product p) throws Exception {
        remove(p.getProductId());
    }
    
    public void remove(int productId) {
        int result = doUpdate("");
        
        if(result > 0) {
            //the successful, now remove from list
            Product original = get(productId);
            productList.remove(original);
        }
    }
    
    public void set(int productId, Product product) {
        //Update database first, then volitile memory
        int result = doUpdate("UPDATE Product SET Name = ?, Description = ?. Quantity = ? WHERE ProductId = ?" ,
                product.getName(),
                product.getDescription(),
                String.valueOf(product.getQuantity()),
                String.valueOf(productId)
        );
        if(result == 1) {
            Product original = get(productId);
            original.setName(product.getName());
            //...
        }
        
        Product original = get(productId);
        original.setName(product.getName());
        original.setDescription(product.getDescription());
        original.setQuantity(product.getQuantity());
    }
}
