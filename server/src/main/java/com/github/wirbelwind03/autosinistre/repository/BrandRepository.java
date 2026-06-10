package com.github.wirbelwind03.autosinistre.repository;

import com.github.wirbelwind03.autosinistre.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findById(Long id);
    List<Brand> findAll();
}
