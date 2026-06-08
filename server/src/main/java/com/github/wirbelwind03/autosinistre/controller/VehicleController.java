package com.github.wirbelwind03.autosinistre.controller;

import com.github.wirbelwind03.autosinistre.model.dto.request.VehicleAddRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    @PostMapping
    public void addVehicle(@Valid @RequestBody VehicleAddRequestDTO request){

    }
}
