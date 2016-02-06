package com.ningzeta.sample.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author Ningthoujam Lokhendro
 * @since 5th Feb 2016
 * <p>
 * Jackson configuration to indent.
 */
@Configuration
public class JacksonConfiguration {

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void setUp() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
}
