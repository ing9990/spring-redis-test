package com.example.springredis.app.service.cache;

import com.example.springredis.app.service.check.CheckMailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.stream.Stream;

/**
 * @author Taewoo
 */

@Service
@RequiredArgsConstructor
public class RedisLoginTokenService implements RedisService {

    private final RedisTemplate<String, String> redisTemplate;
    private final CheckMailSender checkMailSender;

    @Value("${email.max_size}")
    private Integer max_size;

    @Value("${email.expired_minute}")
    private Integer expired_minute;


    public void createToken(String email) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(email, "token" + email, Duration.ofMinutes(expired_minute));
    }

    @Override
    public String sendCheckEmail(String email) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        String checker = checkMailSender.sendEmailAndGet(max_size);
        values.set(email, checker, Duration.ofMinutes(expired_minute));
        return checker;
    }

    public String getToken(String email) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(email);
    }


}














