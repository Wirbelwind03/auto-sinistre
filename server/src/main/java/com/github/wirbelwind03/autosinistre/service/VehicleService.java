package com.github.wirbelwind03.autosinistre.service;

import com.github.wirbelwind03.autosinistre.model.dto.request.VehicleAddRequestDTO;
import com.github.wirbelwind03.autosinistre.model.entity.Sinistre;
import com.github.wirbelwind03.autosinistre.model.entity.Vehicle;
import com.github.wirbelwind03.autosinistre.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public void addVehicle(VehicleAddRequestDTO request){
        Vehicle vehicle = Vehicle.builder()
                .brand(request.getBrand())
                .model(request.getModel())
                .year(request.getYear())
                .mileage(request.getMileage())
                .build();

        vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public void deleteVehicle(long id){
        vehicleRepository.deleteById(id);
    }

    public List<Sinistre> getSinistresOfVehicle(long id){
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        //List<Sinistre> sinistres
        return null;
    }
}
