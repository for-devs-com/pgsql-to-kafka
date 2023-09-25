package com.fordevs.springbatchpostgresqltokafka.config;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fordevs.springbatchpostgresqltokafka.entity.postgresql.InputStudent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuración de Kafka para la producción de mensajes.
 *
 * @author Enoc.Velza | for-devs.com
 * @version 1.0
 */
@Configuration
@EnableKafka
public class KafkaConfig {

    /**
     * Dirección de los servidores de Kafka.
     */
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Configura la fábrica del productor de Kafka.
     *
     * @return Una fábrica de productores de Kafka configurada.
     */
    @Bean
    public ProducerFactory<String, Object> producerConfigs() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // ... otras configuraciones

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public ConsumerFactory<String, InputStudent> consumerConfigs() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "students_group_id");
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // ... otras configuraciones

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    /**
     * Configura la plantilla de Kafka para la producción de mensajes.
     *
     * @return Una plantilla de Kafka configurada.
     */
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerConfigs());
    }
}
