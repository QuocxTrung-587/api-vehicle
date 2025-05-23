package com.api.vehiclemanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    private boolean active = true;

    public enum BrandType {
        CAR,TRUCK,BUS
    }

    //    @JsonIgnoreProperties("brand")
    @OneToMany(mappedBy = "brand")
    @JsonManagedReference
    private List<Vehicle> vehicles;
}