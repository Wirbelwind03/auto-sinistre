package com.github.wirbelwind03.autosinistre.repositories;

import com.github.wirbelwind03.autosinistre.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  UserRepository  extends JpaRepository<User, Long> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
