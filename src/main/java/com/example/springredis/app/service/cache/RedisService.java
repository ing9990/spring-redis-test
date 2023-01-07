package com.example.springredis.app.service.cache;

import org.springframework.stereotype.Service;

/**
 * @author Taewoo
 */

@Service
public interface RedisService {

    void createToken(String email);

    String sendCheckEmail(String email);

    String getToken(String email);
}
