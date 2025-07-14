package com.example.order_producer.service;

import com.example.order_common.dto.OrderCommon;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, OrderCommon> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, OrderCommon> kafkaTemplate) {this.kafkaTemplate = kafkaTemplate;}

    public void sendOrder(OrderCommon order) {
        kafkaTemplate.send("orders-topic", order.getId(), order);
    }
}