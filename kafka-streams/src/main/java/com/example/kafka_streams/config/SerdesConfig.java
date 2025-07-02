package com.example.kafka_streams.config;

import com.example.order_common.dto.OrderCommon;
import com.example.kafka_streams.model.OrderSerdes;

import org.apache.kafka.common.serialization.Serde;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SerdesConfig {
    
    @Bean
    public Serde<OrderCommon> orderSerde() {
        return new OrderSerdes();
    }
}