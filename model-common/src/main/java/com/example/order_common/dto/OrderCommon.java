package com.example.order_common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    // Add this annotation to format the date
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dateTime;

    public void setDateTimeNow() {
        this.dateTime = LocalDateTime.now();
    }
}