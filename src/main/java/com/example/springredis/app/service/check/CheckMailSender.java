package com.example.springredis.app.service.check;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CheckMailSender {

    @Async
    public String sendEmailAndGet(int maxSize) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < maxSize; i++) {
            sb.append(i);
        }

        return sb.toString();
    }
}
