package com.subscriptionapp.dao;

import com.subscriptionapp.model.Subscription;
import com.subscriptionapp.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAOImpl implements SubscriptionDAO {

    @Override
    public void subscribe(Subscription subscription) throws Exception {
        String query = "INSERT INTO subscriptions (user_id, product_id, frequency, is_active) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, subscription.getUserId());
            stmt.setInt(2, subscription.getProductId());
            stmt.setString(3, subscription.getFrequency());
            stmt.setBoolean(4, subscription.isActive());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Subscription> getSubscriptionsByUser(int userId) throws Exception {
        List<Subscription> subscriptions = new ArrayList<>();
        String query = "SELECT * FROM subscriptions WHERE user_id=?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
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
        }
        return subscriptions;
    }

    @Override
    public void cancelSubscription(int subscriptionId) throws Exception {
        String query = "DELETE FROM subscriptions WHERE id=?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, subscriptionId);
            stmt.executeUpdate();
        }
    }
}
