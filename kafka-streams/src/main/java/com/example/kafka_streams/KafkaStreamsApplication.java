package com.example.kafka_streams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class KafkaStreamsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamsApplication.class, args);
	}

}
