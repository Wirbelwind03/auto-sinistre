package com.github.wirbelwind03.autosinistre.repository;

import com.github.wirbelwind03.autosinistre.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByBrand(String brand);
    List<Vehicle> findByYear(int year);
    List<Vehicle> findByOwnerId(Long id);
}
