package org.example;

import com.subscriptionapp.dao.SubscriptionDAO;
import com.subscriptionapp.dao.SubscriptionDAOImpl;
import com.subscriptionapp.model.Subscription;
import com.subscriptionapp.service.SubscriptionServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionServiceImplTest {
    private SubscriptionDAO subscriptionDAO;
    private SubscriptionServiceImpl subscriptionService;
    private Connection connection;

    @BeforeEach
    public void setUp() throws Exception {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");

        subscriptionDAO = new SubscriptionDAOImpl();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE subscriptions (id INT PRIMARY KEY AUTO_INCREMENT, user_id INT, product_id INT, frequency VARCHAR(255), is_active BOOLEAN)");
        }

        // Initialize the service with the DAO
        subscriptionService = new SubscriptionServiceImpl(subscriptionDAO);
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testSubscribe() throws Exception {
        Subscription subscription = new Subscription(1, 1, "Monthly", true);
        subscriptionService.subscribe(subscription);

        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUser(1);
        assertEquals(1, subscriptions.size());
        assertEquals(subscription.getUserId(), subscriptions.get(0).getUserId());
        assertEquals(subscription.getProductId(), subscriptions.get(0).getProductId());
//        assertEquals(subscription.getFrequency(), subscriptions.get(0).getFrequency());
//        assertEquals(subscription.isActive(), subscriptions.get(0).isActive());
    }

    @Test
    public void testGetSubscriptionsByUser() throws Exception {
        Subscription subscription1 = new Subscription(1, 1, "Monthly", true);
        Subscription subscription2 = new Subscription(1, 2, "Weekly", false);
        subscriptionService.subscribe(subscription1);
        subscriptionService.subscribe(subscription2);

        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUser(1);
        assertEquals(2, subscriptions.size());
    }

    @Test
    public void testCancelSubscription() throws Exception {
        Subscription subscription = new Subscription(1, 1, "Monthly", true);
        subscriptionService.subscribe(subscription);

        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUser(1);
        assertEquals(1, subscriptions.size());

        // Assuming the ID of the inserted subscription is 1
        subscriptionService.cancelSubscription(1);

        subscriptions = subscriptionService.getSubscriptionsByUser(1);
        Assert.assertTrue(subscriptions.isEmpty());
    }
}
