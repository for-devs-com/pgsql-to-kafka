package com.fordevs.springbatchpostgresqltokafka.writer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MongoDbItemWriter implements ItemWriter<String> {

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public void write(List<? extends String> messages) throws Exception {
        String collectionName = "students";
        log.info("Writing data to MongoDB: {}", messages);
        for (String message : messages) {
            mongoTemplate.insert(message, collectionName);
        }
    }
}
