package com.github.wirbelwind03.autosinistre.service;

import com.github.wirbelwind03.autosinistre.model.dto.request.VehicleAddRequestDTO;
import com.github.wirbelwind03.autosinistre.model.entity.Brand;
import com.github.wirbelwind03.autosinistre.model.entity.Sinistre;
import com.github.wirbelwind03.autosinistre.model.entity.User;
import com.github.wirbelwind03.autosinistre.model.entity.Vehicle;
import com.github.wirbelwind03.autosinistre.repository.BrandRepository;
import com.github.wirbelwind03.autosinistre.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final BrandRepository brandRepository;
    private final VehicleRepository vehicleRepository;

    public void addVehicle(VehicleAddRequestDTO request, User owner){
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new RuntimeException("Marque introuvable"));

        Vehicle vehicle = Vehicle.builder()
                .brand(brand)
                .owner(owner)
                .model(request.getModel())
                .year(request.getYear())
                .mileage(request.getMileage())
                .licensePlate(request.getLicensePlate())
                .build();

        vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getVehiculesByUser(Long userId){
        return vehicleRepository.findByOwnerId(userId);
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
