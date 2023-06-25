package com.example.planner.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID _id;
    private String chasis;
    private String manufactureCompany;
    private String manufactureYear;
    private String plateNumber;
    private Integer price;
    private String modelName;
}