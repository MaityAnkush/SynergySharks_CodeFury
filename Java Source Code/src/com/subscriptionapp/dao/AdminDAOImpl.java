package com.subscriptionapp.dao;

import com.subscriptionapp.model.Product;
import com.subscriptionapp.model.Subscription;
import com.subscriptionapp.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public void addProduct(Product product) throws Exception {
        String query = "INSERT INTO products (name, description, price, is_active) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setBoolean(4, product.isActive());
            stmt.executeUpdate();
        }
    }

    @Override
    public void updateProduct(Product product) throws Exception {
        String query = "UPDATE products SET name=?, description=?, price=?, is_active=? WHERE id=?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setBoolean(4, product.isActive());
            stmt.setInt(5, product.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteProduct(int productId) throws Exception {
        String query = "DELETE FROM products WHERE id=?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        }
    }

    @Override
    public void activateSubscription(int subscriptionId) throws Exception {
        updateSubscriptionStatus(subscriptionId, true);
    }

    @Override
    public void deactivateSubscription(int subscriptionId) throws Exception {
        updateSubscriptionStatus(subscriptionId, false);
    }

    private void updateSubscriptionStatus(int subscriptionId, boolean status) throws Exception {
        String query = "UPDATE subscriptions SET is_active=? WHERE id=?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBoolean(1, status);
            stmt.setInt(2, subscriptionId);
            stmt.executeUpdate();
        }
    }

    @Override
    public Product getProductById(int productId) throws Exception {
        String query = "SELECT * FROM products WHERE id=?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getBoolean("is_active")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try (Connection connection = DBUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getBoolean("is_active")
                ));
            }
        }
        return products;
    }

    @Override
    public List<Subscription> getAllSubscriptions() throws Exception {
        List<Subscription> subscriptions = new ArrayList<>();
        String query = "SELECT * FROM subscriptions";
        try (Connection connection = DBUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                subscriptions.add(new Subscription(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("product_id"),
                        rs.getString("frequency"),
                        rs.getBoolean("is_active")
                ));
            }
        }
        return subscriptions;
    }
}
