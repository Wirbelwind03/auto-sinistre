package com.github.wirbelwind03.autosinistre.repositories;

import com.github.wirbelwind03.autosinistre.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByYear(int year);
}
