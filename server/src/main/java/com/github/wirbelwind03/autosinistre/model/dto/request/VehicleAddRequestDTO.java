package com.github.wirbelwind03.autosinistre.model.dto.request;

import com.github.wirbelwind03.autosinistre.model.entity.Brand;
import com.github.wirbelwind03.autosinistre.model.enums.FuelTypeEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VehicleAddRequestDTO {
    @NotNull(message = "La marque est obligatoire")
    private Long brandId;

    @NotBlank(message = "Le modèle est obligatoire")
    private String model;

    @NotNull(message = "L'année est obligatoire")
    private Integer year;

    @NotNull(message = "Le kilométrage est obligatoire")
    private Long mileage;

    @NotBlank(message = "La plaque d'immatriculation est obligatoire")
    private String licensePlate;

    @NotNull(message = "Le type de carburant est obligatoire")
    private FuelTypeEnum fuelType;

    @NotBlank(message = "Le N° VIN est obligatoire")
    private String vin;
}
