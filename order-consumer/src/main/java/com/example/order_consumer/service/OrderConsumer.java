package com.example.order_consumer.service;

import java.time.Instant;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.order_consumer.model.LogMessage;
import com.example.order_common.dto.OrderCommon;
import com.example.order_consumer.model.Transaction;
import com.example.order_consumer.repository.TransactionRepository;

@Service
public class OrderConsumer {

    private final TransactionRepository transactionRepo;
    /*
     * NOTE: KafkaTemplate does not need to be configured again
     * Just define the param here (LogMessage) and it should be fine?
     */
    private final KafkaTemplate<String, LogMessage> logTemplate;

    private OrderConsumer(TransactionRepository transactionRepo, KafkaTemplate<String, LogMessage> logTemplate) {
        this.transactionRepo = transactionRepo;
        this.logTemplate = logTemplate;
    }

    @KafkaListener(topics = "orders-topic")
    public void consume(OrderCommon order) {
        try {
            /*
             * NOTE: Throw exception early to simulate failure
             * throw new RuntimeException("test_gagal");
             */
            Transaction transaction = new Transaction(order);
            transactionRepo.save(transaction);

            LogMessage successLog = new LogMessage(
                    Instant.now(),
                    "order-consumer",
                    "SUCCESS",
                    null
            );
            logTemplate.send("logs-topic", order.getId(), successLog);
        } catch (Exception e) {
            LogMessage errorLog = new LogMessage(
                    Instant.now(),
                    "order-consumer",
                    "ERROR",
                    e.getMessage()
            );
            logTemplate.send("logs-topic", order.getId(), errorLog);
        }
    }
}