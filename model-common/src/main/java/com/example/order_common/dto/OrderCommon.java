package com.example.order_common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCommon {
    private String id;
    private String product;
    private int quantity;
    private double price;
    private LocalDateTime dateTime;

    public void setDateTimeNow() {
        this.dateTime = LocalDateTime.now();
    }
}

