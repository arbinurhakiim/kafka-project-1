package com.example.order_producer.controller;

import com.example.order_common.dto.OrderCommon;
import com.example.order_producer.service.KafkaProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    public final KafkaProducerService kafkaProducerService;

    public OrderController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }


    @PostMapping("/api/orders")
    public String createOrder(@RequestBody OrderCommon order) {

        order.setDateTimeNow();
        this.kafkaProducerService.sendOrder(order);
        return "OrderCommon created: " + order;
    }
}
