package com.example.kafka_streams.config;

import com.example.kafka_streams.model.HourlyTransaction;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    // Explicitly defines a ConsumerFactory for your HourlyTransaction consumer
    @Bean
    public ConsumerFactory<String, HourlyTransaction> hourlyTransactionConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, groupId);

        // Configure the JsonDeserializer for the HourlyTransaction DTO
        JsonDeserializer<HourlyTransaction> jsonDeserializer = new JsonDeserializer<>(HourlyTransaction.class);
        jsonDeserializer.setRemoveTypeHeaders(false);
        jsonDeserializer.addTrustedPackages("com.example.kafka_streams.model"); // Trust the DTO package
        jsonDeserializer.setUseTypeMapperForKey(true);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
    }

    // Creates a specific listener factory using the ConsumerFactory defined above
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, HourlyTransaction> hourlyTransactionListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, HourlyTransaction> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(hourlyTransactionConsumerFactory());
        return factory;
    }
}