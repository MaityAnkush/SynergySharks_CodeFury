package com.subscriptionapp.service;

import com.subscriptionapp.dao.AdminDAO;
import com.subscriptionapp.dao.AdminDAOImpl;
import com.subscriptionapp.model.Product;
import com.subscriptionapp.model.Subscription;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDAO adminDAO = new AdminDAOImpl();

    @Override
    public void addProduct(Product product) throws Exception {
        adminDAO.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) throws Exception {
        adminDAO.updateProduct(product);
    }

    @Override
    public void deleteProduct(int productId) throws Exception {
        adminDAO.deleteProduct(productId);
    }

    @Override
    public void activateSubscription(int subscriptionId) throws Exception {
        adminDAO.activateSubscription(subscriptionId);
    }

    @Override
    public void deactivateSubscription(int subscriptionId) throws Exception {
        adminDAO.deactivateSubscription(subscriptionId);
    }

    @Override
    public Product getProductById(int productId) throws Exception {
        return adminDAO.getProductById(productId);
    }

    @Override
    public List<Product> getAllProducts() throws Exception {
        return adminDAO.getAllProducts();
    }

    @Override
    public List<Subscription> getAllSubscriptions() throws Exception {
        return adminDAO.getAllSubscriptions();
    }
}
