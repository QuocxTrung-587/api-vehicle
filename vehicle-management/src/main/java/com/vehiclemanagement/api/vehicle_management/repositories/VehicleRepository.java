package com.vehiclemanagement.api.vehicle_management.repositories;

import com.vehiclemanagement.api.vehicle_management.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v WHERE " +
            "(:brandName IS NULL OR v.brand.name = :brandName) AND " +
            "(:year IS NULL OR v.year = :year) AND " +
            "(:price IS NULL OR v.price = :price) AND " +
            "(:owner IS NULL OR v.owner = :owner)")
    List<Vehicle> search(@Param("brandName") String brandName,
                         @Param("year") Integer year,
                         @Param("price") Long price,
                         @Param("owner") String owner);

    @Query("SELECT v FROM Vehicle v " +
            "WHERE " +
            "   (v.price > 10000000 AND v.brand.name LIKE 'S%') " +
            "   OR " +
            "   (v.price <= 10000000 AND v.brand.type = com.vehiclemanagement.api.vehicle_management.models.Brand.BrandType.BUS)")
    List<Vehicle> filterByPriceAndBrand();
}
