package com.fordevs.springbatchpostgresqltokafka.reader;

import com.fordevs.springbatchpostgresqltokafka.entity.postgresql.InputStudent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Slf4j
@Configuration
public class JpaPgsqlItemReader {
    private final EntityManagerFactory pgsqlEntityManagerFactory;

    @Autowired
    public JpaPgsqlItemReader(@Qualifier("pgsqlEntityManagerFactory") EntityManagerFactory pgsqlEntityManagerFactory) {
        this.pgsqlEntityManagerFactory = pgsqlEntityManagerFactory;

    }
    /**
     * Configura el lector de elementos de JPA para PostgreSQL.
     *
     * @return jpaCursorItemReader para JPA configurado.
     */

    @Bean
    public JpaCursorItemReader<InputStudent> createJpaCursorPgsqlItemReader() {
        log.info("Initializing JPA Cursor Item Reader");
        JpaCursorItemReader<InputStudent> jpaCursorItemReader =
                new JpaCursorItemReader<InputStudent>();

        jpaCursorItemReader.setEntityManagerFactory(pgsqlEntityManagerFactory);
        jpaCursorItemReader.setQueryString("From InputStudent");

        return jpaCursorItemReader;
    }
}
