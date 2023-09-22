package com.fordevs.springbatchpostgresqltokafka.writer;

import com.fordevs.springbatchpostgresqltokafka.entity.postgresql.InputStudent;
import com.fordevs.springbatchpostgresqltokafka.listeners.KafkaEventListener;
import com.fordevs.springbatchpostgresqltokafka.reader.CustomKafkaItemReader;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongoDbItemWriter implements ItemWriter<InputStudent> {


    private MongoTemplate mongoTemplate;

    private KafkaEventListener kafkaEventListener;

    private CustomKafkaItemReader customKafkaItemReader;

    @Override
    public void write(List<? extends InputStudent> students) throws Exception {
        System.out.println("Writing data to MongoDB: " + students);
        for (InputStudent student : students) {
            System.out.println("Student: " + student);
        }
        mongoTemplate.insertAll(students);
    }


    public void writeMongo(List<? extends ConsumerRecord<String, InputStudent>> records) throws Exception {
        System.out.println("Writing data to MongoDB: " + records);
        for (ConsumerRecord<String, InputStudent> record : records) {
            System.out.println("Student: " + records);
        }
        mongoTemplate.insertAll(records);
    }

}
