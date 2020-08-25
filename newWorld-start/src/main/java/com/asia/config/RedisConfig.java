package com.asia.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Xavier.liu
 * @date 2020/6/3 14:59
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        /**
            key value 不指定序列化器 则redis 客户端展示的是乱码
         1. 默认是 JdkSerializationRedisSerializer --- java对象的序列化/反序列化
         2. GenericJackSon2JsonRedisSerializer
            -- {"@class":"com.asia.data.UserXavier","id":33,"name":"xavierredisKey","age":12,"sex":1}
            --用generic则带有@class
         3. new Jackson2JsonRedisSerializer(Object.class)    --可以去除@class
            --- ObjectMapper
         */
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        /**
         * 初始化redisTemplate
         * don't use redisTemplate.afterPropertiesSet();  also work！
         */
        return redisTemplate;
    }

    @Bean
    public RedissonClient redissonClient(RedisProperties redisProperties){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + redisProperties.getHost() + ":" + redisProperties.getPort())
                .setPassword(redisProperties.getPassword())
                .setDatabase(redisProperties.getDatabase());
        return Redisson.create(config);
    }
}
