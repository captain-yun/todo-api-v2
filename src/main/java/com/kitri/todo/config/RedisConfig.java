package com.kitri.todo.config;

import com.kitri.todo.dto.response.ResponseTodo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory("redis", 6379);
    }
    @Bean
    public RedisTemplate<String, ResponseTodo> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, ResponseTodo> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // Set other serializers if needed
        return redisTemplate;
    }
}
