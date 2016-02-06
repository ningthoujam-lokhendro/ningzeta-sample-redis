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
 * @author Ningthoujam Lokhendro
 * @since 6th Feb 2016
 */
@Configuration
@Profile({"custom"})
public class RedisCustomConfiguration {

    @Autowired
    private Environment environment;

    /**
     * Use this to refine the Connection Pool.
     *
     * @return RedisConnectionFactory
     */
    @Bean
    RedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisConnectionFactory.setHostName(environment.getRequiredProperty("redis.host"));
        jedisConnectionFactory.setPassword(environment.getProperty("redis.password"));
        jedisConnectionFactory.setPort(environment.getRequiredProperty("redis.port", Integer.class));
        Boolean usePool = environment.getRequiredProperty("redis.pool.use", Boolean.class)
                .toString().equalsIgnoreCase("true");
        jedisConnectionFactory.setUsePool(usePool);

        if (usePool) {
            jedisPoolConfig.setMaxTotal(environment.getProperty("redis.pool.max-active", Integer.class));
            jedisPoolConfig.setMaxIdle(environment.getProperty("redis.pool.max-idle", Integer.class));
        }

        return jedisConnectionFactory;
    }

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
     * Use of Custom Tempplate for marshalling objects.
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        // Use this when refine Connection Pool
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setDefaultSerializer(stringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer());
        return redisTemplate;
    }

}
