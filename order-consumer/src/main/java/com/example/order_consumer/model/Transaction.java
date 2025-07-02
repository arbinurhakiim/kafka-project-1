package com.example.order_consumer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.order_common.dto.OrderCommon;

@Entity
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;
    private String product;
    private int quantity;
    private double price;

    public Transaction(OrderCommon order) {
        this.orderId = order.getId();
        this.product = order.getProduct();
        this.quantity = order.getQuantity();
        this.price = order.getPrice();
    }
}