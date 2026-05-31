package com.github.wirbelwind03.autosinistre.services;

import com.github.wirbelwind03.autosinistre.models.Vehicle;
import com.github.wirbelwind03.autosinistre.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private  final VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles(){
        return  vehicleRepository.findAll();
    }
}
