package com.subscriptionapp.service;

import com.subscriptionapp.dao.SubscriptionDAO;
import com.subscriptionapp.dao.SubscriptionDAOImpl;
import com.subscriptionapp.model.Subscription;
import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionDAO subscriptionDAO = new SubscriptionDAOImpl();

    @Override
    public void subscribe(Subscription subscription) throws Exception {
        subscriptionDAO.subscribe(subscription);
    }

    @Override
    public List<Subscription> getSubscriptionsByUser(int userId) throws Exception {
        return subscriptionDAO.getSubscriptionsByUser(userId);
    }

    @Override
    public void cancelSubscription(int subscriptionId) throws Exception {
        subscriptionDAO.cancelSubscription(subscriptionId);
    }
}
