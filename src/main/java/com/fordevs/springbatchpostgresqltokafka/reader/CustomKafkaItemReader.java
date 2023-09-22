package com.fordevs.springbatchpostgresqltokafka.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fordevs.springbatchpostgresqltokafka.config.KafkaConfig;
import com.fordevs.springbatchpostgresqltokafka.entity.postgresql.InputStudent;
import com.fordevs.springbatchpostgresqltokafka.listeners.KafkaEventListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.kafka.KafkaItemReader;
import org.springframework.batch.item.kafka.builder.KafkaItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Slf4j
@Service
public class CustomKafkaItemReader implements ItemReader<InputStudent> {
    @Autowired
    private KafkaEventListener kafkaEventListener;

    @Autowired
    private KafkaConfig properties;

    /*@KafkaListener(topics = "student_topic", groupId = "students_group_id")
    public InputStudent readMsg(InputStudent record) {

        ObjectMapper objectMapper = new ObjectMapper();
        InputStudent student = objectMapper.readValue(String.valueOf(record))   ;

        log.info("Received student: {}", record);

        return null;
    }*/

    /*@Override
    public InputStudent read() throws Exception {
        ConsumerRecord<String, InputStudent> student = kafkaEventListener.getStudent();
        if (student != null){
            log.info("Student: {}", student);
        } else {
            log.info("No records found in student_topic.");
        }
        return student.value();
    }*/

    @Override
    public InputStudent read() throws Exception {
        return null;
    }

    @KafkaListener(topics = "student_topic", groupId = "students_group_id")
    public ConsumerRecord<String, InputStudent> readMsg(ConsumerRecord<String, InputStudent> record) {
        //ObjectMapper objectMapper = new ObjectMapper();
        //InputStudent consumedMsg = objectMapper.readValue(record.value());


        log.info("Received student: {}", record.value());

        return record;
    }

    /*public KafkaItemReader<String, InputStudent> kafkaItemReader() {
        Properties props = new Properties();
        props.putAll(this.properties.consumerConfigs().getConfigurationProperties());


        return new KafkaItemReaderBuilder<String, InputStudent>()
                .consumerProperties(props)
                .name("student_topic")
                .saveState(true)
                .build();
    }*/
}

