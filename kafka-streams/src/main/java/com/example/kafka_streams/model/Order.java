package com.example.kafka_streams.model;

import java.util.Date;


@Deprecated
public class Order {
    private String id;
    private double amount;
    private Date timestamp;
    
    // Default constructor for serialization
    public Order() {
    }
    
    public Order(String id, double amount, Date timestamp) {
        this.id = id;
        this.amount = amount;
        this.timestamp = timestamp;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}