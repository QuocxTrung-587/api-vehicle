package com.api.vehiclemanagement.models;

import com.api.vehiclemanagement.entities.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {
    private Long id;

    private String name;

    private Brand.BrandType type;

    private boolean active = true;

    private List<VehicleDTO> vehicles;

    public BrandDTO(Brand brand) {
        this.id = brand.getId();
        this.name = brand.getName();
        this.type = brand.getType();
        this.active = brand.isActive();

        if (brand.getVehicles() != null) {
            this.vehicles = brand.getVehicles().stream()
                    .map(VehicleDTO::new)
                    .collect(Collectors.toList());
        }
    }

    public Brand toBrand() {
        Brand brand = new Brand();
        if (id != null) {
            brand.setId(id);
        }
        brand.setName(name);
        brand.setType(type);
        brand.setActive(active);
        return brand;
    }
}
