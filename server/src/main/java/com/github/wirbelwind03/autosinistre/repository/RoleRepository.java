package com.github.wirbelwind03.autosinistre.repository;

import com.github.wirbelwind03.autosinistre.model.entity.Role;
import com.github.wirbelwind03.autosinistre.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
