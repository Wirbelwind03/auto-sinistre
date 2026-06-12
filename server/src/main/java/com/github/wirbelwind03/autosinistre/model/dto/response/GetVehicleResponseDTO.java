package com.github.wirbelwind03.autosinistre.model.dto.response;

import com.github.wirbelwind03.autosinistre.model.entity.Brand;
import com.github.wirbelwind03.autosinistre.model.entity.User;
import com.github.wirbelwind03.autosinistre.model.enums.FuelTypeEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetVehicleResponseDTO {
    private GetUserResponseDTO owner;
    private String brand;
    private String model;
    private Integer year;
    private Long mileage;
    private String licensePlate;
    private String fuelType;
    private String vin;
}
