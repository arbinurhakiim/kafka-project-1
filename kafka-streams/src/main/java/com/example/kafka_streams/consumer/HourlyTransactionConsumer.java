package com.example.kafka_streams.consumer;

import com.example.kafka_streams.model.HourlyTransaction;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HourlyTransactionConsumer {

    /**
     * Listens to the topic containing hourly transaction counts.
     * The method signature is updated to expect an HourlyTransaction object.
     */
    @KafkaListener(topics = "hourly-transaction-topic",
            groupId = "hourly-consumer-group",
            containerFactory = "hourlyTransactionListenerContainerFactory")
    public void consume(ConsumerRecord<String, HourlyTransaction> record) {
        // This entire block of code has been moved inside the consume method.

        // The key is the product name.
        String product = record.key();

        // The value is the deserialized HourlyTransaction object.
        HourlyTransaction transaction = record.value();
        Long count = transaction.getMeasurement();

        // Print the final, formatted output.
        System.out.println("==============================");
        System.out.println("Hourly transaction for " + product + ": " + count);
        System.out.println("Window start time: " + transaction.getEventTimestamp());
        System.out.println("==============================");
    }
}