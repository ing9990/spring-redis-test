package com.example.springredis.app.service.user;

import com.example.springredis.api.dto.SignInParam;
import com.example.springredis.api.dto.SignUpParam;
import org.springframework.stereotype.Service;

/**
 * @author Taewoo
 */

@Service
public interface UserService {
    Boolean signUp(SignUpParam loginParam);

    Boolean signIn(SignInParam signInParam);

    String updateUser(SignUpParam signUpParam);

    String checkMailSend(String email);
}
