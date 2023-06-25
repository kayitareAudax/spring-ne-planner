package com.example.planner.service;

import com.example.planner.dto.VehicleRequest;
import com.example.planner.dto.VehicleResponse;
import com.example.planner.model.Vehicle;
import com.example.planner.repository.VehicleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleService {
    private final VehicleRepo vehicleRepo;

    public VehicleResponse getVehicles() {
        List<Vehicle> vehicles = vehicleRepo.findAll();
        if (vehicles.isEmpty()) {
            return VehicleResponse.builder().message("no vehicle found").success(false).data(null).build();
        }
        return VehicleResponse.builder().data(vehicles).message("vehicles found").success(true).build();
    }

    public VehicleResponse createVehicle(VehicleRequest request) {
        log.info("vehicle company",request.getManufactureCompany());
        var vehicle = Vehicle.
                builder()
                .chasis(request.getChasis())
                .manufactureCompany(request.getManufactureCompany())
                .manufactureYear(request.getManufactureYear())
                .modelName(request.getModelName())
                .price(request.getPrice())
                .plateNumber(request.getPlateNumber()).build();
        Vehicle vehicle1 = vehicleRepo.save(vehicle);
        return VehicleResponse.builder().success(true).message("success created vehicle").data(List.of(vehicle1)).build();
    }

    public VehicleResponse deleteVehicle(UUID id) {
        Optional<Vehicle> vehicle = vehicleRepo.findById(id);
        if (vehicle.isEmpty()) {
            return VehicleResponse.builder().success(false).message("Vehicle not found").data(null).build();
        }
        vehicleRepo.deleteById(id);
        return VehicleResponse.builder().data(null).success(true).message("vehicle deleted successfully").build();
    }
}
