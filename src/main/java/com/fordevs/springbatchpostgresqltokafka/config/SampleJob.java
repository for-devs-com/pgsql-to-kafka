package com.fordevs.springbatchpostgresqltokafka.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fordevs.springbatchpostgresqltokafka.entity.postgresql.InputStudent;
import com.fordevs.springbatchpostgresqltokafka.reader.CustomKafkaItemReader;
import com.fordevs.springbatchpostgresqltokafka.services.ProducerService;
import com.fordevs.springbatchpostgresqltokafka.writer.MongoDbItemWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;

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
    @Qualifier("postgresdatasource")
    private DataSource postgresdatasource;

    @Autowired
    @Qualifier("postgresqlEntityManagerFactory")
    private EntityManagerFactory postgresqlEntityManagerFactory;

    @Autowired
    private KafkaProperties properties;
    @Autowired
    private CustomKafkaItemReader customKafkaItemReader;
    @Autowired
    private MongoDbItemWriter mongoDBItemWriter;

    /**
     * Configura el lector de elementos de Kafka.
     *
     * @return ItemReader para Kafka.
     */
    /*@Bean
    ItemReader<? extends String> kafkaItemReader() {
        Properties props = new Properties();
        props.putAll(this.properties.buildConsumerProperties());
        CustomKafkaItemReader<Long, InputStudent> build = new KafkaItemReaderBuilder<Long, InputStudent>()
                .partitions(0)
                .consumerProperties(props)
                .name("student_reader")
                .saveState(true)
                .topic("student_topic")
                .build();
        return build;
    }*/

    /**
     * Configura el escritor de elementos de Kafka.
     *
     * @return ItemWriter para Kafka.
     */
    @Bean
    public ItemWriter<InputStudent> kafkaItemWriter() {
        return new ItemWriter<InputStudent>() {
            @Autowired
            private ProducerService producerService;

            private final ObjectMapper objectMapper = new ObjectMapper();

            @Override
            public void write(List<? extends InputStudent> items) throws Exception {
                for (InputStudent item : items) {
                    String valueAsString = objectMapper.writeValueAsString(item);
                    producerService.sendMessage("student_topic", valueAsString);
                }
            }
        };
    }

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
                    .next(kafkaToMongoStep())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public Step kafkaToMongoStep() {
        return stepBuilderFactory.get("kafkaToMongoStep")
                .<InputStudent, InputStudent>chunk(1500) // Tamaño del chunk
                .reader(customKafkaItemReader)
                //.processor(dataTransformProcessor)
                .writer(mongoDBItemWriter)
                .build();
    }
    /**
     * Configura el Step de Spring Batch.
     *
     * @return Step de Spring Batch.
     */
    private Step chunkStep() {
        return stepBuilderFactory.get("First Chunk Step")
                .<InputStudent, InputStudent>chunk(1000)
                .reader(jpaCursorPgsqlItemReader())
                .writer(kafkaItemWriter())
                .build();
    }
    /**
     * Configura el lector de elementos de JPA para PostgreSQL.
     *
     * @return jpaCursorItemReader para JPA configurado.
     */
    public JpaCursorItemReader<InputStudent> jpaCursorPgsqlItemReader() {
        log.info("Initializing JPA Cursor Item Reader");
        JpaCursorItemReader<InputStudent> jpaCursorItemReader =
                new JpaCursorItemReader<>();

        jpaCursorItemReader.setEntityManagerFactory(postgresqlEntityManagerFactory);
        jpaCursorItemReader.setQueryString("From InputStudent");

        return jpaCursorItemReader;
    }

}
