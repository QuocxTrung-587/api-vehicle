package com.vehiclemanagement.api.vehicle_management.repositories;

import com.vehiclemanagement.api.vehicle_management.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findBrandsByNameContainingIgnoreCase(String name);

    List<Brand> findBrandsByType(Brand.BrandType type);
}
