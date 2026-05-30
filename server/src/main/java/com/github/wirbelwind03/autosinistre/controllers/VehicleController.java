package com.github.wirbelwind03.autosinistre.controllers;

import com.github.wirbelwind03.autosinistre.models.Vehicle;
import com.github.wirbelwind03.autosinistre.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles(){
        try{
            List<Vehicle> vehicles = new ArrayList<>();

            vehicles = vehicleRepository.findAll();

            return new ResponseEntity<>(vehicles, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
