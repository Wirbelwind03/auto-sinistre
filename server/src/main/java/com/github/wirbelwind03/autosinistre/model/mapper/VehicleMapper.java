package com.github.wirbelwind03.autosinistre.model.mapper;

import com.github.wirbelwind03.autosinistre.model.dto.response.GetVehicleResponseDTO;
import com.github.wirbelwind03.autosinistre.model.entity.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleMapper {
    private final UserMapper userMapper;

    public GetVehicleResponseDTO toDTO(Vehicle vehicle){
        return GetVehicleResponseDTO.builder()
                .owner(userMapper.toDTO(vehicle.getOwner()))
                .brand(vehicle.getBrand().getName())
                .model(vehicle.getModel())
                .year(vehicle.getYear())
                .mileage(vehicle.getMileage())
                .licensePlate(vehicle.getLicensePlate())
                .fuelType(vehicle.getFuelType().getLabel())
                .vin(vehicle.getVin())
                .build();
    }
}
