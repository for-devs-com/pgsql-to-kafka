package com.fordevs.springbatchpostgresqltokafka.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fordevs.springbatchpostgresqltokafka.services.ProducerService;
import com.fordevs.springbatchpostgresqltokafka.entity.postgresql.InputStudent;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomKafkaItemWriter implements ItemWriter<InputStudent> {

    @Autowired
    private ProducerService producerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Configura el escritor de elementos de Kafka.
     *
     * @return ItemWriter para Kafka.
     */

    @Override
    public void write(List<? extends InputStudent> items) throws Exception {
        for (InputStudent item : items) {
            String valueAsString = objectMapper.writeValueAsString(item);
            producerService.sendMessage("student_topic", valueAsString);
        }
    }
}