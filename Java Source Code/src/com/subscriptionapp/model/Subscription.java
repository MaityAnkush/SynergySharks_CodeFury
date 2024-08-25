package com.subscriptionapp.model;

public class Subscription {
    private int id;
    private int userId;
    private int productId;
    private String frequency;
    private boolean isActive;

    public Subscription(int userId, int productId, String frequency, boolean isActive) {
        this.userId = userId;
        this.productId = productId;
        this.frequency = frequency;
        this.isActive = isActive;
    }

    public Subscription(int id, int userId, int productId, String frequency, boolean isActive) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.frequency = frequency;
        this.isActive = isActive;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
