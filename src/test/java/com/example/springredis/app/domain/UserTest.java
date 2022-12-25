package com.example.springredis.app.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Taewoo
 */


class UserTest {

    @Test
    @DisplayName("유저를 생성한다.")
    public void 유저_생성() {
        User user = User.makeUser("test@test.com", "testtest", "testuser");

        assertTrue(Objects.isNull(user.getId()));

        assertEquals(user.getEmail(), "test@test.com");
        assertEquals(user.getPassword(), "testtest");
        assertEquals(user.getUsername(), "testuser");
    }

    @Test
    @DisplayName("비밀번호를 확인한다.")
    public void 비밀번호_확인() {
        User user = User.makeUser("test@test.com", "testtest", "testuser");

        assertTrue(user.passwordCheck("testtest"));
    }

    @Test
    @DisplayName("업데이트를 확인한다.")
    public void 유저_업데이트() {
        User user = User.makeUser("test@test.com", "testtest", "testuser");
        user.update("test2@test.com", "testtest2", "testuser2");

        assertEquals(user.getEmail(), "test2@test.com");
        assertEquals(user.getPassword(), "testtest2");
        assertEquals(user.getUsername(), "testuser2");
    }

    @Test
    @DisplayName("")
    public void 유저_업데이트_실패() {
        User user = User.makeUser("test@test.com", "testtest", "testuser");

        assertThrows(IllegalArgumentException.class,
                () -> user.update(null, null, null));

        assertThrows(IllegalArgumentException.class,
                () -> user.update("", "", ""));
    }
}