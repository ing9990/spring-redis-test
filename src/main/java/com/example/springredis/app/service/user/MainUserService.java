package com.example.springredis.app.service.user;

import com.example.springredis.api.dto.SignInParam;
import com.example.springredis.api.dto.SignUpParam;
import com.example.springredis.app.domain.User;
import com.example.springredis.app.repository.UserRepository;
import com.example.springredis.app.service.cache.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Taewoo
 */


@Service
@Transactional
@RequiredArgsConstructor
public class MainUserService implements UserService {

    private final UserRepository userRepository;
    private final RedisService redisService;

    public Boolean signUp(SignUpParam loginParam) {
        if (!emailCheck(loginParam.getEmail())) {
            return false;
        }

        var user = User.makeUser(loginParam.getEmail(), loginParam.getPassword(), loginParam.getUsername());
        userRepository.save(user);

        return true;
    }

    public Boolean signIn(SignInParam signInParam) {
        var user = getUserByEmail(signInParam.getEmail());

        return user.passwordCheck(signInParam.getPassword());
    }


    public String updateUser(SignUpParam signUpParam) {

        System.out.println(signUpParam.getEmail());
        System.out.println(signUpParam.getUsername());
        System.out.println(signUpParam.getPassword());

        System.out.println("hello");
        var user = getUserByEmail(signUpParam.getEmail());

        user.update(signUpParam.getEmail(), signUpParam.getPassword(), signUpParam.getUsername());

        redisService.createToken(signUpParam.getEmail());

        return user.getEmail();
    }

    /**
     * 객체 내부에서만 사용하는 함수는 외부에서 호출하지 못하도록 접근 제한자를 private으로 둡니다.
     */
    private boolean emailCheck(String email) {
        return !userRepository.existsByEmail(email);
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("이메일을 찾을 수 없습니다."));
    }
}









