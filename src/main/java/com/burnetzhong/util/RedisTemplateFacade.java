package com.burnetzhong.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisTemplateFacade implements InitializingBean {

    @Autowired
    private RedisTemplate redisTemplate;

    private RedisSerializer stringSerializer = new StringRedisSerializer();

    private RedisTemplateFacade(){}


    public RedisTemplate getRedisTemplate(){
        return redisTemplate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
    }
}