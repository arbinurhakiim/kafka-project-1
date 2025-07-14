package com.example.kafka_streams.stream;

import com.example.order_common.dto.OrderCommon;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class HourlyTransactionStream {

    @Bean(name = "TransactionPerHourAgregation")
    public KStream<String, OrderCommon> kStream(StreamsBuilder streamsBuilder, Serde<OrderCommon> orderSerde) {
        System.out.println("start stream for hourly transaction count");

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

                // 5. Format the output key and value
                .map((windowedKey, count) -> new KeyValue<>(
                        windowedKey.key() + "@" + windowedKey.window().startTime().toString(),
                        count
                ))

                // 6. Send to the final topic
                .to("hourly-transaction-topic", Produced.with(Serdes.String(), Serdes.Long()));

        return stream;
    }
}
