package com.example.springredis.app.service.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * @author Taewoo
 */

@Service
@RequiredArgsConstructor
public class RedisLoginTokenService implements RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public void createToken(String email) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(email, "token" + email, Duration.ofMinutes(5));
    }

    public String getToken(String email) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(email);
    }

}
