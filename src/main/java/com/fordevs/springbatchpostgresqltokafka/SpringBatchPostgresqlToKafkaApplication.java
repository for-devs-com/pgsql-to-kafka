package com.fordevs.springbatchpostgresqltokafka;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({"com.fordevs.springbatchpostgresqltokafka.reader", "com.fordevs.springbatchpostgresqltokafka.config", "com.fordevs.springbatchpostgresqltokafka.entity.postgresql",
         "com.fordevs.springbatchpostgresqltokafka.writer",
        "com.fordevs.springbatchpostgresqltokafka.services"})
public class SpringBatchPostgresqlToKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchPostgresqlToKafkaApplication.class, args);
    }

}
