package com.example.springredis.api.dto;

import lombok.Getter;

/**
 * @author Taewoo
 */

@Getter
public class SignUpParam {
    private String email;
    private String password;
    private String username;
}
