package com.example.planner.controller;

import com.example.planner.dto.VehicleRequest;
import com.example.planner.dto.VehicleResponse;
import com.example.planner.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vehicle")
@Slf4j
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping("/")
    public VehicleResponse createVehicle(@RequestBody VehicleRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(fieldError -> {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            });
            if (!errors.isEmpty()) {
                String error = errors.values().iterator().next();
                return VehicleResponse.builder().success(false).message(error).data(null).build();
            }
        }
        return vehicleService.createVehicle(request);
    }

    @GetMapping("/")
    public VehicleResponse getVehicles() {
        return vehicleService.getVehicles();
    }
}

