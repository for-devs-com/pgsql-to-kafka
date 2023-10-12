package com.fordevs.springbatchkafkatomongodb.config;


import com.fordevs.springbatchkafkatomongodb.reader.CustomKafkaItemReader;
import com.fordevs.springbatchkafkatomongodb.writer.MongoDbItemWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de un de Spring Batch Job que útiliza Kafka y JPA.
 *
 * @author Enoc.Velza | for-devs.com
 * @version 1.0
 */
@Configuration
@Slf4j
public class SampleJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private CustomKafkaItemReader customKafkaItemReader;
    @Autowired
    private MongoDbItemWriter mongoDBItemWriter;

    /**
     * Configura el Job de Spring Batch.
     *
     * @return Job de Spring Batch.
     */
    @Bean
    public Job chunkJob() {
        try {
            return jobBuilderFactory.get("First Chunk Job")
                    .incrementer(new RunIdIncrementer())
                    .start(kafkaToMongoStep())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public Step kafkaToMongoStep() {
        return stepBuilderFactory.get("kafkaToMongoStep")
                .<String, String>chunk(1500) // Tamaño del chunk
                .reader(customKafkaItemReader)
                .writer(mongoDBItemWriter)
                .build();
    }

}
