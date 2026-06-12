package com.github.wirbelwind03.autosinistre.controller;

import com.github.wirbelwind03.autosinistre.model.dto.request.FuelTypeRequestDTO;
import com.github.wirbelwind03.autosinistre.model.dto.request.VehicleAddRequestDTO;
import com.github.wirbelwind03.autosinistre.model.dto.response.GetVehicleResponseDTO;
import com.github.wirbelwind03.autosinistre.model.entity.User;
import com.github.wirbelwind03.autosinistre.model.entity.Vehicle;
import com.github.wirbelwind03.autosinistre.model.enums.FuelTypeEnum;
import com.github.wirbelwind03.autosinistre.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping("/add")
    public ResponseEntity<Void> addVehicle(@Valid @RequestBody VehicleAddRequestDTO request, Authentication authentication){
        User owner = (User) authentication.getPrincipal();
        vehicleService.addVehicle(request, owner);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/my-vehicles")
    public ResponseEntity<List<GetVehicleResponseDTO>> getMyVehicles(Authentication authentication){
        User owner = (User)authentication.getPrincipal();
        return ResponseEntity.ok(vehicleService.getVehiculesByUser(owner.getId()));
    }

    @GetMapping("/fuel-types")
    public ResponseEntity<List<FuelTypeRequestDTO>> getFuelTypes(){
        List<FuelTypeRequestDTO> fuelTypes = Arrays.stream(FuelTypeEnum.values())
                .map(f -> new FuelTypeRequestDTO(f.name(), f.getLabel()))
                .toList();
        return ResponseEntity.ok(fuelTypes);
    }
}
