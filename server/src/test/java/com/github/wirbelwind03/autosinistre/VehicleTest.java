package com.github.wirbelwind03.autosinistre;

import com.github.wirbelwind03.autosinistre.models.Vehicle;
import com.github.wirbelwind03.autosinistre.repositories.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class VehicleTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    void testCreateVehicle(){
        Vehicle vehicle = new Vehicle();
        vehicle.setYear(2005);

        Vehicle saved = vehicleRepository.save(vehicle);

        System.out.println("Véhicule créé : " + saved);
    }

    @Test
    void testGetAllVehicles(){
        List<Vehicle> vehicles = vehicleRepository.findAll();
        System.out.println("Véhicules en base : " + vehicles.size());
    }
}