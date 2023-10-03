package com.fordevs.springbatchpostgresqltokafka.config;

import com.fordevs.springbatchpostgresqltokafka.entity.postgresql.InputStudent;
import com.fordevs.springbatchpostgresqltokafka.reader.JpaPgsqlItemReader;
import com.fordevs.springbatchpostgresqltokafka.writer.CustomKafkaItemWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Configuración de un de Spring Batch Job que útiliza Kafka y JPA.
 *
 * @author Enoc.Velza | for-devs.com
 * @version 1.0
 */
@Configuration
@EnableKafka
@Slf4j
public class SampleJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ItemReader<InputStudent> jpaCursorPgsqlItemReader;

    @Autowired
    private CustomKafkaItemWriter customKafkaItemWriter;

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
                    .start(chunkStep())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Configura el Step de Spring Batch.
     *
     * @return Step de Spring Batch.
     */
    private Step chunkStep() {
        return stepBuilderFactory.get("First Chunk Step")
                .<InputStudent, InputStudent>chunk(1000)
                .reader(jpaCursorPgsqlItemReader)
                .writer(customKafkaItemWriter)
                .build();
    }


}
