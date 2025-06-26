package com.URLSHortner.ShortURL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    @Qualifier("redisStringConfig")
    RedisTemplate<String,String> redisTemplate;

    public  int save(String key,String value)
    {
        redisTemplate.opsForValue().set(key,value);
        return 1;
    }

}
