package com.example.springredis.api;

import com.example.springredis.api.dto.SignInParam;
import com.example.springredis.api.dto.SignUpParam;
import com.example.springredis.app.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author Taewoo
 */

@RestController
@RequiredArgsConstructor
public class UsersApi {

    /**
     * 추상화를 하기 위해 인터페이스에 의존하도록 설계합니다.
     * Controller는 Service가 어떤 로직으로 실행되는지 몰라야합니다.
     */
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<UserResponse> singUp(@RequestBody SignUpParam loginParam) {
        var result = userService.signUp(loginParam);

        if (result) {
            return ResponseEntity.ok()
                    .body(UserResponse.success(loginParam.getEmail()));
        }

        return ResponseEntity.badRequest()
                .body(UserResponse.fail(loginParam.getEmail()));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserResponse> signIn(@RequestBody SignInParam signInParam) {
        var isSuccess = userService.signIn(signInParam);

        return isSuccess ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(UserResponse.success(signInParam.getEmail())) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(UserResponse.fail(signInParam.getEmail()));
    }

    @PatchMapping
    public ResponseEntity<UserResponse> update(@RequestBody SignUpParam signUpParam) {
        var email = userService.updateUser(signUpParam);

        return ResponseEntity.ok().body(UserResponse.success(email));
    }

    @Getter
    @AllArgsConstructor
    static class UserResponse {
        private String email;

        private Boolean isLogin = false;

        private LocalDateTime timeStamp;

        /**
         * UserResponse의 인스턴스는 success나 fail로만 만들어지도록 기본 생성자를 private으로 막습니다.
         */
        private UserResponse() {
        }

        public static UserResponse success(String email) {
            return new UserResponse(email, true, LocalDateTime.now());
        }

        public static UserResponse fail(String email) {
            return new UserResponse(email, false, LocalDateTime.now());
        }
    }
}






