# NingZeta-Sample-redis
General usage example of Redis with Spring.

* Use of Spring-boot auto-configuration for Redis properties
* Use of customize configuration of Redis.
* Generic RedisTemplate usage.

## Technology use
* Spring-Boot
* Spring Redis - Jedis

Refer to `pom.xml` for details.

## Requirement

* Maven 3.x
* Java 1.7+

## Use of Spring-boot auto-configuration for Redis properties
Inject RedisConnectionFactory
```java
@Autowired
private RedisConnectionFactory redisConnectionFactory;
```
## Use of Customize configuration of Redis.
Create a bean for the JedisConnectionFactory
```java
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
```
## Generic RedisTemplate Serializer-DeSerializer usage.
Create a bean for the RedisTemplate that accepts Jackson2JsonRedisSerializer and/or StringRedisSerializer
according to the data-store format.
```java
@Bean
public RedisTemplate<?, ?> redisTemplate() {
    RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
    // Use this when refine Connection Pool
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    redisTemplate.setDefaultSerializer(stringRedisSerializer());
    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer());
    return redisTemplate;
}
```

## Usage
There are two profiles : dev || custom
Activate the profile in the application.yml

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D
