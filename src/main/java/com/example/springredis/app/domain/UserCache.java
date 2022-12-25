package com.example.springredis.app.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Taewoo
 */

@RedisHash("user")
@Getter
@ToString
public class UserCache {

    @Id
    private Long id;

    private String email;

    private String token;
}
