package com.vehiclemanagement.api.vehicle_management.repositories;

import com.vehiclemanagement.api.vehicle_management.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Query("SELECT b FROM Brand b WHERE b.name = :name")
    List<Brand> findByName(String name);

    @Query("SELECT b FROM Brand b WHERE b.type = :type")
    List<Brand> findByType(Brand.BrandType type);
}
