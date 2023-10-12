package com.fordevs.springbatchkafkatomongodb;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({
        "com.fordevs.springbatchkafkatomongodb.config",
        "com.fordevs.springbatchkafkatomongodb.reader",
        "com.fordevs.springbatchkafkatomongodb.writer"
})
public class SpringBatchKafkaToMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchKafkaToMongoApplication.class, args);
    }

}
