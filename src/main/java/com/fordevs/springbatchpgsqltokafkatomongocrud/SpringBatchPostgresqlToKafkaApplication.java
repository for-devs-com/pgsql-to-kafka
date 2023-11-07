package com.fordevs.springbatchpgsqltokafkatomongocrud;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({
        "com.fordevs.springbatchpgsqltokafkatomongocrud.config",
        "com.fordevs.springbatchpgsqltokafkatomongocrud.entity.postgresql",
        "com.fordevs.springbatchpgsqltokafkatomongocrud.reader",
        "com.fordevs.springbatchpgsqltokafkatomongocrud.services",
        "com.fordevs.springbatchpgsqltokafkatomongocrud.writer",
        "com.fordevs.springbatchpgsqltokafkatomongocrud.listeners",
})
public class SpringBatchPostgresqlToKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchPostgresqlToKafkaApplication.class, args);
    }

}
