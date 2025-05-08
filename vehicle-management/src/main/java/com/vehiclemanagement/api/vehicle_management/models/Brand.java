package com.vehiclemanagement.api.vehicle_management.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private BrandType type;

    public enum BrandType {
        CAR,TRUCK,BUS
    }

    @OneToMany(mappedBy = "brand")
    @JsonIgnoreProperties("brand")
    private List<Vehicle> vehicles;
}
