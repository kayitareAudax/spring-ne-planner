package com.example.planner.dto;

import com.example.planner.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleResponse {
    private String message;
    private List<Vehicle> data;
    private boolean success;
}
