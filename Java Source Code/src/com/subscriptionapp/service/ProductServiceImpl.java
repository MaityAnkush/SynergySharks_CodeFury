package com.subscriptionapp.service;

import com.subscriptionapp.dao.ProductDAO;
import com.subscriptionapp.dao.ProductDAOImpl;
import com.subscriptionapp.model.Product;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public Product getProductById(int productId) throws Exception {
        return productDAO.getProductById(productId);
    }

    @Override
    public List<Product> getAllProducts() throws Exception {
        return productDAO.getAllProducts();
    }
}
