//package com.example.kafka_streams.stream;
//
//import com.example.order_common.dto.OrderCommon;
//import org.apache.kafka.common.serialization.Serde;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.KeyValue;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.kstream.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.Duration;
//
//@Configuration
//public class HourlyTransactionStream {
//
//    @Bean(name = "TransactionPerHourAgregation")
//    public KStream<String, OrderCommon> kStream(StreamsBuilder streamsBuilder, Serde<OrderCommon> orderSerde) {
//        System.out.println("start stream for hourly transaction count");
//
//        KStream<String, OrderCommon> stream = streamsBuilder.stream("orders-topic",
//                Consumed.with(Serdes.String(), orderSerde)
//                        .withTimestampExtractor((record, partitionTime) -> {
//                            OrderCommon order = (OrderCommon) record.value();
//                            return order.getDateTime().toInstant(java.time.ZoneOffset.UTC).toEpochMilli();
//                        })
//        );
//
//        stream
//                // 1. Group by the product name
//                .groupBy((key, order) -> order.getProduct())
//
//                // 2. Create a 1-hour window
//                .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofHours(1)))
//
//                // 3. Count the records in each window
//                .count(Materialized.with(Serdes.String(), Serdes.Long()))
//
//                // 4. Convert back to a regular stream
//                .toStream()
//
//                // 5. Format the output key and value
//                .map((windowedKey, count) -> new KeyValue<>(
//                        windowedKey.key() + "@" + windowedKey.window().startTime().toString(),
//                        count
//                ))
//
//                // 6. Send to the final topic
//                .to("hourly-transaction-topic", Produced.with(Serdes.String(), Serdes.Long()));
//
//        return stream;
//    }
//}



package com.example.kafka_streams.stream;

import com.example.kafka_streams.model.HourlyTransaction;
import com.example.order_common.dto.OrderCommon;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;


import java.time.Duration;

@Configuration
public class HourlyTransactionStream {

    @Bean(name = "TransactionPerHourAgregation")
    public KStream<String, OrderCommon> kStream(StreamsBuilder streamsBuilder, Serde<OrderCommon> orderSerde) {
        System.out.println("start stream for hourly transaction count");

        // Define a JSON Serde for our new HourlyTransaction DTO.
        // Spring Boot with spring-kafka will configure the ObjectMapper automatically.
        Serde<HourlyTransaction> hourlyTransactionSerde = new JsonSerde<>(HourlyTransaction.class);

        KStream<String, OrderCommon> stream = streamsBuilder.stream("orders-topic",
                Consumed.with(Serdes.String(), orderSerde)
                        .withTimestampExtractor((record, partitionTime) -> {
                            OrderCommon order = (OrderCommon) record.value();
                            return order.getDateTime().toInstant(java.time.ZoneOffset.UTC).toEpochMilli();
                        })
        );

        stream
                // 1. Group by the product name
                .groupBy((key, order) -> order.getProduct())

                // 2. Create a 1-hour window
                .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofHours(1)))

                // 3. Count the records in each window
                .count(Materialized.with(Serdes.String(), Serdes.Long()))

                // 4. Convert back to a regular stream
                .toStream()

                // 5. Format the output to a structured object (DTO).
                //    The key can be the product name or null, as all data is now in the value.
                .map((windowedKey, count) -> {
                    String productName = windowedKey.key();
                    java.time.Instant timestamp = windowedKey.window().startTime();
                    HourlyTransaction value = new HourlyTransaction(productName, timestamp, count);
                    // The new key is the product name.
                    return new KeyValue<>(productName, value);
                })

                // 6. Send to the final topic using the new JSON Serde for the value.
                .to("hourly-transaction-topic", Produced.with(Serdes.String(), hourlyTransactionSerde));

        return stream;
    }
}
