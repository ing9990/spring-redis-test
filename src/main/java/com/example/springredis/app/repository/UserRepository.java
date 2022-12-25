package com.example.springredis.app.repository;

import com.example.springredis.api.dto.SignInParam;
import com.example.springredis.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Taewoo
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select (count(u) > 0) from User u where u.email = ?1")
    boolean existsByEmail(String email);

    @Query("select u from User u where u.email = ?1")
    Optional<User> findByEmail(String email);
}
