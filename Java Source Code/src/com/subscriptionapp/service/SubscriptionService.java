package com.subscriptionapp.service;

import com.subscriptionapp.model.Subscription;
import java.util.List;

public interface SubscriptionService {
    void subscribe(Subscription subscription) throws Exception;
    List<Subscription> getSubscriptionsByUser(int userId) throws Exception;
    void cancelSubscription(int subscriptionId) throws Exception;
}
