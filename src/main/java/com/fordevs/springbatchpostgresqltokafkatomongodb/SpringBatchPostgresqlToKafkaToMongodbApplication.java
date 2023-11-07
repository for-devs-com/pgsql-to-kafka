package com.fordevs.springbatchpostgresqltokafkatomongodb;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({
        "com.fordevs.springbatchpostgresqltokafkatomongodb.config",
        "com.fordevs.springbatchpostgresqltokafkatomongodb.entity.postgresql",
        "com.fordevs.springbatchpostgresqltokafkatomongodb.reader",
        "com.fordevs.springbatchpostgresqltokafkatomongodb.services",
        "com.fordevs.springbatchpostgresqltokafkatomongodb.writer",
})
public class SpringBatchPostgresqlToKafkaToMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchPostgresqlToKafkaToMongodbApplication.class, args);
    }

}
