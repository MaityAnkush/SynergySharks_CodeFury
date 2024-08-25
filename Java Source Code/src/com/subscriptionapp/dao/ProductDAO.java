package com.subscriptionapp.dao;

import com.subscriptionapp.model.Product;
import java.util.List;

public interface ProductDAO {
    Product getProductById(int productId) throws Exception;
    List<Product> getAllProducts() throws Exception;
}
