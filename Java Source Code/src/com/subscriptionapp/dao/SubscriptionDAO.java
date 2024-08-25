package com.subscriptionapp.dao;

import com.subscriptionapp.model.Subscription;
import java.util.List;

public interface SubscriptionDAO {
    void subscribe(Subscription subscription) throws Exception;
    List<Subscription> getSubscriptionsByUser(int userId) throws Exception;
    void cancelSubscription(int subscriptionId) throws Exception;
}
