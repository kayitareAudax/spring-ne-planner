package com.example.planner.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VehicleRequest {
    @NotNull(message = "vehicle chasis number should not be null")
    @Length(min = 2, max = 30)
    private String chasis;
    @NotNull(message = "vehicle plate number should not be null")
    @Length(min = 7, max = 7, message = "Plate number is of 7 characters")
    private String plateNumber;
    @NotNull(message = "vehicle model Name should not be null")
    private String modelName;
    @NotNull
    private String manufactureCompany;
    @NotNull
    private String manufactureYear;
    @NotNull
    @NumberFormat
    private Integer price;
}
