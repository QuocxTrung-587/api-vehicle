package com.vehiclemanagement.api.vehicle_management.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    private Long id;

    private String name;

    private Integer year;

    private Long price;

    private String owner;

    private Long brandId;

    private Instant createdAt;
    private BrandSummary brand;

    public VehicleDTO(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.name = vehicle.getName();
        this.year = vehicle.getYear();
        this.price = vehicle.getPrice();
        this.owner = vehicle.getOwner();
        this.createdAt = vehicle.getCreatedAt();

        if (vehicle.getBrand() != null) {
            this.brand = new BrandSummary(vehicle.getBrand());
            this.brandId = vehicle.getBrand().getId();
        }
    }

    public Vehicle toVehicle() {
        Vehicle vehicle = new Vehicle();
        if (id != null) {
            vehicle.setId(id);
        }
        vehicle.setName(name);
        vehicle.setYear(year);
        vehicle.setPrice(price);
        vehicle.setOwner(owner);
        vehicle.setCreatedAt(createdAt);

        if (brandId == null && brand != null && brand.getId() > 0) {
            brandId = brand.getId();
        }

        if (brandId != null) {
            Brand brandRef = new Brand();
            brandRef.setId(brandId);
            vehicle.setBrand(brandRef);
        }

        return vehicle;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BrandSummary {
        private long id;
        private String name;
        private Brand.BrandType type;

        public BrandSummary(Brand brand) {
            this.id = brand.getId();
            this.name = brand.getName();
            this.type = brand.getType();
        }
    }
}