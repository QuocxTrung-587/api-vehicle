package com.vehiclemanagement.api.vehicle_management.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleReportDTO {
    private Long id;
    private String name;
    private Integer year;
    private BigDecimal price;
    private String owner;
    private String brandName;
    private String brandType;
    
    public VehicleReportDTO(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.name = vehicle.getName();
        this.year = vehicle.getYear();
        this.price = vehicle.getPrice() != null ? new BigDecimal(vehicle.getPrice()) : null;
        this.owner = vehicle.getOwner();
        
        if (vehicle.getBrand() != null) {
            this.brandName = vehicle.getBrand().getName();
            this.brandType = vehicle.getBrand().getType() != null ? 
                vehicle.getBrand().getType().toString() : null;
        }
    }
}