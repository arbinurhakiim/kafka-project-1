package com.example.kafka_streams.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.Instant;

public class HourlyTransaction {

    private String productName;

    // This annotation solves the error by formatting the Instant as a String
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private Instant eventTimestamp;

    private Long measurement;

    // A no-argument constructor is needed for JSON deserialization
    public HourlyTransaction() {
    }

    // Constructor with all fields
    public HourlyTransaction(String productName, Instant eventTimestamp, Long measurement) {
        this.productName = productName;
        this.eventTimestamp = eventTimestamp;
        this.measurement = measurement;
    }

    // Getters and Setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Instant getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(Instant eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public Long getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Long measurement) {
        this.measurement = measurement;
    }
}