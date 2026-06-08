package com.github.wirbelwind03.autosinistre.model.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VehicleAddRequestDTO {
    @NotBlank(message = "La marque est obligatoire")
    private String brand;

    @NotBlank(message = "Le modèle est obligatoire")
    private String model;

    @NotBlank(message = "L'année est obligatoire")
    private int year;

    @NotBlank(message = "Le kilométrage est obligatoire")
    private long mileage;
}
