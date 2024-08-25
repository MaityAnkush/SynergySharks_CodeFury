package com.subscriptionapp.dao;

import com.subscriptionapp.model.Product;
import com.subscriptionapp.model.Subscription;
import java.util.List;

public interface AdminDAO {
    void addProduct(Product product) throws Exception;
    void updateProduct(Product product) throws Exception;
    void deleteProduct(int productId) throws Exception;
    void activateSubscription(int subscriptionId) throws Exception;
    void deactivateSubscription(int subscriptionId) throws Exception;
    Product getProductById(int productId) throws Exception;
    List<Product> getAllProducts() throws Exception;
    List<Subscription> getAllSubscriptions() throws Exception;
}

