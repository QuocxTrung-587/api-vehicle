package com.vehiclemanagement.api.vehicle_management.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int year;
    private Long price;
    private  String owner;
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
