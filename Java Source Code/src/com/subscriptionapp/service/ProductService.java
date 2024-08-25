package com.subscriptionapp.service;

import com.subscriptionapp.model.Product;
import java.util.List;

public interface ProductService {
    Product getProductById(int productId) throws Exception;
    List<Product> getAllProducts() throws Exception;
}
