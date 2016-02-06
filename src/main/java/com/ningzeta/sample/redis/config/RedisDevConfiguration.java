package com.ningzeta.sample.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Ningthoujam Lokhendro on 2/5/2016.
 */
@Configuration
@Profile({"dev"})
public class RedisDevConfiguration {

    /**
     * Using the default parameters from the spring-boot
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
                new Jackson2JsonRedisSerializer<Object>(Object.class);
        return jackson2JsonRedisSerializer;
    }

    @Bean
    StringRedisSerializer stringRedisSerializer() {
        return new StringRedisSerializer();
    }

    /**
     * Use of Generic Tempplate for marshalling objects.
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // Use this when refine Connection Pool
        //redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setDefaultSerializer(stringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
