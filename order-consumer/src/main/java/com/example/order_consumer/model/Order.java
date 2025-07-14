package com.example.order_consumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Deprecated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String id;
    private String product;
    private int quantity;
    private double price;
}