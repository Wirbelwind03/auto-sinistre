package com.github.wirbelwind03.autosinistre.service;

import com.github.wirbelwind03.autosinistre.exception.AlreadyExistException;
import com.github.wirbelwind03.autosinistre.exception.NotFoundException;
import com.github.wirbelwind03.autosinistre.model.dto.request.VehicleAddRequestDTO;
import com.github.wirbelwind03.autosinistre.model.dto.response.GetVehicleResponseDTO;
import com.github.wirbelwind03.autosinistre.model.entity.Brand;
import com.github.wirbelwind03.autosinistre.model.entity.Sinistre;
import com.github.wirbelwind03.autosinistre.model.entity.User;
import com.github.wirbelwind03.autosinistre.model.entity.Vehicle;
import com.github.wirbelwind03.autosinistre.model.mapper.VehicleMapper;
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
    private final VehicleMapper vehicleMapper;

    /**
     * Ajoute un véhicule possédée par le client dans le base de données
     * @param request La rêquete qui contient les valeurs nécessaire pour ajouter le véhicule dans la base de données
     * @param owner Le propriétaire du véhicule
     */
    public void addVehicle(VehicleAddRequestDTO request, User owner){
        // Regarde si la plaque d'immatriculation existe déja dans la base de donnée
        if (vehicleRepository.existsByLicensePlate(request.getLicensePlate())){
            throw new AlreadyExistException("Cette plaque d'immatriculation est déjà pris");
        }

        // Cherche la marque de la voiture
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new NotFoundException("Marque introuvable"));

        Vehicle vehicle = Vehicle.builder()
                .brand(brand)
                .owner(owner)
                .model(request.getModel())
                .year(request.getYear())
                .mileage(request.getMileage())
                .licensePlate(request.getLicensePlate())
                .fuelType(request.getFuelType())
                .vin(request.getVin())
                .build();

        vehicleRepository.save(vehicle);
    }

    public List<GetVehicleResponseDTO> getVehiculesByUser(Long userId){
        return  vehicleRepository.findByOwnerId(userId)
                .stream()
                .map(vehicleMapper::toDTO)
                .toList();
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
