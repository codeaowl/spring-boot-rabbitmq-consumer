package com.microservice.consumermicroservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import com.microservice.consumermicroservice.entity.Product;

@Configuration
public class AppConfig {

    //Creating Connection with Redis
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("redis", 6379);
        config.setPassword(RedisPassword.of("redis"));
        return new LettuceConnectionFactory(config);
    }

    //Creating RedisTemplate for Entity 'Product'
    @Bean
    public RedisTemplate<String, Product> redisTemplate(){
        RedisTemplate<String, Product> empTemplate = new RedisTemplate<>();
        empTemplate.setConnectionFactory(redisConnectionFactory());
        return empTemplate;
    }
}