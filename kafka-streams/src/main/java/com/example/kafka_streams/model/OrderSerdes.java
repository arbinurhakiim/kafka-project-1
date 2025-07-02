package com.example.kafka_streams.model;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import com.example.order_common.dto.OrderCommon;

public class OrderSerdes implements Serde<OrderCommon> {
    
    @Override
    public Serializer<OrderCommon> serializer() {
        return new JsonSerializer<>();
    }

    @Override
    public Deserializer<OrderCommon> deserializer() {
        return new JsonDeserializer<>(OrderCommon.class);
    }
}