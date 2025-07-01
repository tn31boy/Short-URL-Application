package com.URLSHortner.ShortURL.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    @Qualifier("redisStringConfig")
    RedisTemplate<String,String> redisTemplate;

    public  int save(String key, String value, long duration, TimeUnit unit)
    {
        redisTemplate.opsForValue().set(key,value,duration,unit);
        return 1;
    }

    public String get(String key)
    {
        return redisTemplate.opsForValue().get(key);
    }

    public void updateUrl(String shortUrl,String url)
    {
        redisTemplate.opsForValue().set(shortUrl,url);
    }

    public void delete(String key)
    {
        redisTemplate.delete(key);
    }


}
