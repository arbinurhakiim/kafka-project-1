package com.example.kafka_streams.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HourlyTransactionConsumer {

    /**
     * Listens to the topic containing hourly transaction counts.
     * The method signature is updated to expect a Long value, which is the count.
     * The key is parsed to extract the product name and timestamp.
     */
    @KafkaListener(topics = "hourly-transaction-topic",
            groupId = "hourly-consumer-group"
            // Ensure your containerFactory is configured for <String, Long>
            // or remove it to use the default.
            // containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(ConsumerRecord<String, Long> record) {

        // The value is the count calculated by the stream.
        Long count = record.value();

        // The key is a composite string like "productName@timestamp".
        // We need to parse it.
        String[] keyParts = record.key().split("@");
        String product = "Unknown Product";
        String windowStartTime = "Unknown Timestamp";

        if (keyParts.length == 2) {
            product = keyParts[0];
            windowStartTime = keyParts[1];
        }

        // Print the final, formatted output.
        System.out.println("==============================");
        System.out.println("Hourly transaction for " + product + ": " + count);
        System.out.println("Window start time: " + windowStartTime);
        System.out.println("==============================");
    }
}