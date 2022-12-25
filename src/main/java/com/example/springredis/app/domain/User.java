package com.example.springredis.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;

/**
 * @author Taewoo
 */

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "USERNAME")
    private String username;


    public static User makeUser(String email, String password, String username) {
        return new User(null, email, password, username);
    }

    public boolean passwordCheck(String password) {
        return this.password.equals(password);
    }

    /**
     * Update를 호출하는 엔티티는 영속성 컨텍스트에 있어 변경감지가 됩니다.
     * 트랜잭션이 종료되면 데이터베이스에 Merge 되기 때문에 save를 할 필요가 없습니다.
     */
    public void update(String email, String password, String username) {
        if (!StringUtils.hasText(email)) {
            throw new IllegalArgumentException("Email is empty.");
        }

        if (!StringUtils.hasText(password)) {
            throw new IllegalArgumentException("Password is empty.");
        }

        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("Username is empty.");
        }

        this.email = email;
        this.password = password;
        this.username = username;
    }
}
