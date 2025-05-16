package com.api.vehiclemanagement.repositories;

import com.api.vehiclemanagement.entities.Vehicle;
import com.api.vehiclemanagement.models.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>, PagingAndSortingRepository<Vehicle, Long> {
//    @Query("SELECT v FROM Vehicle v WHERE " +
//            "(:brandName IS NULL OR v.brand.name = :brandName) AND " +
//            "(:year IS NULL OR v.year = :year) AND " +
//            "(:price IS NULL OR v.price = :price) AND " +
//            "(:owner IS NULL OR v.owner = :owner)")
//    Page<Vehicle> search(@Param("brandName") String brandName,
//                         @Param("year") Integer year,
//                         @Param("price") Long price,
//                         @Param("owner") String owner,
//                         Pageable pageable);

    @Query("SELECT v FROM Vehicle v WHERE " +
            "(:#{#request.brandName} IS NULL OR v.brand.name = :#{#request.brandName}) AND " +
            "(:#{#request.year} IS NULL OR v.year = :#{#request.year}) AND " +
            "(:#{#request.price} IS NULL OR v.price = :#{#request.price}) AND " +
            "(:#{#request.owner} IS NULL OR v.owner = :#{#request.owner})")
    Page<Vehicle> search(@Param("request") SearchRequest request, Pageable pageable);

    @Query("SELECT v FROM Vehicle v " +
            "WHERE " +
            "   (v.price > 10000000 AND v.brand.name LIKE 'S%') " +
            "   OR " +
            "   (v.price <= 10000000 AND v.brand.type" +
            " = com.api.vehiclemanagement.entities.Brand.BrandType.BUS)")
    Page<Vehicle> filterByPriceAndBrand(Pageable pageable);

    Page<Vehicle> findAll(Pageable pageable);
}
